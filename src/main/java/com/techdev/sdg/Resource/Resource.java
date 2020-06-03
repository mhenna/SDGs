package com.techdev.sdg.Resource;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.techdev.sdg.NGO.NGO;
import com.techdev.sdg.PrivateSector.PrivateSector;
//import com.techdev.sdg.NGO.NGO;
import com.techdev.sdg.Project.Project;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.*;

@Entity
@Table(name = "Resource")
public class Resource implements Serializable {

    final public static String ID = "id";
    final public static String NAME = "name";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "privateSector")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "resources")
    @JsonBackReference
    private Set<PrivateSector> privateSectors = new HashSet<>();

    @Column(name = "ngo")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "resources")
    @JsonBackReference
    private Set<NGO> ngos = new HashSet<>();

    @Column(name = "project")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "resources")
    @JsonBackReference
    private Set<Project> projects = new HashSet<>();

//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            },
//            mappedBy = "Resource")
//    private Set<NGO> NGOs = new HashSet<>();

    public Resource() {
    }

    public Resource(String name) {
        setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<PrivateSector> getPrivateSectors() {
        return privateSectors;
    }

    public Set<NGO> getNGOs() {
        return ngos;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setPrivateSectors(Set<PrivateSector> privateSectors) {
        this.privateSectors = privateSectors;
    }

    public void setNGOs(Set<NGO> NGOs) {
        this.ngos = NGOs;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public void addPrivateSector(PrivateSector ps) {
        getPrivateSectors().add(ps);
    }

//    public Set<NGO> getNGOs() {
//        return NGOs;
//    }

//    public void setNGOs(Set<NGO> NGOs) {
//        this.NGOs = NGOs;
//    }

    @Override
    public String toString() {
        return "Resource: {\n" +
                "\tid: " + id + ",\n" +
                "\tname: " + name + ",\n" +
                '}';
    }
}