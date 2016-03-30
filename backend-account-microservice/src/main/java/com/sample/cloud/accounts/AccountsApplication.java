package com.sample.cloud.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class AccountsApplication extends SpringBootServletInitializer {

	public static final Long APPLICATION_VERSION = 001L; // version 0.0.1

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}
}
