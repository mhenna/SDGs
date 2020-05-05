package com.techdev.sdg.intendedSDG;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntendedSDGController {

    @Autowired
    private IntendedSDGService service;

}
