package com.latptop.flexuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.latptop.flexuy.model.User;



public interface UserRepository extends JpaRepository<User, Long>{
	public User findByEmail(String email);
	public User findByResetPasswordToken(String token);
}
