package com.techdev.sdg.DirectionToImpact;

import com.techdev.sdg.DirectionToImpact.DirectionToImpactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirectionToImpactController {
    @Autowired
    private DirectionToImpactService service;
}