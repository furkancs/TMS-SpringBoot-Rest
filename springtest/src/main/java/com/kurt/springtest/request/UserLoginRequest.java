package com.kurt.springtest.request;

import lombok.Data;

@Data
public class UserLoginRequest {
	
	private String username;
	
	private String password;
}
