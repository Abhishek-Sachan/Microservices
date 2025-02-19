package com.ServiceRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryMicro4Application {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryMicro4Application.class, args);
	}

}
