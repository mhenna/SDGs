package com.techdev.sdg.Project;

import com.techdev.sdg.Resource.Resource;
import com.techdev.sdg.Resource.ResourceRepository;
import com.techdev.sdg.Utils.Utils;
import com.techdev.sdg.WorkLocation.WorkLocation;
import com.techdev.sdg.WorkLocation.WorkLocationRepository;
import com.techdev.sdg.intendedSDG.IntendedSDG;
import com.techdev.sdg.intendedSDG.IntendedSDGRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    public Project save(Map<String, Object> body) {
        Project p = new Project(
                Objects.toString(body.get(Project.NAME), null),
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

        p.getWorkLocations().addAll(workLocations);
        p.getResources().addAll(resources);
        p.getIntendedSDGs().addAll(intendedSDGs);
        repository.save(p);
        return p;
    }
}
