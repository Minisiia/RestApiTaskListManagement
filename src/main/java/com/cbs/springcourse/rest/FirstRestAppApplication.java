package com.cbs.springcourse.rest;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//посилання для браузеру
//http://localhost:8080/people/1
@SpringBootApplication
public class FirstRestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstRestAppApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
