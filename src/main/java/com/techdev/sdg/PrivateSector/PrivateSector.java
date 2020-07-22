package com.techdev.sdg.PrivateSector;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.techdev.sdg.DirectionToImpact.DirectionToImpact;
import com.techdev.sdg.Entity.UserEntity;
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
@Table(name = "PrivateSector")
public class PrivateSector extends UserEntity implements Serializable {
    final public static String ID = "id";
    final public static String NAME = "name";
    final public static String PROJECT = "project";
    final public static String EMAIL = "email";
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
    private String password;

    @Column(name = "isApproved", nullable = false)
    private Boolean isApproved;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "privateSector_project",
            joinColumns = {@JoinColumn(name = "privateSector_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")})
    @JsonManagedReference
    private Set<Project> projects = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "privateSector_workLocation",
            joinColumns = {@JoinColumn(name = "privateSector_id")},
            inverseJoinColumns = {@JoinColumn(name = "workLocation_id")})
    @JsonManagedReference
    private Set<WorkLocation> workLocations = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "privateSector_resource",
            joinColumns = {@JoinColumn(name = "privateSector_id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id")})
    @JsonManagedReference
    private Set<Resource> resources = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "privateSector_directionToImpact",
            joinColumns = {@JoinColumn(name = "privateSector_id")},
            inverseJoinColumns = {@JoinColumn(name = "directionToImpact_id")})
    @JsonManagedReference
    private Set<DirectionToImpact> directionToImpact = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "privateSector_intendedSDG",
            joinColumns = {@JoinColumn(name = "privateSector_id")},
            inverseJoinColumns = {@JoinColumn(name = "intendedSDG_id")})
    @JsonManagedReference
    private Set<IntendedSDG> intendedSDGs = new HashSet<>();

    public PrivateSector() {
    }

    public PrivateSector(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
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

    public void setIntendedSDGs(Set<IntendedSDG> intendedSDGs) {
        this.intendedSDGs = intendedSDGs;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public Set<Project> getProjects() {
        return projects;
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

    public Set<IntendedSDG> getIntendedSDGs() { return intendedSDGs; }

    public String getType() { return this.getClass().getSimpleName(); }

    @Override
    public String toString() {
        return "PrivateSector: {\n" +
                "\tid: " + id + ",\n" +
                "\tname: " + name + ",\n" +
                "\temail: " + email + ",\n" +
                "\tisApproved: " + isApproved + ",\n" +
                "\tprojects: " + projects + ",\n" +
                "\tworkLocation: " + workLocations + ",\n" +
                "\tresource: " + resources + ",\n" +
                "\tdirectionToImpact" + directionToImpact + ",\n" +
                '}';
    }

    public Map<String, Object> toMap() {
        Map<String, Object> ps = new HashMap<>();
        ps.put("id", id);
        ps.put("name", name);
        ps.put("email", email);
        ps.put("isApproved", isApproved);
        ps.put("intendedSDGs", intendedSDGs);
        ps.put("projects", projects);
        ps.put("workLocations", workLocations);
        ps.put("resources", resources);
        ps.put("directionsToImpact", directionToImpact);
        ps.put("type",getType());
        return ps;
    }
}
