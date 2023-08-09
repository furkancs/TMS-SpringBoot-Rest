package com.kurt.springtest.service;

import java.util.List;
import java.util.Optional;

import com.kurt.springtest.model.Task;
import com.kurt.springtest.request.TaskCreateRequest;
import com.kurt.springtest.request.TaskUpdateRequest;

import jakarta.validation.Valid;

public interface TaskService {
	
	List<Task> getsTasks(Optional<Long> userId);

	Task getSingleTask(Long Id);

	Task saveTask(@Valid TaskCreateRequest taskCreateRequest);

	Task updateTask(Long id, TaskUpdateRequest taskUpdateRequest);

	void deleteTask(Long id);

}
