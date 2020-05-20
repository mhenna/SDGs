package com.techdev.sdg.NGO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class NGOController {

    @Autowired
    private NGOService service;

    @RequestMapping(value = Router.REGISTER, method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestBody Map<String, Object> body) {
        ResponseEntity<Object> res;
        try {
            NGO ngo = service.save(body);
            res = new ResponseEntity<>(ngo.toMap(), HttpStatus.OK);
        } catch (Exception e) {
            res = new ResponseEntity<>("Unexpected error occured: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }
}
