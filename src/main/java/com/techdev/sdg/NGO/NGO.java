package com.techdev.sdg.NGO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.techdev.sdg.DirectionToImpact.DirectionToImpact;
import com.techdev.sdg.Project.Project;
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

@Entity
@Table(name = "ngo")
public class NGO implements Serializable {
    final public static String ID = "id";
    final public static String NAME = "name";
    final public static String MAINCONTACT = "contact";
    final public static String VISION = "vision";
    final public static String PROJECT = "project";
    final public static String EMAIL = "email";
    final public static String PASSWORD = "password";
    final public static String ISAPPROVED = "isApproved";
    final public static String INTENDEDSDG = "intendedSDG";
    final public static String WORKLOCATION = "workLocation";
    final public static String RESOURCE = "resource";
    final public static String DIRECTIONTOIMPACT = "directionToImpact";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;

    @Column(name = "mainContact", nullable = false, unique = true)
    @Email
    private String mainContact;

    @Column(name = "vision", nullable = false)
    private String vision;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "isApproved", nullable = false)
    private Boolean isApproved;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "ngo_project",
            joinColumns = {@JoinColumn(name = "ngo_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")})
    @JsonManagedReference
    private Set<Project> projects = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "ngo_intendedSDG",
            joinColumns = {@JoinColumn(name = "ngo_id")},
            inverseJoinColumns = {@JoinColumn(name = "intendedSDG_id")})
    @JsonManagedReference
    private Set<IntendedSDG> intendedSDGs = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "ngo_workLocation",
            joinColumns = {@JoinColumn(name = "ngo_id")},
            inverseJoinColumns = {@JoinColumn(name = "workLocation_id")})
    @JsonManagedReference
    private Set<WorkLocation> workLocations = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "ngo_resource",
            joinColumns = {@JoinColumn(name = "ngo_id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id")})
    @JsonManagedReference
    private Set<Resource> resources = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "ngo_directionToImpact",
            joinColumns = {@JoinColumn(name = "ngo_id")},
            inverseJoinColumns = {@JoinColumn(name = "directionToImpact_id")})
    @JsonManagedReference
    private Set<DirectionToImpact> directionToImpact = new HashSet<>();

    public NGO() {
    }

    public NGO(String name, String email, String password, String mainContact, String vision) {
        setName(name);
        setEmail(email);
        setPassword(password);
        setMaincontact(mainContact);
        setVision(vision);
        setIsApproved(false);
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

    public void setVision(String vision) {
        this.vision = vision;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public void setProjects(Set<Project> project) {
        this.projects = project;
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMainContact() {
        return mainContact;
    }

    public String getVision() {
        return vision;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public Set<IntendedSDG> getIntendedSDGs() {
        return intendedSDGs;
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

    @Override
    public String toString() {
        return "ngo: {\n" +
                "\tid: " + id + ",\n" +
                "\tname: " + name + ",\n" +
                "\temail: " + email + ",\n" +
                "\tmainContact: " + mainContact + ",\n" +
                "\tvision: " + vision + ",\n" +
                "\tisApproved: " + isApproved + ",\n" +
                "\tintendedSDGs: " + intendedSDGs + ",\n" +
                "\tprojects: " + projects + ",\n" +
                "\tworkLocation: " + workLocations + ",\n" +
                "\tresource: " + resources + ",\n" +
                "\tdirectionToImpact" + directionToImpact + ",\n" +
                '}';
    }

    public Map<String, Object> toMap() {
        Map<String, Object> ngo = new HashMap<>();
        ngo.put("id", id);
        ngo.put("name", name);
        ngo.put("email", email);
        ngo.put("mainContact", mainContact);
        ngo.put("vision", vision);
        ngo.put("isApproved", isApproved);
        ngo.put("intededSDGs", intendedSDGs);
        ngo.put("projects", projects);
        ngo.put("workLocations", workLocations);
        ngo.put("resources", resources);
        ngo.put("directionsToImpact", directionToImpact);

        return ngo;
    }
}
