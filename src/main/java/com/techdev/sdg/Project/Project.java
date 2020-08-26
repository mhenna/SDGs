package com.techdev.sdg.Project;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.techdev.sdg.Discussion.Discussion;
import com.techdev.sdg.Request.Request;
import com.techdev.sdg.Utils.StringListConverter;
import com.techdev.sdg.WorkLocation.WorkLocation;
import com.techdev.sdg.intendedSDG.IntendedSDG;
import com.techdev.sdg.Resource.Resource;


import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Project")
public class Project implements Serializable {

    final public static String ID = "id";
    final public static String NAME = "name";
    final public static String AIM = "aim";
    final public static String DURATION = "duration";
    final public static String PEOPLETARGETED = "peopleTargeted";
    final public static String SUBPROJECT = "subProject";
    final public static String WORKLOCATION = "workLocation";
    final public static String RESOURCE = "resource";
    final public static String INTENDEDSDG = "intendedSDG";
    final public static String Entity = "entity";
    final public static String OWNER = "owner";
    final public static String VIEWER = "viewer";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "aim", nullable = false)
    private String aim;

    @Column(name = "duration", nullable = false)
    private Long duration;

    @Column(name = "peopleTargeted", nullable = false)
    private Long peopleTargeted;

    @OneToMany(mappedBy = "parentProject")
    @JsonManagedReference
    private Set<Project> subProjects = new HashSet<>();

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Discussion> discussions = new HashSet<>();

    @ManyToOne
    @JsonBackReference
    private Project parentProject;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "project_workLocation",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "workLocation_id")})
    @JsonManagedReference
    private Set<WorkLocation> workLocations = new HashSet<>();

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "project_entitiesJoined",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "entity_id")})
    @JsonManagedReference
    private Set<com.techdev.sdg.Entity.Entity> entities = new HashSet<>();

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "project_entities_can_view",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "entity_id")})
    @JsonManagedReference
    private Set<com.techdev.sdg.Entity.Entity> viewers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "project_intendedSDG",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "intendedSDG_id")})
    @JsonManagedReference
    private Set<IntendedSDG> intendedSDGs = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "project_resource",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id")})
    @JsonManagedReference
    private Set<Resource> resources = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "projectOwner", nullable = false)
    @JsonBackReference
    private com.techdev.sdg.Entity.Entity owner;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Request> requests = new HashSet<>();

    public Project() {
    }

    public Project(String name, com.techdev.sdg.Entity.Entity owner, String aim, Long duration, Long peopleTargeted) {
        setName(name);
        setOwner(owner);
        setAim(aim);
        setDuration(duration);
        setPeopleTargeted(peopleTargeted);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(com.techdev.sdg.Entity.Entity owner) {
        this.owner = owner;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public void setViewers(Set<com.techdev.sdg.Entity.Entity> viewers) {
        this.viewers = viewers;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public void setPeopleTargeted(Long peopleTargeted) {
        this.peopleTargeted = peopleTargeted;
    }

    public void setSubProjects(Set<Project> subProjects) {
        this.subProjects = subProjects;
    }

    public void setDiscussions(Set<Discussion> discussions) {
        this.discussions = discussions;
    }

    public void setParentProject(Project parentProject) {
        this.parentProject = parentProject;
    }

    public void setWorkLocation(Set<WorkLocation> workLocations) {
        this.workLocations = workLocations;
    }

    public void setEntities(Set<com.techdev.sdg.Entity.Entity> entities) {
        this.entities = entities;
    }

    public void setIntendedSDGs(Set<IntendedSDG> intendedSDGs) {
        this.intendedSDGs = intendedSDGs;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

    public void addEntity(com.techdev.sdg.Entity.Entity entity) {
        getEntities().add(entity);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public com.techdev.sdg.Entity.Entity getOwner() {
        return owner;
    }

    public Set<com.techdev.sdg.Entity.Entity> getViewers() {
        return viewers;
    }

    public String getAim() {
        return aim;
    }

    public Long getDuration() {
        return duration;
    }

    public Long getPeopleTargeted() {
        return peopleTargeted;
    }

    public Set<Project> getSubProjects() {
        return subProjects;
    }

    public Set<Discussion> getDiscussions() {
        return discussions;
    }

    public Project getParentProject() {
        return parentProject;
    }

    public Set<WorkLocation> getWorkLocations() {
        return workLocations;
    }

    public Set<com.techdev.sdg.Entity.Entity> getEntities() {
        return entities;
    }

    public Set<IntendedSDG> getIntendedSDGs() {
        return intendedSDGs;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public Set<Request> getRequests() {
        return requests;
    }

    @Override
    public String toString() {
        return "\n\tProject: {\n" +
                "\t\tid: " + id + ",\n" +
                "\t\tname: " + name + ",\n" +
                "\t\taim: " + aim + ",\n" +
                "\t\tviewers: " + viewers + ",\n" +
                "\t\tduration: " + duration + ",\n" +
                "\t\tpeople targeted: " + peopleTargeted + ",\n" +
                "\t\tworkLocation: " + workLocations + ",\n" +
                "\t\tintendedSDGs: " + intendedSDGs + ",\n" +
                "\t\rresources: " + resources + ",\n" +
                "\t\rowner: " + owner + ",\n" +
                "\t}";
    }

    public Map toMap() {
        Map<String, Object> project = new HashMap<>();

        project.put("id", id);
        project.put("name", name);
        project.put("aim", aim);
        project.put("duration", duration);
        project.put("peopleTargeted", peopleTargeted);
        project.put("subProjects", subProjects);
        project.put("workLocations", workLocations);
        project.put("resources", resources);
        project.put("intendedSDGs", intendedSDGs);
        project.put("entities", entities);
        project.put("owner", owner);

        return project;
    }
}
