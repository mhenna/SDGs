package com.techdev.sdg.WorkLocation;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Column(name = "entity")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "workLocations")
    @JsonBackReference
    private Set<com.techdev.sdg.Entity.Entity> entities = new HashSet<>();

    public WorkLocation() {
    }

    public WorkLocation(String area) {
        setArea(area);
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setID(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public void setEntities(Set<com.techdev.sdg.Entity.Entity> entities) {
        this.entities = entities;
    }

    public String getArea() {
        return area;
    }

    public Set<com.techdev.sdg.Entity.Entity> getEntities() {
        return entities;
    }

    public void addEntity(com.techdev.sdg.Entity.Entity entity) {
        getEntities().add(entity);
    }

    @JsonIgnore
    public Set<Project> getProjects() {
        return projects;
    }

    @Override
    public String toString() {
        return "WorkLocation: {\n" +
                "\tid: " + id + ",\n" +
                "\tarea: " + area + ",\n" +
                '}';
    }
}
