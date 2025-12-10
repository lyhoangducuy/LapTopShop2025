package com.latptop.flexuy.service;





import com.latptop.flexuy.exception.UsernameNotFoundException;
import com.latptop.flexuy.model.User;



public interface UserService {
	User findByEmail(String email);
	boolean createUser(User user);
	public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException;
	public User findByResetPasswordToken(String token);
	public void updatePassword(User user, String newPassword);
	public void save(User user);
}
