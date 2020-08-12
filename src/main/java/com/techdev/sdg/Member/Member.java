package com.techdev.sdg.Member;

//import com.techdev.sdg.Entity.NGO.NGOModel;

import javax.persistence.*;
@Entity
@Table(name = "Member")

public class Member {
    final public static String ID = "id";
    final public static String EMAIL = "email";
    final public static String PHONE = "phoneNumber";
    final public static com.techdev.sdg.Entity.Entity ENTITY = new com.techdev.sdg.Entity.Entity();
//    final public static NGOModel NGO = new NGOModel();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "ngo", referencedColumnName = "id")
//    private NGOModel ngo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "entity_id", referencedColumnName = "id")
    private com.techdev.sdg.Entity.Entity entity;

    public Member() {}

    public Member(String email, String phoneNumber) {
        setEmail(email);
        setPhoneNumber(phoneNumber);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPrivateSector(com.techdev.sdg.Entity.Entity entity) {
        this.entity = entity;
    }
//    public void setNGO(NGOModel ngo) {
//        this.ngo = ngo;
//    }

    public Long getId() { return id;}

    public String getEmail() {
    return email;
}

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public com.techdev.sdg.Entity.Entity getEntity() { return entity;}

//    public NGOModel getNGO() { return ngo;}

    @Override
    public String toString() {
        return "Member: {\n" +
                "\tid: " + id + ",\n" +
                "\temail: " + email + ",\n" +
                "\tphoneNumber: " + phoneNumber + ",\n" +
//                "\tngo: " + ngo + ",\n" +
                "\tprivateSector" + entity + ",\n" +
                '}';
    }



}
