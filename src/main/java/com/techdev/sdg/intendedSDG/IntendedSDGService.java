package com.techdev.sdg.intendedSDG;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntendedSDGService {

    @Autowired
    private IntendedSDGRepository repository;

    public List<IntendedSDG> findAll() throws Exception {
        List<IntendedSDG> intendedSDG = repository.findAll();
        if (intendedSDG.isEmpty())
            throw new Exception ("cannot get SDGs");
        else
            return intendedSDG;
    }
}
