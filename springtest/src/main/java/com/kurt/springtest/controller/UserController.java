package com.kurt.springtest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kurt.springtest.model.User;
import com.kurt.springtest.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService uService;
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		return new ResponseEntity<List<User>>(uService.getUsers(), HttpStatus.OK) ;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		return new ResponseEntity<User>(uService.getSingleUser(id), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		return new ResponseEntity<User>(uService.saveUser(user), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
		user.setId(id);
		return new ResponseEntity<User>(uService.updateUser(user), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<HttpStatus> deleteUser(@RequestParam Long id) {
		uService.deleteUser(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@GetMapping("/filterByKeyword")
	public ResponseEntity<List<User>> getUsersByKeyword(@RequestParam String keyword){
		return new ResponseEntity<List<User>>(uService.getUsersByKeyword(keyword), HttpStatus.OK); 
	}
	
}
