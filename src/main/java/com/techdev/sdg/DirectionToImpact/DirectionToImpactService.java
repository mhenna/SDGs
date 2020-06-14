package com.techdev.sdg.DirectionToImpact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techdev.sdg.Utils.Utils;
import java.util.List;
import java.util.Objects;

@Service
public class DirectionToImpactService {

    @Autowired
    private DirectionToImpactRepository repository;

    public List<DirectionToImpact> findAll() throws Exception {
        List<DirectionToImpact> directionToImpact = repository.findAll();
        if (directionToImpact.isEmpty())
            throw new Exception ("cannot get directions");
        else
            return directionToImpact;
    }
}
