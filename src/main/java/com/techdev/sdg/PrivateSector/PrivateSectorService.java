package com.techdev.sdg.PrivateSector;

import com.techdev.sdg.DirectionToImpact.DirectionToImpact;
import com.techdev.sdg.DirectionToImpact.DirectionToImpactRepository;
import com.techdev.sdg.Project.Project;
import com.techdev.sdg.Project.ProjectRepository;
import com.techdev.sdg.Resource.Resource;
import com.techdev.sdg.Resource.ResourceRepository;
import com.techdev.sdg.Utils.Utils;
import com.techdev.sdg.WorkLocation.WorkLocation;
import com.techdev.sdg.WorkLocation.WorkLocationRepository;
import com.techdev.sdg.intendedSDG.IntendedSDG;
import com.techdev.sdg.intendedSDG.IntendedSDGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private IntendedSDGRepository intendedSDGRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public PrivateSector save(Map<String, Object> body) {
        PrivateSector ps = new PrivateSector(
                Objects.toString(body.get(PrivateSector.NAME), null),
                Objects.toString(body.get(PrivateSector.EMAIL), null),
                bcryptEncoder.encode(Objects.toString(body.get(PrivateSector.PASSWORD), null))
        );


        List<Long> worklocationIds = utils.getIdsListFromReqBody(body, PrivateSector.WORKLOCATION);
        List<WorkLocation> workLocations = workLocationRepository.findAllById(worklocationIds);

        List<Long> resourceIds = utils.getIdsListFromReqBody(body, PrivateSector.RESOURCE);
        List<Resource> resources = resourceRepository.findAllById(resourceIds);

        List<Long> directionToImpactIds = utils.getIdsListFromReqBody(body, PrivateSector.DIRECTIONTOIMPACT);
        List<DirectionToImpact> directionsToImpact = directionToImpactRepository.findAllById(directionToImpactIds);

        List<Long> intendedSDGIds = utils.getIdsListFromReqBody(body, PrivateSector.INTENDEDSDG);
        List<IntendedSDG> intendedSDGs = intendedSDGRepository.findAllById(intendedSDGIds);

        ps.getWorkLocations().addAll(workLocations);
        ps.getResources().addAll(resources);
        ps.getDirectionToImpact().addAll(directionsToImpact);
        ps.getIntendedSDGs().addAll(intendedSDGs);

        repository.save(ps);
        return ps;
    }

    public PrivateSector findById(Long id) throws Exception {
        PrivateSector ps = repository.findById(id).get();
        if (Objects.isNull(ps))
            throw new Exception ("NGO with specified id does not exist");
        else
            return ps;
    }

    public List<Map<String, Object>> findAll() {
        List<PrivateSector> ps = repository.findAll();
        List<Map<String, Object>> res = new ArrayList<>();
        for (PrivateSector n : ps) {
            res.add(n.toMap());
        }
        return res;
    }
}
