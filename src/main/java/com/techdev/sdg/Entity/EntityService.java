package com.techdev.sdg.Entity;

import com.techdev.sdg.Project.Project;
import com.techdev.sdg.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public List<Entity> findProjectViewers(Map<String, Object> body) {
        List<Long> entityIds = utils.getIdsListFromReqBody(body, Project.VIEWER);
        List<Entity> e = repository.findAllById(entityIds);
        return e;
    }
}
