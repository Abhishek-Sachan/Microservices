package com.example.user.service.UserService.Micro1.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.user.service.UserService.Micro1.entities.UserServiceEntity;

import com.example.user.service.UserService.Micro1.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	private Logger log= LoggerFactory.getLogger(UserController.class);
	
	/// create user or save user
	@PostMapping
	public ResponseEntity<UserServiceEntity> createUser(@RequestBody UserServiceEntity user){
		UserServiceEntity user1= userService.saveUser(user);
	
	return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	// single get user
	// this method is calling another microservice so here we use circuit breaker
	
	int retryCount=1;
	@GetMapping("/{userId}")
	//@CircuitBreaker(name="RATINGHOTELBREAKER",fallbackMethod = "ratinghotelfallback")
	//@Retry(name="ratinghotelservice",fallbackMethod ="ratinghotelfallback" )
	@RateLimiter(name="userRateLimiter",fallbackMethod ="ratinghotelfallback" )
	public ResponseEntity<UserServiceEntity>  getSingleUser(@PathVariable String userId){
		log.info("retry count ",retryCount);
		UserServiceEntity user=	userService.getUser(userId);
	return ResponseEntity.ok(user);
	}
	
	// creating fall back method for circuit breaker
	
	public ResponseEntity<UserServiceEntity>  ratinghotelfallback(String userId, Exception ex){
		
		log.info("fallback is executed because service is down",ex.getMessage());
		UserServiceEntity user=	UserServiceEntity.builder().email("dummy@abc")
		.name("Dummy").about("dummy user").userId("12345").build();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	
	// get all user
	@GetMapping
	public ResponseEntity<List<UserServiceEntity>> getAllUser(){
		
		List<UserServiceEntity> allUser= userService.getAllUser();
	return ResponseEntity.ok(allUser);
	
	}
	
	
}

