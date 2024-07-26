package com.dio.parking_system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

@Configuration
public class CorsConfig {

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(false); // Set to false to disable credentials
		config.addAllowedOrigin("http://127.0.0.1:5500"); // Add specific origin
		config.addAllowedOriginPattern("*://localhost:*"); // Allows all origins on localhost with any port
		config.addAllowedHeader("*"); // Allow all headers
		config.addAllowedMethod("*"); // Allow all HTTP methods
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}

