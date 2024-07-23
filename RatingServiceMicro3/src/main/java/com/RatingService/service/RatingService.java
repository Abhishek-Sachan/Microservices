package com.RatingService.service;

import java.util.List;

import com.RatingService.entity.Rating;

public interface RatingService {
	
	// create 
	
	Rating  create(Rating rating);
	
	// get all ratings
	List<Rating> getRatings();
	
	// get all ratings by userid
	List<Rating> getRatingByUserId(String userId);
	
	// get all ratings by hotelid
	
	List<Rating> getRatingByHotelId(String hotelId);

}
