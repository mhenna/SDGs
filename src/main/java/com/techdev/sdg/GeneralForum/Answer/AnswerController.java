package com.techdev.sdg.GeneralForum.Answer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class AnswerController {

    @Autowired
    private AnswerService service;

    @RequestMapping(value = Router.SUBMIT, method = RequestMethod.POST)
    public ResponseEntity<Object> submitAnswer(@RequestBody Map<String, Object> body) {
        ResponseEntity<Object> res;
        try{
            Answer ans = service.submit(body);
            res = new ResponseEntity<>(ans.toMap(), HttpStatus.OK);
        } catch (Exception e) {
            res = new ResponseEntity<>("Unexpected error occured: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }

}
