package com.techdev.sdg.Request;

import com.techdev.sdg.Entity.Entity;
import com.techdev.sdg.Entity.EntityService;
import com.techdev.sdg.Project.Project;
import com.techdev.sdg.Project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
public class RequestController {

    @Autowired
    private RequestService service;

    @Autowired
    private EntityService entityService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = Router.REQUEST, method = RequestMethod.POST)
    public ResponseEntity<Object> addRequest(@RequestBody Map<String, Object> body) {
        ResponseEntity<Object> res;
        try {
            String name = entityService.currentUserName();
            Entity requestor = entityService.findByUsername(name);
            Project project = projectService.get(Long.parseLong(String.valueOf(body.get(Request.PROJECT))));
            for (Request r : requestor.getRequests())
                if (r.getProject().getId().equals(project.getId()))
                    throw (new Exception("You have already submitted a request to join this project"));
            Request req = service.save(requestor, project);
            res = new ResponseEntity<>(req.toMap(), HttpStatus.OK);
        } catch (Exception e) {
            res = new ResponseEntity<Object>("Unexpected error occured: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }

    @RequestMapping(value = Router.REQUEST, method = RequestMethod.PUT)
    public ResponseEntity<Object> modifyStatus(@RequestBody Map<String, Object> body) {
        ResponseEntity<Object> res;
        try {
            String name = entityService.currentUserName();
            Request request = service.findById(Long.parseLong(String.valueOf(body.get(Request.ID))));
            if (!request.getProject().getOwner().getName().equals(name))
                throw new Exception("Only the project owner can modify the status of a request");
            Request updatedRequest = service.modifyStatus(request, String.valueOf(body.get(Request.STATUS)));
            res = new ResponseEntity<>(updatedRequest.toMap(), HttpStatus.OK);
        } catch (Exception e) {
            res = new ResponseEntity<Object>("Unexpected error occured: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }

    @RequestMapping(value = Router.GETREQUESTS, method = RequestMethod.GET)
    public ResponseEntity<Object> getRequests(@PathVariable Long id) {
        ResponseEntity<Object> res;
        try {
            String name = entityService.currentUserName();
            Project project = projectService.get(id);
            if (!project.getOwner().getName().equals(name))
                throw (new Exception("Only the project owner can view the join requests"));
            List<Map<String, Object>> requests = service.findByProject(project);
            res = new ResponseEntity<>(requests, HttpStatus.OK);
        } catch (Exception e) {
            res = new ResponseEntity<Object>("Unexpected error occured: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }

    @RequestMapping(value = Router.GETSENTREQUESTS, method = RequestMethod.GET)
    public ResponseEntity<Object> getSentRequests() {
        ResponseEntity<Object> res;
        try {
            String name = entityService.currentUserName();
            Entity entity = entityService.findByUsername(name);
            List<Map<String, Object>> requests = new ArrayList<>();
            for (Request r : entity.getRequests()) {
                requests.add(r.toMap());
            }

            res = new ResponseEntity<>(requests, HttpStatus.OK);
        } catch (Exception e) {
            res = new ResponseEntity<Object>("Unexpected error occured: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }
}
