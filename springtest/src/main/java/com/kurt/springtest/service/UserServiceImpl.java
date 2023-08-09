package com.kurt.springtest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kurt.springtest.model.User;
import com.kurt.springtest.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository uRepository;
	
	@Override
	public List<User> getUsers() {
		return uRepository.findAll();
	}
	
	
	@Override
	public User saveUser(User user) {
		return uRepository.save(user);
		
	}

	@Override
	public User getSingleUser(Long id) {
		Optional<User> user  = uRepository.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		throw new RuntimeException("User does not exist for the id: "+id);
	}
	
	@Override
	public void deleteUser(Long id) {
		uRepository.deleteById(id);
	}

	@Override
	public User updateUser(User user) {
		return uRepository.save(user);
	}

	@Override
	public List<User> getUsersByKeyword(String keyword) {
		Sort sort = Sort.by(Sort.Direction.ASC, "id");
		return uRepository.findByNameContaining(keyword, sort);
	}


	@Override
	public User getSingleUserByUsername(String username) {
		return uRepository.findByUsername(username);
	}
}
