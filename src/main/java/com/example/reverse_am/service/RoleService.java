package com.example.reverse_am.service;

import com.example.reverse_am.dto.RoleDTO;
import com.example.reverse_am.entities.Role;
import com.example.reverse_am.exceptions.DuplicateResourceException;
import com.example.reverse_am.exceptions.ResourceNotFoundException;
import com.example.reverse_am.mappers.RoleMapper;
import com.example.reverse_am.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public RoleDTO createRole(RoleDTO role){
        Optional<Role> roleDB = this.roleRepository.findRoleByRole(role.getRole());
        if (roleDB.isEmpty()){
            return roleMapper.toRoleDTO(this.roleRepository.save(roleMapper.toRole(role)));
        }
        throw new DuplicateResourceException("There is already such role ");
    }

    public void deleteRole(String role){
        Optional<Role> roleDB = this.roleRepository.findRoleByRole(role);
        if (roleDB.isEmpty()) {
            throw new ResourceNotFoundException("The role is not found ");
        }
        this.roleRepository.delete(roleDB.get());
    }

}
