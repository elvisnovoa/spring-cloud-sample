package com.sample.cloud.accounts.resource;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sample.cloud.accounts.serializer.AmountSerializer;

import lombok.Data;

@Data
public class Balance implements Serializable {

	private static final long serialVersionUID = 2L;

	private BalanceType type;

	@JsonSerialize(using = AmountSerializer.class)
	private BigDecimal amount;

}
