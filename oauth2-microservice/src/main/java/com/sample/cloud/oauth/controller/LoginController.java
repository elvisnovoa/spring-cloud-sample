package com.sample.cloud.oauth.controller;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController
{
	@RequestMapping( path = { "/user", "/me" }, method = RequestMethod.GET )
	public Map<String, String> user(Principal principal)
	{
		Map<String, String> map = new LinkedHashMap<>();
		map.put("name", principal.getName());
		return map;
	}

}
