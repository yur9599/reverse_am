package com.example.reverse_am.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "member")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",length = 20)
    private String name;
    @Column(name = "sure_name",length = 25)
    private String sureName;
    @Column(name = "phone_nmbr",length = 20)
    private String phoneNumber;
    @Column(name = "email",length = 30)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "rev_coin")
    private Long revCoin;
    @JoinColumn(name = "address_id",nullable = false,foreignKey = @ForeignKey(name = "fk_user_address"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    @OneToOne(mappedBy = "user")
    private Bag bag;

    @OneToMany(mappedBy = "user")
    private final Set<Product> products = new HashSet<>();

    public User() {
    }

    public User(String name, String sureName, String phoneNumber, String email, String password,
                Long revCoin, Address address) {
        this.name = name;
        this.sureName = sureName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.revCoin = revCoin;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setRevCoin(Long revCoin) {
        this.revCoin = revCoin;
    }

    public Long getRevCoin() {
        return revCoin;
    }

}
