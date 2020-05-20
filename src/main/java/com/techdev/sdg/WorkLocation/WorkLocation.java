package com.techdev.sdg.WorkLocation;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techdev.sdg.NGO.NGO;
import com.techdev.sdg.PrivateSector.PrivateSector;
import com.techdev.sdg.Project.Project;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "WorkLocation")
public class WorkLocation implements Serializable {
    final public static String ID = "id";
    final public static String AREA = "area";
    final public static String PROJECT = "project";
    final public static String PRIVATESECTORS = "privateSectors";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "area", nullable = false)
    private String area;

    @Column(name = "project")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "workLocations")
    @JsonBackReference
    private Set<Project> projects;
    @Column(name = "privateSector")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "workLocations")
    @JsonBackReference
    private Set<PrivateSector> privateSectors = new HashSet<>();

    @Column(name = "ngo")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "workLocations")
    @JsonBackReference
    private Set<NGO> ngos = new HashSet<>();

    public WorkLocation() {
    }

    public WorkLocation(String area) {
        setArea(area);
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public void setPrivateSectors(Set<PrivateSector> privateSectors) {
        this.privateSectors = privateSectors;
    }

    public void setNGOs(Set<NGO> NGOs) {
        this.ngos = NGOs;
    }

    public String getArea() {
        return area;
    }

//    public Set<Project> getProjects() {
//        return projects;
//    }

    public Set<PrivateSector> getPrivateSectors() {
        return privateSectors;
    }

    public Set<NGO> getNGOs() {
        return ngos;
    }

    public void addPrivateSector(PrivateSector ps) {
        getPrivateSectors().add(ps);
    }

    @Override
    public String toString() {
        return "WorkLocation: {\n" +
                "\tid: " + id + ",\n" +
                "\tarea: " + area + ",\n" +
                '}';
    }
}
