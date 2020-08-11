package com.techdev.sdg.intendedSDG;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.techdev.sdg.Project.Project;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@Entity
@Table(name = "IntendedSDG")
public class IntendedSDG implements Serializable {

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
            mappedBy = "intendedSDGs")
    @JsonBackReference
    private Set<com.techdev.sdg.Entity.Entity> entities = new HashSet<>();

    @Column(name = "project")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "intendedSDGs")
    @JsonBackReference
    private Set<Project> projects = new HashSet<>();

    public IntendedSDG() {}

    public IntendedSDG(String name) {
        setName(name);
    }

    public void setID(Long id) {this.id = id;}

    public Long getId(){return id;}

    public void setName(String name) {
        this.name = name;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public String getName() {
        return name;
    }

    public Set<com.techdev.sdg.Entity.Entity> getEntities() {
        return entities;
    }

    public void setEntities(Set<com.techdev.sdg.Entity.Entity> entities) {
        this.entities = entities;
    }

    @Override
    public String toString() {
        return "SDG: {\n" +  "\tid: " + id + ",\n" +
                "\tname: " + name + ",\n" +
                '}';
    }
}
