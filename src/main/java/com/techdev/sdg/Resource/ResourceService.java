package com.techdev.sdg.Resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ResourceService {
    @Autowired
    private ResourceRepository repository;

    public List<Resource> findAll() throws Exception {
        List<Resource> resources = repository.findAll();
        if (resources.isEmpty())
            throw new Exception ("cannot get resources");
        else
            return resources;
    }
}
