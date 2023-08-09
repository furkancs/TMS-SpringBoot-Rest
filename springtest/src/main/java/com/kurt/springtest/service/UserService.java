package com.kurt.springtest.service;

import java.util.List;

import com.kurt.springtest.model.User;

public interface UserService {
	
	List<User> getUsers();
	
	User saveUser(User user);
	
	User getSingleUser(Long id);
	
	void deleteUser(Long id);
	
	User updateUser(User user);
		
	List<User> getUsersByKeyword(String keyword);

	User getSingleUserByUsername(String username);
}
