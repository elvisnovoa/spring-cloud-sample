package com.sample.cloud.accounts.resource;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class AccountV2 implements Serializable {

	private static final long serialVersionUID = 2L;

	private Long id;
	private String accountNumber;
	private LocalDate createdDate;
	private List<Balance> balance;

}
