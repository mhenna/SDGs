package com.techdev.sdg.Resource;

import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.techdev.sdg.Entity.NGO.NGO;
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

    @Column(name = "entity")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "resources")
    @JsonBackReference
    private Set<com.techdev.sdg.Entity.Entity> entities = new HashSet<>();

    @Column(name = "project")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "resources")
    @JsonBackReference
    private Set<Project> projects = new HashSet<>();

    public Resource() {
    }

    public Resource(String name) {
        setName(name);
    }

    public void setID(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<com.techdev.sdg.Entity.Entity> getEntities() {
        return entities;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setEntities(Set<com.techdev.sdg.Entity.Entity> entities) {
        this.entities = entities;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public void addEntity(com.techdev.sdg.Entity.Entity entity) {
        getEntities().add(entity);
    }

    @Override
    public String toString() {
        return "Resource: {\n" +
                "\tid: " + id + ",\n" +
                "\tname: " + name + ",\n" +
                '}';
    }
}