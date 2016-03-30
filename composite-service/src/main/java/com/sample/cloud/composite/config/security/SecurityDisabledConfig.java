package com.sample.cloud.composite.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Profile("!oauth2-enabled")
@EnableWebSecurity
public class SecurityDisabledConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// Disable all security for testing
		// Please note that oauth2 security will not work with hsqldb. Switch to
		// another db before enabling oauth2.
		http.csrf().disable().httpBasic().disable();
	}
}
