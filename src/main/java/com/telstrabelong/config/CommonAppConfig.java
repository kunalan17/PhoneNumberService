package com.telstrabelong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Contains bean definitions for common utilities
 * 
 * @author Kunalan
 * @date 19-02-2023
 *
 */
@Configuration
public class CommonAppConfig {

	/**
	 * Bean definition to allow APIs to access cross domain
	 * 
	 * @return an instance of WebMvcConfigurer
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "DELETE");
			}
		};
	}
}
