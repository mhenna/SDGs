package com.techdev.sdg.NGO;

import com.techdev.sdg.sample.SampleModel;

import javax.persistence.*;
@Entity
@Table(name = "NGO")
public class NGOModel {

    final public static String ID = "id";
    final public static String NAME = "name";
    final public static String MAINCONTACT = "contact";
    final public static String VISION = "vision";
    final public static Boolean ISAPPROVED = false;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mainContact")
    private String mainContact;

    @Column(name = "vision")
    private String vision;

    @Column(name = "isApproved", nullable = false)
    private Boolean isApproved;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "NGO", fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id", referencedColumnName = "id")
//    private Set<Member> members;

//    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "NGO", fetch = FetchType.LAZY)
//    @JoinColumn(name = "intended_sdg_id", referencedColumnName = "id")
//    private Set<IntendedSDG> intendedSDGs;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "NGO", fetch = FetchType.LAZY)
//    @JoinColumn(name = "program_id", referencedColumnName = "id")
//    private Set<Program> programs;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "NGO", fetch = FetchType.LAZY)
//    @JoinColumn(name = "project_id", referencedColumnName = "id")
//    private Set<Project> projects;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "NGO", fetch = FetchType.LAZY)
//    @JoinColumn(name = "admin_id", referencedColumnName = "id")
//    private Set<Admin> admins;


//    @Column(name = "resource")
//    @ManyToMany(mappedBy = "NGO", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private Set<Resource> resources;

//    @Column(name = "files")
//    @ManyToMany(mappedBy = "NGO", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private Set<File> files;

    public NGOModel() {}

    public NGOModel(String name, String mainContact, String vision, Boolean isApproved) {
        setName(name);
        setMaincontact(mainContact);
        setVision(vision);
        setIsApproved(false);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaincontact(String mainContact) {
        this.mainContact = mainContact;
    }

    public void setVision(String vision) {this.vision = vision;}

    public void setIsApproved(Boolean isApproved) {this.isApproved = isApproved;}


//    public void setProjects(Set<Project> projects) {
//        this.projects = projects;
//    }
//    public void setMembers(Set<Members> members) {
//        this.members = members;
//    }
//
//    public void setAdmins(Set<Admin> admins) {
//        this.admins = admins;
//    }
//
//    public void setResources(Set<Resource> resources) {
//        this.resource = resource;
//    }
//
//    public void setPrograms(Set<Program> programs) {
//        this.programs = programs;
//    }

//    public void setIntendedSDGS(Set<IntendedSDG> intendedSDGs) {
//        this.intendedSDGs = intendedSDGs;
//    }

//    public void setFiles(Set<File> files) {
//        this.files = files;
//    }
    public String getName() {
        return name;
    }

    public String getMaincontact() {
        return mainContact;
    }

    public String getVision() { return vision; }

    public Boolean getIsApproved() {
        return isApproved;
    }

//    public void getProjects() {
//        return projects;
//    }
//    public void getMembers() {
//        return members;
//    }
//
//    public void getAdmins() {
//        return admins;
//    }
//
//    public void getResources() {
//        return resource;
//    }
//
//    public void getPrograms() {
//        return programs;
//    }
//    public void getIntendedSDGS() {
//        return intendedSDGs;
//    }
//    public void getFiles() {
//        return files;
//    }
    @Override
    public String toString() {
        return "NGO: {\n" +
                "\tid: " + id + ",\n" +
                "\tname: " + name + ",\n" +
                "\tmainContact: " + mainContact + ",\n" +
                "\tvision: " + vision + ",\n" +
                "\tisApproved: " + isApproved + ",\n" +
    //                "\tprojects: " + projects + ",\n" +
    //                "\tmembers: " + members + ",\n" +
    //                "\tadmins: " + admins + ",\n" +
    //                "\rprograms: " + programs + ",\n" +
    //                "\tresource: " + resources + ",\n" +
    //                "\tintendedSDGs" + intendedSDGs + ",\n" +
                '}';
    }


}
