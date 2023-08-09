package com.kurt.springtest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurt.springtest.model.Task;
import com.kurt.springtest.model.User;
import com.kurt.springtest.repository.TaskRepository;
import com.kurt.springtest.request.TaskCreateRequest;
import com.kurt.springtest.request.TaskUpdateRequest;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository tRepository;
	
	@Autowired
	private UserService uService;

	@Override
	public List<Task> getsTasks(Optional<Long> userId) {
		if(userId.isPresent()) {
			return tRepository.findByUserId(userId.get());
		}
		return tRepository.findAll();
	}

	@Override
	public Task getSingleTask(Long taskId) {
		Optional<Task> task  = tRepository.findById(taskId);
		if(task.isPresent()) {
			return task.get();
		}
		throw new RuntimeException("task does not exist for the id: "+taskId);
	}

	@Override
	public Task saveTask(TaskCreateRequest taskCreateRequest) {
		User user = null;
		try {
			user = uService.getSingleUser(taskCreateRequest.getUserId());
			
		}catch(RuntimeException re) {
			re.printStackTrace();
			
		}
		Task toSave = new Task();
		toSave.setId(taskCreateRequest.getId());
		toSave.setTitle(taskCreateRequest.getTitle());
		toSave.setDuration(taskCreateRequest.getDuration());
		toSave.setDescription(taskCreateRequest.getDescription());
		toSave.setUser(user);
		
		return tRepository.save(toSave);
	}

	@Override
	public Task updateTask(Long id, TaskUpdateRequest taskUpdateRequest) {
		
		Optional<Task> task = tRepository.findById(id);
		if(task.isPresent()) {
			Task toUpdate = task.get();
			toUpdate.setTitle(taskUpdateRequest.getTitle());
			toUpdate.setDescription(taskUpdateRequest.getDescription());
			toUpdate.setDuration(taskUpdateRequest.getDuration());
			tRepository.save(toUpdate);
			return toUpdate;
		}
		
		throw new RuntimeException("Task does not exist for the id: "+id);
	}

	@Override
	public void deleteTask(Long id) {
		tRepository.deleteById(id);
	}
}
