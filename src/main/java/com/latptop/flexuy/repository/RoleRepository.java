package com.latptop.flexuy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.latptop.flexuy.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public List<Role> findAll();
}
