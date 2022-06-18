package com.example.reverse_am.mappers;

import com.example.reverse_am.dto.RoleDTO;
import com.example.reverse_am.entities.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public Role toRole(RoleDTO roleDTO){
        return new Role(roleDTO.getRole());
    }

    public RoleDTO toRoleDTO(Role role){
        return new RoleDTO(role.getRole());
    }

}
