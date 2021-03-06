package com.techdev.sdg.Resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResourceController {
    @Autowired
    private ResourceService service;
    @RequestMapping(value = Router.RETRIEVE, method = RequestMethod.GET)
    public ResponseEntity<Object> get() {
        ResponseEntity<Object> res = null;
        try {
            List<Resource> resources = service.findAll();
            res = new ResponseEntity<>(resources, HttpStatus.OK);
        } catch (Exception e) {
            res = new ResponseEntity<>("Unexpected error occured: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }
}