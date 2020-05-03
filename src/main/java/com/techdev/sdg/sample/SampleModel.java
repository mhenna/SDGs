package com.techdev.sdg.sample;

import javax.persistence.*;

@Entity
@Table(name = "sample")
public class SampleModel {

    final public static String ID = "id";
    final public static String USERNAME = "username";
    final public static String PASSWORD = "password";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    public SampleModel() {}

    public SampleModel(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SampleModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
