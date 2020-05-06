package com.techdev.sdg.intendedSDG;

import com.techdev.sdg.PrivateSector.PrivateSector;
import com.techdev.sdg.NGO.NGO;

import java.io.Serializable;
import java.util.HashSet;


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

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "SDGs")
    private Set<PrivateSector> PrivateSectors = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "SDGs")
    private Set<NGO> NGOs = new HashSet<>();

    public IntendedSDG() {}

    public IntendedSDG(String name) {
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
        return "SDG: {\n" +  "\tid: " + id + ",\n" +
                "\tname: " + name + ",\n" +
                '}';
    }
}
