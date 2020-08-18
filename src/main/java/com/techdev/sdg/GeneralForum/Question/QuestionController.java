package com.techdev.sdg.GeneralForum.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class QuestionController {

    @Autowired
    private QuestionService service;

    @RequestMapping(value = Router.addQuestion, method = RequestMethod.POST)
    public ResponseEntity<Object> addQuestion(@RequestBody Map<String, Object> body) {
        ResponseEntity<Object> res;
        try{
            Question q = service.submit(body);
            res = new ResponseEntity<>(q.toMap(), HttpStatus.OK);
        } catch (Exception e) {
            res = new ResponseEntity<>("Unexpected error occured: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }

}
