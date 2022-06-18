package com.example.reverse_am.controller;

import com.example.reverse_am.configuration.AppUser;
import com.example.reverse_am.configuration.LoggedInUser;
import com.example.reverse_am.dto.UserDTO;
import com.example.reverse_am.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(this.userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO){
        Assert.notNull(userDTO,"User is null ");
        Assert.notNull(userDTO.getAddress(),"Address is null ");
        UserDTO user = this.userService.createUser(userDTO);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@LoggedInUser AppUser appUser, @Valid @RequestBody UserDTO userDTO){
        Assert.notNull(userDTO,"User is null ");
        Assert.notNull(userDTO.getAddress(),"Address is null ");
        UserDTO user = this.userService.updateUser(appUser.getUser(), userDTO);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{productId}")
    @PreAuthorize("hasRole('ROLE_user')")
    public ResponseEntity<?> buyProduct(@LoggedInUser AppUser appUser, @PathVariable("productId")Long pId){
        this.userService.buyProduct(appUser.getUser(), pId);
        return ResponseEntity.ok().body("Successfully done" );
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_user')")
    public ResponseEntity<?> deleteUser(@LoggedInUser AppUser appUser){
        this.userService.deleteUser(appUser.getUser());
        return ResponseEntity.ok().body("User is deleted ");
    }

}
