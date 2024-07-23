package com.example.user.service.UserService.Micro1.implservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.user.service.UserService.Micro1.entities.Hotel;
import com.example.user.service.UserService.Micro1.entities.Rating;
import com.example.user.service.UserService.Micro1.entities.UserServiceEntity;
import com.example.user.service.UserService.Micro1.exception.ResourceNotFoundException;
import com.example.user.service.UserService.Micro1.external.services.HotelService;
import com.example.user.service.UserService.Micro1.reposotory.UserRepository;
import com.example.user.service.UserService.Micro1.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger= org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public UserServiceEntity saveUser(UserServiceEntity user) {
		// TODO Auto-generated method stub
		String randomUserId= UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<UserServiceEntity> getAllUser() {
		// TODO Auto-generated method stub
		List<UserServiceEntity> users= userRepository.findAll();
	for(int i=0;i<users.size();i++) {
		UserServiceEntity user= users.get(i);
		ArrayList obj=restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), ArrayList.class);
		logger.info("object",obj);
		user.setRatings(obj);
	}
		
	return users;
	}

	@Override
	public UserServiceEntity getUser(String userId) {
		// TODO Auto-generated method stub
		// get single user data from database
		UserServiceEntity  user= userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id is not found on server "));
	
		// fetch rating of above user from rating service
		//   http://localhost:8083/ratings/users/e2d8180c-f30e-4539-a7e7-aca968d5e439
		
		Rating[] obj=restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), Rating[].class);
		logger.info("object",obj);
		
		List<Rating>  ratings=Arrays.stream(obj).toList();
		
		List<Rating> ratingList=ratings.stream().map(rating -> { 
			
			
			// api call to hotel service to get hotel
			///  url:  http://HOTELSERVICEMICRO2/hotels/ca7d32a0-2d56-4b9b-94ea-951960f7aff0
//			// this is template method calling another api
		
			// ResponseEntity<Hotel> hotel=restTemplate.getForEntity("http://localhost:8082/hotels/"+rating.getHotelId(), Hotel.class);
//			Hotel hotel1= hotel.getBody();
			
			
			// this is feign client method to call another service api
			Hotel hotel1= hotelService.getHotel(rating.getHotelId());
			
			// set hotel to rating
			
			rating.setHotel(hotel1); 
			// return rating
			return rating;
			
		}).collect(Collectors.toList());
		
		user.setRatings(ratings);
		return user;
	}

}
