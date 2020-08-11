package com.techdev.sdg.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {

    @Autowired
    private AdminService service;

    @RequestMapping(value = Router.GETSIGNUPREQUESTS, method = RequestMethod.GET)
    public ResponseEntity<Object> get() {
        ResponseEntity<Object> res = null;
        try {
            List<Map<String, Object>> entities = service.getSignupRequests();
            res = new ResponseEntity<>(entities, HttpStatus.OK);
        } catch (Exception e) {
            res = new ResponseEntity<>("Unexpected error occured: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }

    @RequestMapping(value = Router.APPROVESIGNUPREQUEST, method = RequestMethod.PUT)
    public ResponseEntity<Object> approveSignupRequest(@PathVariable Long id) {
        ResponseEntity<Object> res = null;
        try {
            service.approveSignupRequest(id);
            Map<String, Object> r = new HashMap<>();
            r.put("message", "Sign-up request has been approved");
            res = new ResponseEntity<>(r, HttpStatus.OK);
        } catch (Exception e) {
            res = new ResponseEntity<>("Unexpected error occured: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }

    @RequestMapping(value = Router.ADDADMIN, method = RequestMethod.POST)
    public ResponseEntity<Object> addAdmin(@RequestBody Map<String, Object> body) {
        ResponseEntity<Object> res;
        try {
            Admin admin = service.saveAdmin(body);
            Map<String, Object> r = new HashMap<>();
            r.put("message", "Admin Saved");
            res = new ResponseEntity<>(r, HttpStatus.OK);
        }
        catch (Exception e) {
            res = new ResponseEntity<>("Unexpected error occured: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }

    @RequestMapping(value = Router.GETADMINS, method = RequestMethod.GET)
    public ResponseEntity<Object> getAdmins() {
        ResponseEntity<Object> res = null;
        try {
            List<Map<String, Object>> entities = service.getAdmins();
            res = new ResponseEntity<>(entities, HttpStatus.OK);
        } catch (Exception e) {
            res = new ResponseEntity<>("Unexpected error occured: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }
    @RequestMapping(value = Router.DELETEADMIN, method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteAdmin(@PathVariable Long id) {
        ResponseEntity<Object> res = null;
        try {
            service.deleteAdmin(id);
            Map<String, Object> r = new HashMap<>();
            r.put("message", "Admin Deleted");
            res = new ResponseEntity<>(r, HttpStatus.OK);
        } catch (Exception e) {
            res = new ResponseEntity<>("Unexpected error occured: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }
}
