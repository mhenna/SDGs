package com.techdev.sdg.Resource;

import com.techdev.sdg.Resource.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {
    @Autowired
    private ResourceService service;
}