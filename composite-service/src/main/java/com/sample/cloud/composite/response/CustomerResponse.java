package com.sample.cloud.composite.response;

import java.io.Serializable;
import java.util.List;

import com.sample.cloud.composite.CompositeApplication;

import lombok.Data;

@Data
public class CustomerResponse implements Serializable {

	private static final long serialVersionUID = CompositeApplication.APPLICATION_VERSION;

	private Long id;
	private String firstName;
	private String lastName;
	private String dateOfBirth;

	private List<Account> accounts;

	@Data
	public static class Account implements Serializable {
		private static final long serialVersionUID = CompositeApplication.APPLICATION_VERSION;
		private Long id;
		private String accountNumber;
		private String createdDate;
		private String balance;
	}
}
