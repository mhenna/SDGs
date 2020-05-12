package com.techdev.sdg.PrivateSector;

import com.techdev.sdg.DirectionToImpact.DirectionToImpact;
import com.techdev.sdg.DirectionToImpact.DirectionToImpactRepository;
import com.techdev.sdg.Project.Project;
import com.techdev.sdg.Project.ProjectRepository;
import com.techdev.sdg.Resource.Resource;
import com.techdev.sdg.Resource.ResourceRepository;
import com.techdev.sdg.Utils;
import com.techdev.sdg.WorkLocation.WorkLocation;
import com.techdev.sdg.WorkLocation.WorkLocationRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PrivateSectorService {

    @Autowired
    private Utils utils;

    @Autowired
    private PrivateSectorRepository repository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private WorkLocationRepository workLocationRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private DirectionToImpactRepository directionToImpactRepository;

    public PrivateSector save(Map<String, Object> body) {
        PrivateSector ps = new PrivateSector(
                Objects.toString(body.get(PrivateSector.NAME), null),
                Objects.toString(body.get(PrivateSector.EMAIL), null),
                Objects.toString(body.get(PrivateSector.PASSWORD), null)
        );

        List<Long> projectIds = utils.getIdsListFromReqBody(body, PrivateSector.PROJECT);
        List<Project> projects = projectRepository.findAllById(projectIds);

        List<Long> worklocationIds = utils.getIdsListFromReqBody(body, PrivateSector.WORKLOCATION);
        List<WorkLocation> workLocations = workLocationRepository.findAllById(worklocationIds);

        List<Long> resourceIds = utils.getIdsListFromReqBody(body, PrivateSector.RESOURCE);
        List<Resource> resources = resourceRepository.findAllById(resourceIds);

        List<Long> directionToImpactIds = utils.getIdsListFromReqBody(body, PrivateSector.DIRECTIONTOIMPACT);
        List<DirectionToImpact> directionsToImpact = directionToImpactRepository.findAllById(directionToImpactIds);

        ps.getProjects().addAll(projects);
        ps.getWorkLocations().addAll(workLocations);
        ps.getResources().addAll(resources);
        ps.getDirectionToImpact().addAll(directionsToImpact);
        repository.save(ps);
        return ps;
    }
}
