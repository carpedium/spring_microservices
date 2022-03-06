package com.learn.webservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;


@RestController
public class CircuitBreakerController {
	
	private static Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class) ;

	@GetMapping("/sample-api")
//	@Retry(name = "sample-api" , fallbackMethod = "hardcodedResponse_1")
//	@CircuitBreaker(name = "sample-api" , fallbackMethod = "hardcodedResponse_1")
	@RateLimiter( name = "default" ,  fallbackMethod = "hardcodedResponse_1")
	public String sampleApi() {
		logger.info("Sample API call recieved ") ;
		//new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class ) ;
		return "Sample API" ;
	}
	
	public String hardcodedResponse_1(Exception e) {
		return "hardcodedResponse_1";
	}
}
