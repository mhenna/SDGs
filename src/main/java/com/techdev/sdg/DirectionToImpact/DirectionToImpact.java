package com.techdev.sdg.DirectionToImpact;

import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.techdev.sdg.Entity.NGO.NGO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.*;

@Entity
@Table(name = "DirectionToImpact")
public class DirectionToImpact implements Serializable {

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
            mappedBy = "directionToImpact")
    @JsonBackReference
    private Set<com.techdev.sdg.Entity.Entity> entities = new HashSet<>();

    public DirectionToImpact() {
    }

    public DirectionToImpact(String name) {
        setName(name);
    }

    public void setID(Long id) {this.id = id;}

    public Long getId(){return id;}

    public void setName(String name) {
        this.name = name;
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
        return "Direction To Impact: {\n" + "\tid: " + id + ",\n" +
                "\tname: " + name + ",\n" +
                '}';
    }
}