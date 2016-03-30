package com.sample.cloud.accounts.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.cloud.accounts.mapping.Version;
import com.sample.cloud.accounts.resource.AccountV1;
import com.sample.cloud.accounts.resource.AccountV2;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RestController
@RequestMapping // Springfox apparently needs this at type-level
@SuppressWarnings("unchecked")
public class AccountsController {

	private static final PodamFactory factory = new PodamFactoryImpl();

	@Version("1")
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public List<AccountV1> findAllV1() {
		return factory.manufacturePojo(ArrayList.class, AccountV1.class);
	}

	@Version("2")
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public List<AccountV2> findAllV2() {
		return factory.manufacturePojo(ArrayList.class, AccountV2.class);
	}

	@Version("1")
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public AccountV1 findByIdV1(@PathVariable Long id) {
		return factory.manufacturePojo(AccountV1.class);
	}

	@Version("2")
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public AccountV2 findByIdV2(@PathVariable Long id) {
		return factory.manufacturePojo(AccountV2.class);
	}
}
