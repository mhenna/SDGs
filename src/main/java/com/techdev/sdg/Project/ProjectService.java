package com.techdev.sdg.Project;

import com.techdev.sdg.Entity.Entity;
import com.techdev.sdg.Resource.Resource;
import com.techdev.sdg.Resource.ResourceRepository;
import com.techdev.sdg.Utils.Utils;
import com.techdev.sdg.WorkLocation.WorkLocation;
import com.techdev.sdg.WorkLocation.WorkLocationRepository;
import com.techdev.sdg.intendedSDG.IntendedSDG;
import com.techdev.sdg.intendedSDG.IntendedSDGRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private WorkLocationRepository workLocationRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private IntendedSDGRepository intendedSDGRepository;

    @Autowired
    private Utils utils;

    public Project save(Map<String, Object> body, Entity entity, List<Entity> viewers) {
        Project p = new Project(
                Objects.toString(body.get(Project.NAME), null),
                entity,
                Objects.toString(body.get(Project.AIM), null),
                Long.parseLong(Objects.toString(body.get(Project.DURATION), null)),
                Long.parseLong(Objects.toString(body.get(Project.PEOPLETARGETED), null))
        );


        List<Long> workLocationIds = utils.getIdsListFromReqBody(body, Project.WORKLOCATION);
        List<WorkLocation> workLocations = workLocationRepository.findAllById(workLocationIds);

        List<Long> resourceIds = utils.getIdsListFromReqBody(body, Project.RESOURCE);
        List<Resource> resources = resourceRepository.findAllById(resourceIds);

        List<Long> intendedSDGsIds = utils.getIdsListFromReqBody(body, Project.INTENDEDSDG);
        List<IntendedSDG> intendedSDGs = intendedSDGRepository.findAllById(intendedSDGsIds);

        List<Long> subProjectIds = utils.getIdsListFromReqBody(body, Project.SUBPROJECT);

        if (!Objects.isNull(subProjectIds)) {
            List<Project> subProjects = repository.findAllById(subProjectIds);
            p.getSubProjects().addAll(subProjects);
            for (Project proj : subProjects) {
                proj.setParentProject(p);
            }
        }

        p.getEntities().add(entity);
        p.getViewers().addAll(viewers);
        p.getWorkLocations().addAll(workLocations);
        p.getResources().addAll(resources);
        p.getIntendedSDGs().addAll(intendedSDGs);

        repository.save(p);

        return p;
    }

    public Project get(Long id) throws Exception {
        Project project = repository.findById(id).get();
        if (Objects.isNull(project))
            throw new Exception("project with specified id does not exist");
        else
            return project;
    }

    public List<Project> findAll() throws Exception {
        List<Project> projects = repository.findAll();
        if (projects.isEmpty())
            throw new Exception("cannot get work locations");
        else
            return projects;
    }

    public List<Project> findViewerProjects(Entity viewer, Entity all, Entity typed) {
        List<Project> projects = repository.findByViewersContaining(viewer);
        List<Project> projectsWithTypeAll = repository.findByViewersContaining(all);
        List<Project> projectsWithViewerType = repository.findByViewersContaining(typed);
        List<Project> projectsCreated = repository.findByOwner(viewer);
        List<Project> projectList = Stream.of(projects, projectsWithTypeAll, projectsWithViewerType, projectsCreated)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());

        return projectList;
    }

    public Project findByViewer(Entity viewer, long projectId) throws Exception {
        Project project = get(projectId);
        Set<Entity> viewers = project.getViewers();
        boolean flag = false;

        if (project.getOwner().getId().equals(viewer.getId()))
            flag = true;
        else {
            if (viewers.size() == 1) {
                String type = viewers.iterator().next().getType();
                if (type.equals("All") || (type.equals("PrivateSectors") && viewer.getType().equals("PrivateSector"))
                        || (type.equals("NGOs") && viewer.getType().equals("NGO")))
                    flag = true;
                else if (type.equals("PrivateSector") || type.equals("NGO"))
                    if (viewers.contains(viewer))
                        flag = true;
                    else
                        flag = false;
                else
                    flag = false;
            } else {
                if (viewers.contains(viewer))
                    flag = true;
                else flag = false;
            }
        }
        if (flag)
            return project;
        else
            throw new Exception("Not allowed to view this project");
    }
}
