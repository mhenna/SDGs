package com.techdev.sdg.Request;

import com.techdev.sdg.Entity.Entity;
import com.techdev.sdg.Project.Project;
import com.techdev.sdg.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RequestService {

    @Autowired
    private RequestRepository repository;

    @Autowired
    private Utils utils;

    public Request save(Entity requestor, Project project) throws Exception {
        try {
            Request request = new Request(requestor, project);
            request = repository.save(request);
            utils.sendEmail(project.getOwner().getEmail(),
                    "Hello " + project.getOwner().getName() + "," +
                            "\n\t" + requestor.getName() + " has requested to join your project " + project.getName()
                            + ".", "Project Join Request"
            );

            return request;
        } catch (Exception e) {
            throw e;
        }
    }

    public Request findById(Long id) {
        return repository.findById(id).get();
    }

    public Request modifyStatus(Request request, String status) {
        request.setStatus(status);
        return repository.save(request);
    }

    public List<Map<String, Object>> findByProject(Project project) {
        List<Request> r = repository.findByProject(project);
        List<Map<String, Object>> requests = new ArrayList<>();
        for (Request req : r) {
            requests.add(req.toMap());
        }

        return requests;
    }
}
