package com.example.reverse_am.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddressDTO {
    @Size(min = 4,max = 56)
    @NotNull
    private String country;
    @Size(min = 1,max = 60)
    @NotNull
    private String city;

    public AddressDTO() {
    }

    public AddressDTO(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
