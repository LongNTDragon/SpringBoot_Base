package com.v1.leadservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class LeadserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeadserviceApplication.class, args);
	}

}
