package com.latptop.flexuy.service;





import java.util.ArrayList;
import java.util.List;

import com.latptop.flexuy.dto.UserUpdateDTO;
import com.latptop.flexuy.exception.UsernameNotFoundException;
import com.latptop.flexuy.model.User;



public interface UserService {
	User findByEmail(String email);
	boolean createUser(User user);
	public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException;
	public User findByResetPasswordToken(String token);
	public void updatePassword(User user, String newPassword);
	public void save(User user);
	public ArrayList<User> findAll();
	public void deleteAllById(List<Long> userIds);
	public User findById(Long id);
	public void update(UserUpdateDTO userDTO);
}
