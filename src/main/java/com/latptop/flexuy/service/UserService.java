package com.latptop.flexuy.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.latptop.flexuy.model.User;
import com.latptop.flexuy.repository.UserRepository;



public interface UserService {
	public User findByEmail(String email);
}
