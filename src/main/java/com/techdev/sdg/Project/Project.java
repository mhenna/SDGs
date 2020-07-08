package com.techdev.sdg.Project;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.techdev.sdg.Discussion.Discussion;
import com.techdev.sdg.NGO.NGO;
import com.techdev.sdg.WorkLocation.WorkLocation;
import com.techdev.sdg.intendedSDG.IntendedSDG;
import com.techdev.sdg.PrivateSector.PrivateSector;
import com.techdev.sdg.Resource.Resource;

import org.hibernate.jdbc.Work;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
    final public static String PRIVATESECTOR = "privateSector";
    final public static String NGOS = "ngo";

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

    @Column(name = "privateSector")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "projects")
    @JsonBackReference
    private Set<PrivateSector> privateSectors = new HashSet<>();

    @Column(name = "ngo")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "projects")
    @JsonBackReference
    private Set<NGO> ngos = new HashSet<>();

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

    public Project() {
    }

    public Project(String name, String aim, Long duration, Long peopleTargeted) {
        setName(name);
        setAim(aim);
        setDuration(duration);
        setPeopleTargeted(peopleTargeted);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAim(String aim) {
        this.aim = aim;
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

    public void setPrivateSectors(Set<PrivateSector> privateSectors) {
        this.privateSectors = privateSectors;
    }

    public void setIntendedSDGs(Set<IntendedSDG> intendedSDGs) {
        this.intendedSDGs = intendedSDGs;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    public void addPrivateSector(PrivateSector ps) {
        getPrivateSectors().add(ps);
    }

    public void setNGOs(Set<NGO> NGOs) {
        this.ngos = NGOs;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public Set<PrivateSector> getPrivateSectors() {
        return privateSectors;
    }

    public Set<IntendedSDG> getIntendedSDGs() {
        return intendedSDGs;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public Set<NGO> getNGOs() {
        return ngos;
   }

    @Override
    public String toString() {
        return "\n\tProject: {\n" +
                "\t\tid: " + id + ",\n" +
                "\t\tname: " + name + ",\n" +
                "\t\taim: " + aim + ",\n" +
                "\t\tduration: " + duration + ",\n" +
                "\t\tpeople targeted: " + peopleTargeted + ",\n" +
                "\t\tworkLocation: " + workLocations + ",\n" +
                "\t\tintendedSDGs: " + intendedSDGs + ",\n" +
                "\t\resources: " + resources + ",\n" +
                "\t}";
    }
}
