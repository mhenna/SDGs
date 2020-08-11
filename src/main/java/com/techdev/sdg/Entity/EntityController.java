package com.techdev.sdg.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class EntityController {

    @Autowired
    private EntityService service;

    @RequestMapping(value = Router.RETRIEVEALL, method = RequestMethod.GET)
    public ResponseEntity<Object> getAll() {
        ResponseEntity<Object> res = null;
        try {
            List<Map<String, Object>> entities = service.findAll();
            res = new ResponseEntity<>(entities, HttpStatus.OK);
        } catch (Exception e) {
            res = new ResponseEntity<>("Unexpected error occured: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }
}
