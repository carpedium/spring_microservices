package com.learn.webservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.webservices.limitsservice.bean.Limits;
import com.learn.webservices.limitsservice.configuration.Configuration;

@RestController
public class LimitsController {
	
	@Autowired
	private Configuration configuration;
 
	@GetMapping(path = "/limits")
	public Limits retrieveLimits() {		
		
		return new Limits( configuration.getMinimum(), configuration.getMaximum());		
	}
}
