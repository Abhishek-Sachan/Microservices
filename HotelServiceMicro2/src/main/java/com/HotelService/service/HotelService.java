package com.HotelService.service;

import java.util.List;

import com.HotelService.entity.Hotel;

public interface HotelService {

	
	// create 
	Hotel create(Hotel hotel);
	
	// get all hotel
	List<Hotel> getAll();
	
	// get single hotel detail
	Hotel get(String id);
}
