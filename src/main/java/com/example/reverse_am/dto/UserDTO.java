package com.example.reverse_am.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {
    @Size(min = 1,max = 25)
    @NotNull
    private String name;
    @Size(min = 1,max = 30)
    @NotNull
    private String sureName;
    @Size(min = 4,max = 25)
    @NotNull
    private String phoneNumber;
    @Email
    @Size(min = 10,max = 45)
    @NotNull
    private String email;
    @Size(min = 8,max = 45)
    @NotNull
    private String password;
    @NotNull
    private  AddressDTO address;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long revCoin;

    public UserDTO() {
    }

    public UserDTO(String name, String sureName, String phoneNumber, String email, String password, AddressDTO address, Long revCoin) {
        this.name = name;
        this.sureName = sureName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.address = address;
        this.revCoin = revCoin;
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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public Long getRevCoin() {
        return revCoin;
    }

    public void setRevCoin(Long revCoin) {
        this.revCoin = revCoin;
    }

}
