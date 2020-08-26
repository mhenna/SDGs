package com.techdev.sdg.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.techdev.sdg.DirectionToImpact.DirectionToImpact;
import com.techdev.sdg.File.File;
import com.techdev.sdg.GeneralForum.Answer.Answer;
import com.techdev.sdg.GeneralForum.Question.Question;
import com.techdev.sdg.Project.Project;
import com.techdev.sdg.Request.Request;
import com.techdev.sdg.Resource.Resource;
import com.techdev.sdg.WorkLocation.WorkLocation;
import com.techdev.sdg.intendedSDG.IntendedSDG;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@javax.persistence.Entity
@Table(name = "Entity")
public class Entity implements Serializable {
    final public static String ID = "id";
    final public static String NAME = "name";
    final public static String PROJECT = "project";
    final public static String VISION = "vision";
    final public static String EMAIL = "email";
    final public static String MAINCONTACT = "contact";
    final public static String PASSWORD = "password";
    final public static String ISAPPROVED = "isApproved";
    final public static String WORKLOCATION = "workLocation";
    final public static String RESOURCE = "resource";
    final public static String DIRECTIONTOIMPACT = "directionToImpact";
    final public static String INTENDEDSDG = "intendedSDG";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "mainContact", nullable = false, unique = true)
    @Email
    private String mainContact;

    @Column(name = "isApproved", nullable = false)
    private Boolean isApproved;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "vision")
    private String vision;

    @Column(name = "projectsJoined")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "entities")
    @JsonBackReference
    @JsonIgnore
    private Set<Project> projectsJoined = new HashSet<>();

    @Column(name = "projectsViewable")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "viewers")
    @JsonBackReference
    @JsonIgnore
    private Set<Project> viewableProjects = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "entity_workLocation",
            joinColumns = {@JoinColumn(name = "entity_id")},
            inverseJoinColumns = {@JoinColumn(name = "workLocation_id")})
    @JsonManagedReference
    private Set<WorkLocation> workLocations = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "entity_resource",
            joinColumns = {@JoinColumn(name = "entity_id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id")})
    @JsonManagedReference
    private Set<Resource> resources = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "entity_directionToImpact",
            joinColumns = {@JoinColumn(name = "entity_id")},
            inverseJoinColumns = {@JoinColumn(name = "directionToImpact_id")})
    @JsonManagedReference
    private Set<DirectionToImpact> directionToImpact = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "entity_intendedSDG",
            joinColumns = {@JoinColumn(name = "entity_id")},
            inverseJoinColumns = {@JoinColumn(name = "intendedSDG_id")})
    @JsonManagedReference
    private Set<IntendedSDG> intendedSDGs = new HashSet<>();

    @OneToMany(mappedBy = "ngo", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<File> files = new HashSet<>();

    @OneToMany(mappedBy = "submitter", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Answer> answers = new HashSet<>();

    @OneToMany(mappedBy = "submitter", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Question> questions = new HashSet<>();

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private Set<Project> projectsCreated = new HashSet<>();

    @OneToMany(mappedBy = "requestor", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Request> requests = new HashSet<>();

    public Entity() {
    }

    public Entity(String name, String email, String password, String mainContact) {
        setName(name);
        setEmail(email);
        setPassword(password);
        setIsApproved(false);
        setMaincontact(mainContact);
        setType("PrivateSector");
    }

    public Entity(String name, String email, String password, String mainContact, String vision) {
        setName(name);
        setEmail(email);
        setPassword(password);
        setIsApproved(false);
        setMaincontact(mainContact);
        setVision(vision);
        setType("NGO");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMaincontact(String mainContact) {
        this.mainContact = mainContact;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public void setProjectsJoined(Set<Project> projects) {
        this.projectsJoined = projects;
    }

    public void setWorkLocations(Set<WorkLocation> workLocations) {
        this.workLocations = workLocations;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    public void setDirectionToImpact(Set<DirectionToImpact> directionToImpact) {
        this.directionToImpact = directionToImpact;
    }

    public void setIntendedSDGs(Set<IntendedSDG> intendedSDGs) {
        this.intendedSDGs = intendedSDGs;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }

    public void setProjectsCreated(Set<Project> projectsCreated) {
        this.projectsCreated = projectsCreated;
    }

    public void setViewableProjects(Set<Project> viewableProjects) {
        this.viewableProjects = viewableProjects;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

    public Long getId() { return id; }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public String getMainContact() {
        return mainContact;
    }

    public Set<WorkLocation> getWorkLocations() {
        return workLocations;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public Set<DirectionToImpact> getDirectionToImpact() {
        return directionToImpact;
    }

    public Set<IntendedSDG> getIntendedSDGs() {
        return intendedSDGs;
    }

    public Set<Question> getQuestions(){return questions;}

    public Set<Answer> getAnswers(){return answers;}

    public String getType() {
        return type;
    }

    public String getVision() {
        return vision;
    }

    public Set<File> getFiles() {
        return files;
    }

    @JsonIgnore
    public Set<Request> getRequests() {
        return requests;
    }

    @JsonIgnore
    public Set<Project> getProjectsCreated() {
        return projectsCreated;
    }

    @JsonIgnore
    public Set<Project> getProjectsJoined() {
        return projectsJoined;
    }

    @JsonIgnore
    public Set<Project> getViewableProjects() {
        return viewableProjects;
    }


    @Override
    public String toString() {
        return "Entity: {\n" +
                "\tid: " + id + ",\n" +
                "\tname: " + name + ",\n" +
                "\temail: " + email + ",\n" +
                "\tisApproved: " + isApproved + ",\n" +
                "\tmainContact: " + mainContact + ",\n" +
                "\tworkLocation: " + workLocations + ",\n" +
                "\tresource: " + resources + ",\n" +
                "\tdirectionToImpact" + directionToImpact + ",\n" +
                '}';
    }

    public Map<String, Object> toMap() {
        Map<String, Object> entity = new HashMap<>();
        entity.put("id", id);
        entity.put("name", name);
        entity.put("email", email);
        entity.put("isApproved", isApproved);
        entity.put("mainContact", mainContact);
        entity.put("intendedSDGs", intendedSDGs);
        entity.put("workLocations", workLocations);
        entity.put("resources", resources);
        entity.put("directionsToImpact", directionToImpact);
        entity.put("type", getType());
        entity.put("vision", vision);
        entity.put("files", files);
        entity.put("projectsJoined", projectsJoined);
        entity.put("projectsCreated", projectsCreated);

        return entity;
    }
}