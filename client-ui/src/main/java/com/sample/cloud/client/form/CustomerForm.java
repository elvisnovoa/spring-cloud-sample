package com.sample.cloud.client.form;

import java.util.List;

import lombok.Data;

@Data
public class CustomerForm {
	private Long id;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private List<Account> accounts;

	@Data
	public static class Account {
		private Long id;
		private String accountNumber;
		private String createdDate;
		private String balance;
	}
}
