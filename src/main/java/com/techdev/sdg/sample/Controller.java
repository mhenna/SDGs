package com.techdev.sdg.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    private SampleService service;

    @RequestMapping(value = Router.TESTROUTE, method = RequestMethod.POST)
    public ResponseEntity<Object> test(@RequestBody Map<String, Object> body) {
        try {
            SampleModel s = service.save(body);
            return new ResponseEntity<Object>(s, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Unexpected error occured: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = Router.TESTROUTE, method = RequestMethod.GET)
    public ResponseEntity<Object> test(@RequestParam Long id) {
        try {
            Optional<SampleModel> s = service.getById(id);
            return new ResponseEntity<Object>(s, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Unexpected error occured: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
