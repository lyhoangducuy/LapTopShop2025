package com.latptop.flexuy.service.role;

import java.util.List;

import org.springframework.stereotype.Service;

import com.latptop.flexuy.model.Role;
import com.latptop.flexuy.repository.RoleRepository;

@Service
public class RoleServiceImp implements RoleService {
    private final RoleRepository roleRepository;
    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
    
}
