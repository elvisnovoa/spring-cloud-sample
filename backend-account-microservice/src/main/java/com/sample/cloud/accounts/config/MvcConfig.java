package com.sample.cloud.accounts.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.sample.cloud.accounts.mapping.CustomRequestMappingHandler;

@Configuration
public class MvcConfig extends DelegatingWebMvcConfiguration {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Handler for Swagger resources
		registry.addResourceHandler("/documentation/**").addResourceLocations("classpath:/META-INF/resources/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// "Move" Swagger UI to /documentation path to prevent conflicts with
		// resources on web application root.
		registry.addRedirectViewController("/documentation/docs", "/docs").setKeepQueryParams(true);
		registry.addRedirectViewController("/documentation/configuration/ui", "/configuration/ui");
		registry.addRedirectViewController("/documentation/configuration/security", "/configuration/security");
		registry.addRedirectViewController("/documentation/swagger-resources", "/swagger-resources");
		registry.addRedirectViewController("/documentation", "/documentation/swagger-ui.html");
		registry.addRedirectViewController("/documentation/", "/documentation/swagger-ui.html");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		// Enable Java 8 LocalDate formatting
		builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
	}

	@Bean
	@Order(0)
	@Override
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		// Custom handler to account for @Version
		CustomRequestMappingHandler handlerMapping = new CustomRequestMappingHandler();
		handlerMapping.setOrder(0);
		handlerMapping.setInterceptors(this.getInterceptors());
		handlerMapping.setContentNegotiationManager(this.mvcContentNegotiationManager());
		handlerMapping.setCorsConfigurations(this.getCorsConfigurations());

		PathMatchConfigurer configurer = this.getPathMatchConfigurer();
		if (configurer.isUseSuffixPatternMatch() != null) {
			handlerMapping.setUseSuffixPatternMatch(configurer.isUseSuffixPatternMatch());
		}
		if (configurer.isUseRegisteredSuffixPatternMatch() != null) {
			handlerMapping.setUseRegisteredSuffixPatternMatch(configurer.isUseRegisteredSuffixPatternMatch());
		}
		if (configurer.isUseTrailingSlashMatch() != null) {
			handlerMapping.setUseTrailingSlashMatch(configurer.isUseTrailingSlashMatch());
		}
		if (configurer.getPathMatcher() != null) {
			handlerMapping.setPathMatcher(configurer.getPathMatcher());
		}
		if (configurer.getUrlPathHelper() != null) {
			handlerMapping.setUrlPathHelper(configurer.getUrlPathHelper());
		}

		return handlerMapping;
	}
}
