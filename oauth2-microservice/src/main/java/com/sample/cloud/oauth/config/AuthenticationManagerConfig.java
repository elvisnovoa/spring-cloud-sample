package com.sample.cloud.oauth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthenticationManagerConfig extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(this.dataSource).passwordEncoder(this.passwordEncoder);
	}
}
