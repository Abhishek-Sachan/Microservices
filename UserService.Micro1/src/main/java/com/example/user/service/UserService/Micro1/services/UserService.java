package com.example.user.service.UserService.Micro1.services;

import java.util.List;

import com.example.user.service.UserService.Micro1.entities.UserServiceEntity;

public interface UserService {

	// save user
	UserServiceEntity saveUser(UserServiceEntity user);
	
	// get all user
	List<UserServiceEntity> getAllUser();
	
	// get single user of given id
	
	UserServiceEntity getUser(String userId);
	
	
}
