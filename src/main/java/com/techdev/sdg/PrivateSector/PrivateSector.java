package com.techdev.sdg.PrivateSector;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@Table(name = "PrivateSector")
public class PrivateSector implements Serializable {
    final public static String ID = "id";
    final public static String NAME = "name";
    final public static String PROJECT = "project";
    final public static String EMAIL = "email";
    final public static String PASSWORD = "password";
    final public static String ISAPPROVED = "isApproved";
    final public static String WORKLOCATION = "workLocation";
    final public static String RESOURCE = "resource";
    final public static String DIRECTIONTOIMPACT = "directionToImpact";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "isApproved", nullable = false)
    private Boolean isApproved;

//    @Column(name = "project")
//    @ManyToMany(mappedBy = "privateSector", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private Long project;
//
//    @Column(name = "workLocation")
//    @ManyToMany(mappedBy = "privateSector", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private Long workLocation;
//
//    @Column(name = "resource")
//    @ManyToMany(mappedBy = "privateSector", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private Long resource;
//
//    @Column(name = "directionToImpact")
//    @ManyToMany(mappedBy = "privateSector", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private Long directionToImpact;

    public PrivateSector() {}

    public PrivateSector(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        setIsApproved(false);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

//    public void setProject(Long project) {
//        this.project = project;
//    }
//
//    public void setWorkLocation(Long workLocation) {
//        this.workLocation = workLocation;
//    }
//
//    public void setResource(Long resource) {
//        this.resource = resource;
//    }
//
//    public void setDirectionToImpact(Long directionToImpact) {
//        this.directionToImpact = directionToImpact;
//    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

//    public Long getProject() {
//        return project;
//    }
//
//    public Long getWorkLocation() {
//        return workLocation;
//    }
//
//    public Long getResource() {
//        return resource;
//    }
//
//    public Long getDirectionToImpact() {
//        return directionToImpact;
//    }

    @Override
    public String toString() {
        return "PrivateSector: {\n" +
                "\tid: " + id + ",\n" +
                "\tname: " + name + ",\n" +
                "\temail: " + email + ",\n" +
                "\tisApproved: " + isApproved + ",\n" +
//                "\tprojects: " + project + ",\n" +
//                "\tworkLocation: " + workLocation + ",\n" +
//                "\tresource: " + resource + ",\n" +
//                "\tdirectionToImpact" + directionToImpact + ",\n" +
                '}';
    }
}
