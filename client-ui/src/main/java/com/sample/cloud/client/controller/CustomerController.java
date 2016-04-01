package com.sample.cloud.client.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.sample.cloud.client.form.CustomerForm;

@Controller
public class CustomerController {

	private RestTemplate restTemplate = new RestTemplate();

	private static final String gatewayEndpoint = "http://localhost:8080/api";
	private static final String customersEndpoint = gatewayEndpoint + "/frontendcustomers-service/frontend-customers";
	private static final String compositeEndpoint = gatewayEndpoint + "/composite-service/composite";

	@RequestMapping(path = "/getCustomers", method = RequestMethod.GET)
	@ResponseBody
	public String getCustomers() {
		return this.restTemplate.getForObject(customersEndpoint + "/customers", String.class);
	}

	@RequestMapping(path = "/addCustomer", method = RequestMethod.POST)
	public String addCustomer(CustomerForm form) {
		this.restTemplate.postForObject(customersEndpoint + "/customers", form, String.class);
		return "redirect:/";
	}

	@RequestMapping(path = "/getCustomer/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CustomerForm getCustomer(@PathVariable Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("includeAccounts", true);

		return this.restTemplate.postForObject(compositeEndpoint + "/loadCustomer", params, CustomerForm.class);
	}

	@RequestMapping(path = "/editCustomer/{id}", method = RequestMethod.POST)
	public String editCustomer(@PathVariable Long id, CustomerForm form) {
		this.restTemplate.put(customersEndpoint + "/customers/" + id, form);
		return "redirect:/";
	}

	@RequestMapping(path = "/deleteCustomer/{id}", method = RequestMethod.GET)
	public String deleteCustomer(@PathVariable Long id) {
		this.restTemplate.delete(customersEndpoint + "/customers/" + id);
		return "redirect:/";
	}
}
