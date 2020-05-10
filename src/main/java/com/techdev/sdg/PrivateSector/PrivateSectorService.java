package com.techdev.sdg.PrivateSector;

import com.techdev.sdg.DirectionToImpact.DirectionToImpact;
import com.techdev.sdg.DirectionToImpact.DirectionToImpactRepository;
import com.techdev.sdg.Project.Project;
import com.techdev.sdg.Project.ProjectRepository;
import com.techdev.sdg.Resource.Resource;
import com.techdev.sdg.Resource.ResourceRepository;
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

        Project project = projectRepository.findById(Long.parseLong(body.get(PrivateSector.PROJECT).toString())).get();

        List<Integer> worklocationIds = JSONValue.parse(body.get(PrivateSector.WORKLOCATION).toString(),
                ArrayList.class);
        List<Long> workLocationsLongValues = worklocationIds.stream().map(Integer::longValue)
                .collect(Collectors.toList());
        List<WorkLocation> workLocations = workLocationRepository.findAllById(workLocationsLongValues);

        List<Integer> resourceIds = JSONValue.parse(body.get(PrivateSector.RESOURCE).toString(), ArrayList.class);
        List<Long> resourceLongValues = resourceIds.stream().map(Integer::longValue).collect(Collectors.toList());
        List<Resource> resources = resourceRepository.findAllById(resourceLongValues);

        List<Integer> directionToImpactIds = JSONValue.parse(body.get(PrivateSector.DIRECTIONTOIMPACT).toString(),
                ArrayList.class);
        List<Long> directionToImpactValues = directionToImpactIds.stream().map(Integer::longValue)
                .collect(Collectors.toList());
        List<DirectionToImpact> directionsToImpact = directionToImpactRepository.findAllById(directionToImpactValues);

        ps.getProjects().add(project);
        ps.getWorkLocations().addAll(workLocations);
        ps.getResources().addAll(resources);
        ps.getDirectionToImpact().addAll(directionsToImpact);

        repository.save(ps);
        return ps;
    }
}
