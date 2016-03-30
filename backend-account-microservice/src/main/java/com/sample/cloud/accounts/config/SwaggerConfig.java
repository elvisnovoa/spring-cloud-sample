package com.sample.cloud.accounts.config;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import com.google.common.base.Predicate;
import com.sample.cloud.accounts.mapping.CustomMediaType;
import com.sample.cloud.accounts.swagger.CustomApiInfo;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
public class SwaggerConfig {

	/**
	 * Prototype scoped to create one for each api version.
	 *
	 * @return an ApiInfo object with values from properties file.
	 */
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@ConfigurationProperties("springfox.api.info")
	public CustomApiInfo apiInfo() {
		return new CustomApiInfo();
	}

	/**
	 * Common method for configuring a Springfox Docket object.
	 *
	 * @param versionNumber
	 *            - the API version for this group.
	 * @param versionTypes
	 *            - the MediaTypes included in this group.
	 * @return a Docket instance.
	 */
	private Docket group(String versionNumber, MediaType[] versionTypes) {
		CustomApiInfo apiInfo = this.apiInfo();
		apiInfo.setVersion(versionNumber);
		// @formatter:off
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
				.apis(this.withMediaType(versionTypes))
				.paths(PathSelectors.any())
				.build()
			.directModelSubstitute(LocalDate.class, String.class) // substitutes LocalDate with String when rendering model properties
			.apiInfo(apiInfo)
			.produces(this.mediaTypesToStringSet(versionTypes)).groupName(versionNumber + " Group");
		// @formatter:on
	}

	/**
	 *
	 * @return Security config for OAuth2 authorization.
	 */
	@Bean
	public SecurityConfiguration security() {
		// @formatter:off
		return new SecurityConfiguration(
				null,
				null,
				null,
				null,
				null,
				ApiKeyVehicle.HEADER,
				"Authorization",
				",");
		// @formatter:on
	}

	@Bean
	public Docket v1Group() {
		return this.group("V1", new MediaType[] { CustomMediaType.APPLICATION_API_V1 });
	}

	@Bean
	public Docket v2Group() {
		return this.group("V2", new MediaType[] { CustomMediaType.APPLICATION_API_V2 });
	}

	/**
	 * A helper method to create a delegate callback class that maps the value
	 * of a RequestMapping's 'produces' value to the specified mediaType. If a
	 * controller method contains one of the specified MediaTypes, the delegate
	 * returns true and the method is added to the Swagger group.
	 *
	 * @param mediaTypes
	 *            - array of accepted MediaTypes.
	 * @return the delegate instance.
	 */
	private Predicate<RequestHandler> withMediaType(final MediaType[] mediaTypes) {
		return input -> {
			if (mediaTypes != null) {
				ProducesRequestCondition producesCondition = input.getRequestMapping().getProducesCondition();
				RequestCondition<?> customCondition = input.getRequestMapping().getCustomCondition();

				ProducesRequestCondition combinedCondition = producesCondition;
				if (customCondition != null && customCondition instanceof ProducesRequestCondition)
					combinedCondition = producesCondition.combine((ProducesRequestCondition) customCondition);

				for (MediaType mt : combinedCondition.getProducibleMediaTypes()) {
					for (MediaType mediaType : mediaTypes) {
						if (mt.equals(mediaType))
							return true;
					}
				}
			}
			return false;
		};
	}

	/**
	 * Helper method to convert the MediaType array into a Set of Strings.
	 *
	 * @param mediaTypes
	 *            - the media types
	 * @return a Set of Strings representing MediaTypes.
	 */
	private Set<String> mediaTypesToStringSet(MediaType[] mediaTypes) {
		Set<String> mediaTypesSet = new HashSet<String>();
		if (mediaTypes != null) {
			for (MediaType mediaType : mediaTypes)
				mediaTypesSet.add(mediaType.toString());
		}
		return mediaTypesSet;
	}
}
