package com.sample.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringCloudApplication
@EnableZuulProxy
public class GatewayApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
