package com.techdev.sdg.Discussion;

import com.techdev.sdg.Discussion.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DiscussionController  {
    @Autowired
    private DiscussionService service;

    @RequestMapping(value = Router.ADDDISCUSSION, method = RequestMethod.POST)
    public ResponseEntity<Object> addQuestion(@RequestBody Map<String, Object> body) {
        ResponseEntity<Object> res;
        try {
            Discussion discussion = service.save(body);
            res = new ResponseEntity<Object>(discussion,HttpStatus.OK);
        } catch (Exception e) {
            res = new ResponseEntity<Object>("Unexpected error occured: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }

    @RequestMapping(value = Router.ADDANSWER, method = RequestMethod.POST)
    public ResponseEntity<Object> addAnswer(@RequestBody Map<String, Object> body) {
        ResponseEntity<Object> res;
        try {
            Discussion discussion = service.saveAnswer(body);
            res = new ResponseEntity<Object>(discussion.toMap(),HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            res = new ResponseEntity<Object>("Unexpected error occured: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }
}
