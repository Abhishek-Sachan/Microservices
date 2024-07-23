package com.HotelService.Exception;

public class ResourceNotFoundException extends RuntimeException {

	
	public ResourceNotFoundException() {
		super("resouce not found");
	}
	
	
	public ResourceNotFoundException(String s) {
		super(s);
	}
	
	
}
