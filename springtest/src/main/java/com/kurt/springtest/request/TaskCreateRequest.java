package com.kurt.springtest.request;

import lombok.Data;

@Data
public class TaskCreateRequest {
	
	private Long id;
	
	private String title;
	
	private String description;
	
	private String duration;
	
	private Long userId;
	
}
