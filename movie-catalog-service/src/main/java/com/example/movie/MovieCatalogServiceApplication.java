package com.example.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;




@EnableEurekaClient
@SpringBootApplication
public class MovieCatalogServiceApplication {
	
	
	@Bean
	@LoadBalanced
	public RestTemplate getTemplate()
	{
		return new RestTemplate();  // what it will do is it will return the object or create object only once
	}
	
/*	
	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();           // another way of doing interaction using webClient
		
	}
	
*/	
	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
		
		
	}
	
	

}
