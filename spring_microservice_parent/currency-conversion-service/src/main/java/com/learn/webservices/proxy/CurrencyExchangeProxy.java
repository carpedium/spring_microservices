package com.learn.webservices.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learn.webservices.bean.CurrencyConversion;

//@FeignClient(name = "currency-exchange" , url = "localhost:8000" )
//@FeignClient( name = "currency-exchange" )

// KUBERNETES CHANGE
//@FeignClient(name = "currency-exchange" , url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000" )
@FeignClient(name = "currency-exchange" , url = "${CURRENCY_EXCHANGE_URI:http://localhost}:8000" )
public interface CurrencyExchangeProxy {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchange( @PathVariable String to, @PathVariable String from ) ;
		
}
