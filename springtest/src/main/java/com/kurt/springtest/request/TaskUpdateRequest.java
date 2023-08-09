package com.kurt.springtest.request;

import lombok.Data;

@Data
public class TaskUpdateRequest {

	private String title;
	
	private String description;
	
	private String duration;
}
