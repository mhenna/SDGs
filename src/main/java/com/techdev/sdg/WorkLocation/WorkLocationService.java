package com.techdev.sdg.WorkLocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkLocationService {
    @Autowired
    private WorkLocationRepository repository;

    public List<WorkLocation> findAll() throws Exception {
        List<WorkLocation> workLocations = repository.findAll();
        if (workLocations.isEmpty())
            throw new Exception ("cannot get work locations");
        else
            return workLocations;
    }
}
