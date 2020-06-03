package com.techdev.sdg.intendedSDG;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.techdev.sdg.NGO.NGO;
import com.techdev.sdg.PrivateSector.PrivateSector;
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

    @Column(name = "ngo")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "intendedSDGs")
    @JsonBackReference
    private Set<NGO> ngos = new HashSet<>();

    @Column(name = "privateSector")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "intendedSDGs")
    @JsonBackReference
    private Set<PrivateSector> privateSectors = new HashSet<>();

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

    public Set<PrivateSector> getPrivateSectors() {
        return privateSectors;
    }

    public void setPrivateSectors(Set<PrivateSector> privateSectors) {
        this.privateSectors = privateSectors;
    }

    public Set<NGO> getNGOs() {
        return ngos;
    }

    public void setNGOs(Set<NGO> NGOs) {
        this.ngos = NGOs;
    }

    @Override
    public String toString() {
        return "SDG: {\n" +  "\tid: " + id + ",\n" +
                "\tname: " + name + ",\n" +
                '}';
    }
}
