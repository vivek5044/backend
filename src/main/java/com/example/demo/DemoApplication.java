package com.example.demo;

import com.example.demo.service.H2UserLoaderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

//	@Bean
//	CommandLineRunner run(H2UserLoaderService loaderService) {
//		return args -> {
//			loaderService.loadUsersToH2();
//		};
//	}

}
