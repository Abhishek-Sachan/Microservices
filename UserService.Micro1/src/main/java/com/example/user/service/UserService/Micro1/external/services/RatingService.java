package com.example.user.service.UserService.Micro1.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.user.service.UserService.Micro1.entities.Rating;

@FeignClient(name="RATINGSERVICEMICRO3")
public interface RatingService {
	
	// get method
	
	
	
	// post method means create
	// you can use this method anywhere and set rating data using builder and set all value then build()
	@PostMapping("/ratings")
	 Rating createRating(Rating value) ;
	
	
	// put method, means update
	// but i have not made method for update in rating service
	// so you can make and use
	@PutMapping("/ratings/{ratingId}")
	public Rating updateRating(@PathVariable String ratingId,Rating value) ;

}
