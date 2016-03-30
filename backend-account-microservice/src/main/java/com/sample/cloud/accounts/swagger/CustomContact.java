package com.sample.cloud.accounts.swagger;

import lombok.Data;
import lombok.EqualsAndHashCode;
import springfox.documentation.service.Contact;

/**
 * Swagger subclass to support configuring Contact from properties file.
 *
 * @author ednovoa
 *		
 */
@Data
@EqualsAndHashCode( callSuper = true )
public class CustomContact extends Contact
{
	private String name;
	private String url;
	private String email;

	public CustomContact()
	{
		super(null, null, null);
	}
}
