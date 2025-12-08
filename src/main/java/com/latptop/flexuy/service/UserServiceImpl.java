package com.latptop.flexuy.service;


import org.springframework.stereotype.Service;

import com.latptop.flexuy.model.User;
import com.latptop.flexuy.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	@Override
	public User findByEmail(String email) {
		if(userRepository.findByEmail(email)==null) System.out.println("User không tồn tại");
		else
		System.out.println("Kiểm tra User: " + userRepository.findByEmail(email));
		return userRepository.findByEmail(email);
		
	}
	
}
