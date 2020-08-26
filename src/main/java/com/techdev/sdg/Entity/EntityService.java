package com.techdev.sdg.Entity;

import com.techdev.sdg.Project.Project;
import com.techdev.sdg.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class EntityService {

    @Autowired
    private EntityRepository repository;

    @Autowired
    private Utils utils;

    public Entity findByUsername(String username) {
        Entity e = repository.findByName(username);
        return e;
    }

    public Entity findById(Long id) throws Exception {
        Entity entity = repository.findById(id).get();
//        if (!entity.exists())
//            throw new Exception ("Entity with specified id does not exist");
//        else
        return entity;
    }

    public List<Entity> findProjectViewers(Map<String, Object> body) {
        List<Long> entityIds = utils.getIdsListFromReqBody(body, Project.VIEWER);
        List<Entity> e = repository.findAllById(entityIds);
        return e;
    }

    public List<Map<String, Object>> findAll() {
        List<Entity> entitiesList = repository.findAll();
        List<Map<String, Object>> entities = new ArrayList<>();

        for (Entity e : entitiesList)
            entities.add(e.toMap());

        return entities;
    }

    public String currentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
