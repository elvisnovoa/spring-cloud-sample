package com.sample.cloud.accounts.mapping;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;

import org.springframework.http.MediaType;

import com.sample.cloud.accounts.AccountsApplication;

/**
 * Media Types for handling multiple versions of the API.
 * 
 * @author ednovoa
 *
 */
public class CustomMediaType extends MediaType {

	private static final long serialVersionUID = AccountsApplication.APPLICATION_VERSION;

	public static final MediaType APPLICATION_API_V1;
	public static final String APPLICATION_API_V1_VALUE = "application/vnd.sample.account.v1+json";
	public static final MediaType APPLICATION_API_V2;
	public static final String APPLICATION_API_V2_VALUE = "application/vnd.sample.account.v2+json";

	static {
		APPLICATION_API_V1 = valueOf(APPLICATION_API_V1_VALUE);
		APPLICATION_API_V2 = valueOf(APPLICATION_API_V2_VALUE);
	}

	public CustomMediaType(String type) {
		super(type);
	}

	public CustomMediaType(String type, String subtype) {
		super(type, subtype);
	}

	public CustomMediaType(String type, String subtype, Charset charset) {
		super(type, subtype, charset);
	}

	public CustomMediaType(String type, String subtype, double qualityValue) {
		this(type, subtype, Collections.singletonMap("q", Double.toString(qualityValue)));
	}

	public CustomMediaType(MediaType other, Map<String, String> parameters) {
		super(other.getType(), other.getSubtype(), parameters);
	}

	public CustomMediaType(String type, String subtype, Map<String, String> parameters) {
		super(type, subtype, parameters);
	}
}
