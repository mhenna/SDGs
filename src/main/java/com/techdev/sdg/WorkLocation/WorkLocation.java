package com.techdev.sdg.WorkLocation;


import com.techdev.sdg.PrivateSector.PrivateSector;
import com.techdev.sdg.Project.Project;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "WorkLocation")
public class WorkLocation implements  Serializable{
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
    @OneToMany(mappedBy = "workLocation", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Project> projects;

    @Column(name = "privateSector")
    @ManyToMany(mappedBy = "workLocations", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<PrivateSector> privateSectors;


    public WorkLocation() {}

    public WorkLocation(String area, Set<Project> projects, Set<PrivateSector> privateSectors) {
        setArea(area);
        setProjects(projects);
        setPrivateSectors(privateSectors);
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
    public String getArea() {
        return area;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public Set<PrivateSector> getPrivateSectors() {
        return privateSectors;
    }

    @Override
    public String toString() {
        return "WorkLocation: {\n" +
                "\tid: " + id + ",\n" +
                "\tarea: " + area + ",\n" +
                "\tprojects: " + projects + ",\n" +
                "\tprivateSectors: " + privateSectors + ",\n" +
                '}';
    }
}
