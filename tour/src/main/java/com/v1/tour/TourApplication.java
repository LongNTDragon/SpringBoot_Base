package com.v1.tour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TourApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourApplication.class, args);
	}

}
