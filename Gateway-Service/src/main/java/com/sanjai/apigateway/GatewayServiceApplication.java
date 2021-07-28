package com.sanjai.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;

import java.util.Arrays;
import java.util.Collections;

//@EnableZuulProxy
@SpringBootApplication
public class GatewayServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	//Also we can set this through .yml file as already set in apigateway-apigateway_service.yml NOTE: PRIORITY will be given first to the config via Java file
	@Bean
	public CorsWebFilter corsWebFilter(){
		final CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
		corsConfig.setMaxAge(86400L);
		corsConfig.setAllowedMethods(Arrays.asList("GET","POST","PUT","PATCH","DELETE"));
		corsConfig.addAllowedHeader("*");
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**",corsConfig);

		return new CorsWebFilter(source);
	}
}
