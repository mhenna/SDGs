package com.techdev.sdg.NGO;

import com.techdev.sdg.DirectionToImpact.DirectionToImpact;
import com.techdev.sdg.DirectionToImpact.DirectionToImpactRepository;
import com.techdev.sdg.File.File;
import com.techdev.sdg.Project.Project;
import com.techdev.sdg.Project.ProjectRepository;
import com.techdev.sdg.Resource.Resource;
import com.techdev.sdg.Resource.ResourceRepository;
import com.techdev.sdg.Utils;
import com.techdev.sdg.WorkLocation.WorkLocation;
import com.techdev.sdg.WorkLocation.WorkLocationRepository;
import com.techdev.sdg.intendedSDG.IntendedSDG;
import com.techdev.sdg.intendedSDG.IntendedSDGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class NGOService {

    @Autowired
    private Utils utils;

    @Autowired
    private NGORepository repository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private WorkLocationRepository workLocationRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private DirectionToImpactRepository directionToImpactRepository;

    @Autowired
    private IntendedSDGRepository intendedSDGRepository;

    public NGO save(Map<String, Object> body, List<File> files) {
        NGO ngo = new NGO(
                Objects.toString(body.get(NGO.NAME), null),
                Objects.toString(body.get(NGO.EMAIL), null),
                Objects.toString(body.get(NGO.PASSWORD), null),
                Objects.toString(body.get(NGO.MAINCONTACT), null),
                Objects.toString(body.get(NGO.VISION), null)
        );

        List<Long> projectIds = utils.getIdsListFromReqBody(body, NGO.PROJECT);
        List<Project> projects = projectRepository.findAllById(projectIds);

        List<Long> worklocationIds = utils.getIdsListFromReqBody(body, NGO.WORKLOCATION);
        List<WorkLocation> workLocations = workLocationRepository.findAllById(worklocationIds);

        List<Long> resourceIds = utils.getIdsListFromReqBody(body, NGO.RESOURCE);
        List<Resource> resources = resourceRepository.findAllById(resourceIds);

        List<Long> directionToImpactIds = utils.getIdsListFromReqBody(body, NGO.DIRECTIONTOIMPACT);
        List<DirectionToImpact> directionsToImpact = directionToImpactRepository.findAllById(directionToImpactIds);

        List<Long> intendedSDGIds = utils.getIdsListFromReqBody(body, NGO.INTENDEDSDG);
        List<IntendedSDG> intendedSDGs = intendedSDGRepository.findAllById(intendedSDGIds);

        ngo.getDirectionToImpact().addAll(directionsToImpact);
        ngo.getIntendedSDGs().addAll(intendedSDGs);
        ngo.getProjects().addAll(projects);
        ngo.getWorkLocations().addAll(workLocations);
        ngo.getResources().addAll(resources);
        ngo.getFiles().addAll(files);

        for (File f : files) {
            f.setNgo(ngo);
        }

        return repository.save(ngo);
    }
}
