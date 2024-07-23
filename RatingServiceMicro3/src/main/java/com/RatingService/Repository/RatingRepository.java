package com.RatingService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.RatingService.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, String>{

	
	List<Rating> findByUserId(String userId);
	
	
	List<Rating> findByHotelId(String hotelId);
	
	
}
