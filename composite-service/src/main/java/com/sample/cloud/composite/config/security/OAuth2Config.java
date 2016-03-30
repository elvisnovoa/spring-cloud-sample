package com.sample.cloud.composite.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@Profile("oauth2-enabled")
public class OAuth2Config {

	@Autowired
	private DataSource dataSource;

	@Bean
	public JdbcTokenStore tokenStore() {
		return new JdbcTokenStore(this.dataSource);
	}

	@Bean
	protected AuthorizationCodeServices authorizationCodeServices() {
		return new JdbcAuthorizationCodeServices(this.dataSource);
	}
}
