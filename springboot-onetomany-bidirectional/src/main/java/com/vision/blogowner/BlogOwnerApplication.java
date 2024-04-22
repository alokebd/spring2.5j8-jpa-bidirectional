package com.vision.blogowner;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogOwnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogOwnerApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}



	/*
	SET foreign_key_checks = 0
	SET foreign_key_checks = 1
	*/
