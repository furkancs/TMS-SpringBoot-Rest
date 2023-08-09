package com.kurt.springtest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kurt.springtest.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

	List<Task> findByUserId(Long userId);

}
