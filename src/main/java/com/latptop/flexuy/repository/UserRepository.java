package com.latptop.flexuy.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.latptop.flexuy.model.User;



public interface UserRepository extends JpaRepository<User, Long>{
	public User findByEmail(String email);
	public User findByResetPasswordToken(String token);
	public ArrayList<User> findAll();
}
