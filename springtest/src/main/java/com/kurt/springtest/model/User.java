package com.kurt.springtest.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Name field cannot be blank.")
	private String name;

	private Long age = 0L;
	
	private String location;
	
	@Column(unique=true)
	@Email(message="Email field has to be a valid email.")
	private String email;
	
	@Column(unique=true, name="user_name")
	@NotBlank(message="Username field cannot be blank.")
	private String username;
	
	@NotBlank(message="Password field cannot be blank.")
	private String password;	
	
	@JsonIgnore
	@CreationTimestamp
	@Column(name="created_at", nullable=false, updatable=false)
	private Date createdAt;

	@JsonIgnore
	@UpdateTimestamp
	@Column(name="updated_at")
	private Date updatedAt;
}	