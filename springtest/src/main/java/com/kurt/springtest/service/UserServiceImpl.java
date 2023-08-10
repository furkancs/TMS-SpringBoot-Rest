package com.kurt.springtest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kurt.springtest.model.User;
import com.kurt.springtest.repository.UserRepository;
import com.kurt.springtest.spec.UserSpecifications;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository uRepository;
	
	@Override
	public List<User> getUsers(
			String findByName,
			String findByLocation,
			String findByEmail,
			String findByUsername,
			String sortBy,
			String sortOrder) {
		
		// Sort settings (default: ascending order, by name)
		Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
		
		// Specification init
		Specification<User> spec = Specification.where(null);
		
		if (findByName != null) {
            spec = spec.and(UserSpecifications.nameContains(findByName));
        }
        if (findByEmail != null) {
            spec = spec.and(UserSpecifications.emailContains(findByEmail));
        }
        if (findByLocation != null) {
            spec = spec.and(UserSpecifications.locationContains(findByLocation));
        }
        if (findByUsername != null) {
            spec = spec.and(UserSpecifications.usernameContains(findByUsername));
        }
		
        return uRepository.findAll(spec, sort);

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
