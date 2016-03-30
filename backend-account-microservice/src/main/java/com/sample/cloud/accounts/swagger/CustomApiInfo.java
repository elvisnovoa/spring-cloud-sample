package com.sample.cloud.accounts.swagger;

import lombok.Data;
import lombok.EqualsAndHashCode;
import springfox.documentation.service.ApiInfo;

/**
 * Swagger subclass to support configuring ApiInfo from properties file.
 *
 * @author ednovoa
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomApiInfo extends ApiInfo {
	private String version;
	private String title;
	private String description;
	private String termsOfServiceUrl;
	private CustomContact contact;
	private String license;
	private String licenseUrl;

	public CustomApiInfo() {
		super(null, null, null, null, new CustomContact(), null, null);
	}
}
