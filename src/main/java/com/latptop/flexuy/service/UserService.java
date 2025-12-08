package com.latptop.flexuy.service;





import com.latptop.flexuy.model.User;



public interface UserService {
	User findByEmail(String email);
}
