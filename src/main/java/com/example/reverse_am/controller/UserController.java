package com.example.reverse_am.controller;

import com.example.reverse_am.dto.UserDTO;
import com.example.reverse_am.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id")Long id, @Valid @RequestBody UserDTO userDTO){
        Assert.notNull(userDTO,"User is null ");
        Assert.notNull(userDTO.getAddress(),"Address is null ");
        UserDTO user = this.userService.updateUser(id,userDTO);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{uId}/{pId}")
    public ResponseEntity<?> buyProduct(@PathVariable("uId")Long uId, @PathVariable("pId")Long pId){
        this.userService.buyProduct(uId,pId);
        return ResponseEntity.ok().body("Successfully done" );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id")Long id){
        this.userService.deleteUser(id);
        return ResponseEntity.ok().body("User is deleted ");
    }

}
