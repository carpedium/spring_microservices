package com.learn.webservices.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter {

	private Logger logger= LoggerFactory.getLogger(LoggingFilter.class);
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		logger.info("Part of the exchange request Path: "+exchange.getRequest().getPath());
		logger.info("Part of the exchange request Body: "+exchange.getRequest().getBody());
		logger.info("Part of the exchange request Headers: "+exchange.getRequest().getHeaders() );
		
		
		return chain.filter(exchange)  ;
	}

}
