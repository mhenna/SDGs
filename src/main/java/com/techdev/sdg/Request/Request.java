package com.techdev.sdg.Request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.techdev.sdg.Project.Project;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "Request")
public class Request implements Serializable {
    final public static String ID = "id";
    final public static String REQUESTOR = "requestor";
    final public static String PROJECTOWNER = "projectOwner";
    final public static String PROJECT = "project";
    final public static String STATUS = "status";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne()
    @JoinColumn(name = "requestor_id", nullable = false)
    @JsonBackReference
    private com.techdev.sdg.Entity.Entity requestor;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @JsonBackReference
    private Project project;

    @Column(name = "status", nullable = false, unique = false)
    private String status;

    public Request() {}

    public Request(com.techdev.sdg.Entity.Entity requestor, Project project) {
        setRequestor(requestor);
        setProject(project);
        setStatus("Pending");
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setRequestor(com.techdev.sdg.Entity.Entity requestor) {
        this.requestor = requestor;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public com.techdev.sdg.Entity.Entity getRequestor() {
        return requestor;
    }

    public Project getProject() {
        return project;
    }

    public String getStatus() {
        return status;
    }

    public Map toMap() {
        Map<String, Object> req = new HashMap<>();

        req.put("id", id);
        req.put("requestor", requestor);
        req.put("project", project);
        req.put("status", status);

        return req;
    }
}
