package com.csc340a2.CSC340a2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class Csc340a2Application {

	public static void main(String[] args) {

		SpringApplication.run(Csc340a2Application.class, args);
	}

	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello, World!";
	}
}