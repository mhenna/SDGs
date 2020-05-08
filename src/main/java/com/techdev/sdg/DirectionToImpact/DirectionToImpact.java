package com.techdev.sdg.DirectionToImpact;

import com.techdev.sdg.PrivateSector.PrivateSector;
import com.techdev.sdg.NGO.NGO;

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

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "DirectionsToImpact")
    private Set<PrivateSector> PrivateSectors = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "DirectionsToImpact")
    private Set<NGO> NGOs = new HashSet<>();

    public DirectionToImpact() {}

    public DirectionToImpact(String name) {
        setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<PrivateSector> getPrivateSectors() {
        return PrivateSectors;
    }

    public void setPrivateSectors(Set<PrivateSector> PrivateSectors) {
        this.PrivateSectors = PrivateSectors;
    }

    public Set<NGO> getNGOs() {
        return NGOs;
    }

    public void setNGOs(Set<NGO> NGOs) {
        this.NGOs = NGOs;
    }

    @Override
    public String toString() {
        return "Direction To Impact: {\n" +  "\tid: " + id + ",\n" +
                "\tname: " + name + ",\n" +
                '}';
    }
}