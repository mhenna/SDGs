package com.techdev.sdg.Entity.NGO;

import com.techdev.sdg.DirectionToImpact.DirectionToImpact;
import com.techdev.sdg.DirectionToImpact.DirectionToImpactRepository;
import com.techdev.sdg.Entity.Entity;
import com.techdev.sdg.Entity.EntityRepository;
import com.techdev.sdg.File.File;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class NGOService {

    @Autowired
    private Utils utils;

    @Autowired
    private EntityRepository repository;

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

    public Entity save(Map<String, Object> body, List<File> files) {
        Entity ngo = new Entity(
                Objects.toString(body.get(Entity.NAME), null),
                Objects.toString(body.get(Entity.EMAIL), null),
                bcryptEncoder.encode(Objects.toString(body.get(Entity.PASSWORD), null)),
                Objects.toString(body.get(Entity.MAINCONTACT), null),
                Objects.toString(body.get(Entity.VISION), null)
        );


        List<Long> worklocationIds = utils.getIdsListFromReqBody(body, Entity.WORKLOCATION);
        List<WorkLocation> workLocations = workLocationRepository.findAllById(worklocationIds);

        List<Long> resourceIds = utils.getIdsListFromReqBody(body, Entity.RESOURCE);
        List<Resource> resources = resourceRepository.findAllById(resourceIds);

        List<Long> directionToImpactIds = utils.getIdsListFromReqBody(body, Entity.DIRECTIONTOIMPACT);
        List<DirectionToImpact> directionsToImpact = directionToImpactRepository.findAllById(directionToImpactIds);

        List<Long> intendedSDGIds = utils.getIdsListFromReqBody(body, Entity.INTENDEDSDG);
        List<IntendedSDG> intendedSDGs = intendedSDGRepository.findAllById(intendedSDGIds);

        ngo.getDirectionToImpact().addAll(directionsToImpact);
        ngo.getIntendedSDGs().addAll(intendedSDGs);
        ngo.getWorkLocations().addAll(workLocations);
        ngo.getResources().addAll(resources);
        ngo.getFiles().addAll(files);

        for (File f : files) {
            f.setNgo(ngo);
        }
        return repository.save(ngo);
    }

    public List<Map<String, Object>> findAll() {
        List<Entity> ngo = repository.findByType("NGO");
        List<Map<String, Object>> res = new ArrayList<>();
        for (Entity n : ngo) {
            res.add(n.toMap());
        }
        return res;
    }
}
