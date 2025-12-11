package com.latptop.flexuy.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.latptop.flexuy.dto.UserUpdateDTO;
import com.latptop.flexuy.exception.UsernameNotFoundException;
import com.latptop.flexuy.model.User;
import com.latptop.flexuy.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
    }
	@Override
	public User findByEmail(String email) {
		if(userRepository.findByEmail(email)==null) System.out.println("User không tồn tại");
		else
		System.out.println("Kiểm tra User: " + userRepository.findByEmail(email));
		return userRepository.findByEmail(email);
		
	}
	@Override
	public boolean createUser(User user) {
		try {
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);

			userRepository.save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException {
		User user=userRepository.findByEmail(email);
		if (user!=null){
			user.setResetPasswordToken(token);
			userRepository.save(user);
		}else{
			throw new UsernameNotFoundException("Khong ton tai email "+email);
		}
	}
	@Override
	public User findByResetPasswordToken(String resetPassWordToken) {
		return userRepository.findByResetPasswordToken(resetPassWordToken);
	}
	@Override
	public void updatePassword(User user, String newPassword) {
		String encodedPassword = passwordEncoder.encode(newPassword);
		user.setPassword(encodedPassword);
		
		user.setResetPasswordToken(null);
		userRepository.save(user);
	}
	@Override
	public void save(User user) {
		userRepository.save(user);
	}
	@Override
	public ArrayList<User> findAll() {
		
		return userRepository.findAll();
	}
	@Override
	public void deleteAllById(List<Long> userIds) {
		userRepository.deleteAllById(userIds);
	}
	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	@Override
	public void update(UserUpdateDTO userDTO) {
		User user = userRepository.findById(userDTO.getId()).orElse(null);
		if (user == null) {
			throw new IllegalArgumentException("User not found with id: " + userDTO.getId());
		}

		user.setEmail(userDTO.getEmail());
		user.setFullName(userDTO.getFullName());
		user.setAddress(userDTO.getAddress());
		user.setPhone(userDTO.getPhone());
		user.setAvatar(userDTO.getAvatar());
		// Assuming you have a method to fetch Role by its ID
		// Role role = roleService.findById(userDTO.getRoleId());
		// user.setRole(role);
		userRepository.save(user);
	}
	
}
