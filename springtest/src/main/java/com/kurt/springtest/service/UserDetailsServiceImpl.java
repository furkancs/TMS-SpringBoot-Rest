package com.kurt.springtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kurt.springtest.model.User;
import com.kurt.springtest.repository.UserRepository;
import com.kurt.springtest.security.JwtUserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository uRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = uRepository.findByUsername(username);
		return JwtUserDetails.create(user);
	}
	
	public UserDetails loadUserById(Long id) {
		User user = uRepository.findById(id).get();
		return JwtUserDetails.create(user);
	}

}
