package com.example.reverse_am.controller;

import com.example.reverse_am.dto.RoleDTO;
import com.example.reverse_am.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_admin')")
    public ResponseEntity<?> createRole(@RequestBody RoleDTO role){
        Assert.notNull(role,"Role is null ");
        return ResponseEntity.ok().body(this.roleService.createRole(role));
    }

    @DeleteMapping("/{role}")
    @PreAuthorize("hasRole('ROLE_admin')")
    public ResponseEntity<?> deleteRole(@PathVariable("role") String role){
        Assert.notNull(role,"Role is null ");
        this.roleService.deleteRole(role);
        return ResponseEntity.ok().body("Role is deleted ");
    }

}
