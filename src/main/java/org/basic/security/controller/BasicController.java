package org.basic.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {
	
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public String status() {
		return "OK";
	}
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello";
	}
	
	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}
}
