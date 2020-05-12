package com.techdev.sdg.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService service;

    @RequestMapping(value = Router.ADDPROJECT, method = RequestMethod.POST)
    public ResponseEntity<Object> addProject(@RequestBody Map<String, Object> body) {
        ResponseEntity<Object> res;
        try {
            Project p = service.save(body);
            res = new ResponseEntity<Object>(p, HttpStatus.OK);
        } catch (Exception e) {
            res = new ResponseEntity<Object>("Unexpected error occured: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }
}
