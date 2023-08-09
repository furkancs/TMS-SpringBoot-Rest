package com.kurt.springtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kurt.springtest.model.User;
import com.kurt.springtest.request.UserLoginRequest;
import com.kurt.springtest.request.UserRegisterRequest;
import com.kurt.springtest.security.JwtTokenProvider;
import com.kurt.springtest.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public String login(@RequestBody UserLoginRequest userLoginRequest){
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);
		return "Bearer "+jwtToken;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserRegisterRequest userRegisterRequest){
		if(uService.getSingleUserByUsername(userRegisterRequest.getUsername()) != null) {
			return new ResponseEntity<String>("Username "+ userRegisterRequest.getUsername() +" has been already taken.", HttpStatus.BAD_REQUEST);
		}
		User user = new User();
		user.setName(userRegisterRequest.getName());
		user.setAge(userRegisterRequest.getAge());
		user.setEmail(userRegisterRequest.getEmail());
		user.setLocation(userRegisterRequest.getLocation());
		user.setUsername(userRegisterRequest.getUsername());
		user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
		uService.saveUser(user);
		return new ResponseEntity<String>("User "+ user.getUsername() +" has ben added to system", HttpStatus.CREATED);
	}
}

