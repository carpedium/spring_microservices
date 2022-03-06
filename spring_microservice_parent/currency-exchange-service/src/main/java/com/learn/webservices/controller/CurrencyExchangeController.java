package com.learn.webservices.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learn.webservices.beans.CurrencyExchange;
import com.learn.webservices.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {

	private static Logger log = LogManager.getLogger(CurrencyExchangeController.class);

	@Autowired
	private CurrencyExchangeRepository repository;

	public CurrencyExchangeController() {
		log.info(">>>  CurrencyExchangeController Initiated");

	}

	@Autowired
	private Environment environment;

	@GetMapping("/get")
	public String getCall() {
		CurrencyExchange currencyExchange = null;
		return "Hellow World !";
	}

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchange(@PathVariable String to, @PathVariable String from) {

		log.info("Kubernetes :  called retrieveExchange with from:{}, to:{} " , from, to);
		
		CurrencyExchange currencyExchange = null;
		currencyExchange = repository.findByFromAndTo(from, to);

		if (currencyExchange == null) {
			throw new RuntimeException("Could not find data for from:" + from + " and to:" + to);
		}

		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");
		String version = "v12";
		
		currencyExchange.setEnvironment(host+":"+port+"-"+version) ;
		
		
		return currencyExchange;
	}

}
