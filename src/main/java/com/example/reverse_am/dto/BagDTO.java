package com.example.reverse_am.dto;

import javax.validation.constraints.NotNull;

public class BagDTO {

    @NotNull
    private UserDTO user;

    public BagDTO() {
    }

    public BagDTO(UserDTO user) {
        this.user = user;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

}
