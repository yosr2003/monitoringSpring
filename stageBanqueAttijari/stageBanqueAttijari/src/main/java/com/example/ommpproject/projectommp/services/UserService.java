package com.example.ommpproject.projectommp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ommpproject.projectommp.models.User;
@Service
public interface UserService {

//	public User addUser(User p);
	
//	public List<User> getListByport(Long id);

	public User editUser(User p);

	public User getUserById(Long id);

	public void deleteUserById(Long id);

	public List<User> getAllUsers();

	void sendPasswordResetEmail(String email);

	void resetPassword(String token, String newPassword);
}

