package com.techdev.sdg.NGO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techdev.sdg.File.File;
import com.techdev.sdg.File.FileService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class NGOController {

    @Autowired
    private NGOService service;

    @Autowired
    private FileService fileService;

    @RequestMapping(value = Router.REGISTER, method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestParam Map<String, Object> body, 
                                           @RequestParam List<Long> resource,
                                           @RequestParam List<Long> workLocation,
                                           @RequestParam List<Long> directionToImpact,
                                           @RequestParam List<Long> intendedSDG,
                                           @RequestParam("file") MultipartFile[] files) {
        ResponseEntity<Object> res;
        body.put(NGO.RESOURCE, resource);
        body.put(NGO.WORKLOCATION, workLocation);
        body.put(NGO.DIRECTIONTOIMPACT, directionToImpact);
        body.put(NGO.INTENDEDSDG, intendedSDG);

        List<File> docs = new ArrayList<>();
        try {
            docs = Arrays.asList(files)
                    .stream()
                    .map(file -> {
                        try {
                            return fileService.constructFile(file);
                        } catch (Exception e) {
                            return null;
                        }
                    })
                    .collect(Collectors.toList());
            if (docs.contains(null)) {
                res = new ResponseEntity<>("Error uploading one of your files. Make sure the file is unique",
                        HttpStatus.BAD_REQUEST);
                fileService.deleteFiles(docs);
            } else {
                NGO ngo = service.save(body, docs);
                res = new ResponseEntity<>(ngo.toMap(), HttpStatus.OK);
            }
        } catch (Exception e) {
            res = new ResponseEntity<>("Unexpected error occured: " + e.getMessage(), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
            fileService.deleteFiles(docs);
        }
        return res;
    }

    @RequestMapping(value = Router.RETRIEVE, method = RequestMethod.GET)
    public ResponseEntity<Object> get(@PathVariable Long id) {
        ResponseEntity<Object> res = null;
        try {
            NGO ngo = service.findById(id);
            res = new ResponseEntity<>(ngo.toMap(), HttpStatus.OK);
        } catch (Exception e) {
            res = new ResponseEntity<>("Unexpected error occured: " + e.getMessage(), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }
}
