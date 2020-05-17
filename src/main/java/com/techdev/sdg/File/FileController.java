package com.techdev.sdg.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class FileController {

    @Autowired
    private FileService service;

    @RequestMapping(value = Router.UPLOAD, method = RequestMethod.POST, consumes="multipart/form-data")
    public ResponseEntity<Object> register(@RequestParam Map<String, Object> body,
                                           @RequestParam("file") MultipartFile file) {
        ResponseEntity<Object> res;
        System.out.println(body);
        System.out.println(file);
        try {
            File f = service.constructFile(file);
            res = new ResponseEntity<>(f, HttpStatus.OK);
        } catch (Exception e) {
            res = new ResponseEntity<>("Unexpected error occured: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }

}
