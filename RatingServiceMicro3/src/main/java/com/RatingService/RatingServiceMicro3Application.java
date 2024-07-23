package com.RatingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class RatingServiceMicro3Application {
	
	

	public static void main(String[] args) {
		SpringApplication.run(RatingServiceMicro3Application.class, args);
	}

}
