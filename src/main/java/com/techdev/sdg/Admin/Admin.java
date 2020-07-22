package com.techdev.sdg.Admin;

//import com.techdev.sdg.NGO.NGOModel;
import com.techdev.sdg.Entity.SuperEntity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@javax.persistence.Entity
@Table(name = "Admin")

public class Admin extends SuperEntity {
    final public static String ID = "id";
    final public static String EMAIL = "email";
    final public static String PASSWORD = "password";
//    final public static NGOModel NGO = new NGOModel();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "ngo", referencedColumnName = "id")
//    private NGOModel ngo;

    public Admin() {}

    public Admin(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public void setName(String name) { }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public void setNGO(NGOModel ngo) {
//        this.ngo = ngo;
//    }

    public Long getId() { return id;}

    public String getName() { return  null;}

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

//    public NGOModel getNGO() { return ngo;
    public String getType() { return this.getClass().getSimpleName(); }

    @Override
    public String toString() {
        return "Admin: {\n" +
                "\tid: " + id + ",\n" +
                "\temail: " + email + ",\n" +
//                "\tngo: " + ngo + ",\n" +
                '}';
    }
    public Map<String, Object> toMap() {
        Map<String, Object> admin = new HashMap<>();
        admin.put("id", id);
        admin.put("email", email);
        admin.put("type",getType());
        return admin;
    }

}
