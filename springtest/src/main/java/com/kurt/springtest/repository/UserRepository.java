package com.kurt.springtest.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kurt.springtest.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	List<User> findByName(String name);
		
	List<User> findByNameContaining(String keyword, Sort sort);

	User findByUsername(String username);

	List<User> findAll(Specification<User> spec, Sort sort);
}
