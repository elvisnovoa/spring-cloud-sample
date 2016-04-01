package com.sample.cloud.composite.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sample.cloud.composite.request.CustomerRequest;
import com.sample.cloud.composite.response.CustomerResponse;
import com.sample.cloud.composite.response.CustomerResponse.Account;

@RestController
public class CompositeController {

	// Ribbon translates this to use an available instance of the service
	private static final String accountsEndpoint = "http://BACKENDACCOUNTS-SERVICE/backend-accounts/";
	private static final String customersEndpoint = "http://FRONTENDCUSTOMERS-SERVICE/frontend-customers/";

	@Autowired
	private RestTemplate restTemplate; // loadbalanced client from autoconfig

	@RequestMapping(path = "/loadCustomer", method = RequestMethod.POST)
	public CustomerResponse loadCustomer(@RequestBody CustomerRequest customerRequest) {
		Long id = customerRequest.getId();
		// Get customer by id
		CustomerResponse customer = this.restTemplate.getForObject(customersEndpoint + "/customers/" + id,
				CustomerResponse.class);

		if (customerRequest.isIncludeAccounts()) {
			// Set the Accept header to V1 for simplicity
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(new MediaType("application", "vnd.sample.account.v1+json")));
			HttpEntity<String> entity = new HttpEntity<String>(null, headers);

			// ParameterizedTypeReference because the response is a generic list
			ResponseEntity<List<Account>> responseEntity = this.restTemplate.exchange(accountsEndpoint, HttpMethod.GET,
					entity, new ParameterizedTypeReference<List<Account>>() {
					});
			customer.setAccounts(responseEntity.getBody());
		}

		return customer;
	}
}
