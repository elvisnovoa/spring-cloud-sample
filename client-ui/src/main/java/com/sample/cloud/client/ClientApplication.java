package com.sample.cloud.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class ClientApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

}
