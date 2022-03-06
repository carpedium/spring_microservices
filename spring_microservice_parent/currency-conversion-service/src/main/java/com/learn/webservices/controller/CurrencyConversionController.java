package com.learn.webservices.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.learn.webservices.bean.CurrencyConversion;
import com.learn.webservices.proxy.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {
	
	private static Logger log = LoggerFactory.getLogger(CurrencyConversionController.class);
	
	@Autowired
	private CurrencyExchangeProxy proxy ;

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(

			@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

		
		log.info("Kubernetes :  called calculateCurrencyConversion with from:{}, to:{}, quantiy:{}" , from, to, quantity);
		
		HashMap<String, String> uriVariableMap = new HashMap<>();
		uriVariableMap.put("to", to);
		uriVariableMap.put("from", from);

		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class,
				uriVariableMap);
		
		CurrencyConversion currencyConversion = responseEntity.getBody();

		if (responseEntity != null) {
			System.out.println("Ankit : from-" + currencyConversion.getFrom());
			System.out.println("Ankit : to-" + currencyConversion.getTo());
			System.out.println("Ankit : Quantity-" + quantity);
			System.out.println("Ankit : multiple-" + currencyConversion.getConversionMultiple() );
			System.out.println("Ankit : totalCalculatedAmount-" + quantity.multiply(currencyConversion.getConversionMultiple()) );
			System.out.println("Ankit : environment-" + currencyConversion.getEnvironment() );
		}

		currencyConversion.setQuantity(quantity);
		currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
		
		
		return currencyConversion;
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(

			@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

		log.info("Kubernetes :  called calculateCurrencyConversionFeign with from:{}, to:{}, quantiy:{}" , from, to, quantity);
		
		HashMap<String, String> uriVariableMap = new HashMap<>();
		uriVariableMap.put("to", to);
		uriVariableMap.put("from", from);

		CurrencyConversion currencyConversion = proxy.retrieveExchange(to, from) ;
		
		if (currencyConversion != null) {
			System.out.println("Ankit >> : from-" + currencyConversion.getFrom());
			System.out.println("Ankit >> : to-" + currencyConversion.getTo());
			System.out.println("Ankit >> : Quantity-" + quantity);
			System.out.println("Ankit >> : multiple-" + currencyConversion.getConversionMultiple() );
			System.out.println("Ankit >> : totalCalculatedAmount-" + quantity.multiply(currencyConversion.getConversionMultiple()) );
			
			currencyConversion.setEnvironment(currencyConversion.getEnvironment()+" Feign" ) ;
			System.out.println("Ankit >> : environment-" + currencyConversion.getEnvironment() );
		}

		currencyConversion.setQuantity(quantity);
		currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
		
		
		return currencyConversion;
	}
}
