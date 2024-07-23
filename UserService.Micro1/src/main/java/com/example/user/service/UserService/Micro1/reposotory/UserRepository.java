package com.example.user.service.UserService.Micro1.reposotory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.service.UserService.Micro1.entities.UserServiceEntity;

public interface UserRepository extends JpaRepository<UserServiceEntity, String> {

	
	
	
}
