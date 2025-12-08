package com.latptop.flexuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.latptop.flexuy.model.User;



public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
	
}
