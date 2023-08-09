package com.kurt.springtest.controller;

import java.util.List;
import java.util.Optional;

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

import com.kurt.springtest.model.Task;
import com.kurt.springtest.request.TaskCreateRequest;
import com.kurt.springtest.request.TaskUpdateRequest;
import com.kurt.springtest.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskService tService;
	
	@GetMapping
	public ResponseEntity<List<Task>> getTasks(@RequestParam Optional<Long> userId) {
		return new ResponseEntity<List<Task>>(tService.getsTasks(userId), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTask(@PathVariable Long id){
		return new ResponseEntity<Task>(tService.getSingleTask(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Task> saveTask(@RequestBody TaskCreateRequest taskCreateRequest){
		return new ResponseEntity<Task>(tService.saveTask(taskCreateRequest), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskUpdateRequest taskUpdateRequest){
		return new ResponseEntity<Task>(tService.updateTask(id, taskUpdateRequest), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteTask(@PathVariable Long id){
		tService.deleteTask(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
