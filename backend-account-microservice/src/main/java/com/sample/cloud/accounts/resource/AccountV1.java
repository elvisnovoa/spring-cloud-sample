package com.sample.cloud.accounts.resource;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sample.cloud.accounts.serializer.AmountSerializer;

import lombok.Data;

@Data
public class AccountV1 implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String accountNumber;
	private LocalDate createdDate;
	@JsonSerialize(using = AmountSerializer.class)
	private BigDecimal balance;
}
