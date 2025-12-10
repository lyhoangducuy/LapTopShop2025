package com.latptop.flexuy.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.latptop.flexuy.model.CustomUserDetails;
import com.latptop.flexuy.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private  UserService userService;
	public CustomUserDetailsService( UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("Không tồn tại user với email: " + email);
		}

		return new CustomUserDetails(user);
	}
	

	
}
