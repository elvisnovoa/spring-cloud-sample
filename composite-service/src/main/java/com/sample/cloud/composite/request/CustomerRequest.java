package com.sample.cloud.composite.request;

import java.io.Serializable;

import com.sample.cloud.composite.CompositeApplication;

import lombok.Data;

@Data
public class CustomerRequest implements Serializable {

	private static final long serialVersionUID = CompositeApplication.APPLICATION_VERSION;

	private Long id;
	private boolean includeAccounts;

}
