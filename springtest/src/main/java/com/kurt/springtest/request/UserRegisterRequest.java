package com.kurt.springtest.request;

import lombok.Data;

@Data
public class UserRegisterRequest {
	
	private String name;
	
	private Long age;
	
	private String location;
	
	private String email;
	
	private String username;
	
	private String password;	

}
