package com.sample.cloud.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class AuthServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}
}
