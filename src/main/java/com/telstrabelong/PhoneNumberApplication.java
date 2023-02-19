package com.telstrabelong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The entry point to the PhoneNumberApplication.
 * Loads the application context via SpringRunner
 * 
 * @author Kunalan
 * @date 19-02-2023
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@PropertySource("classpath:CustomerNumberService.properties")
public class PhoneNumberApplication {
	
	private static Logger logger = LoggerFactory.getLogger("PhoneNumberApplication");
	
	public static void main(String[] args) {
		logger.info("### Loading the PhoneNumberApplication context");
		SpringApplication.run(PhoneNumberApplication.class, args);
	}
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.telstrabelong.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
	
	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder().title("API Reference").version("1.0.0")
	            .description("Specification of Customer Phone Number APIs")
	            .build();
	}
	
}
