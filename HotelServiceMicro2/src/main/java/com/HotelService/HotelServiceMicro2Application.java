package com.HotelService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HotelServiceMicro2Application {

	public static void main(String[] args) {
		SpringApplication.run(HotelServiceMicro2Application.class, args);
	}

}
