package com.example.movie.resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.example.movie.models.CatalogItem;
import com.example.movie.models.Movie;

import com.example.movie.models.UserRating;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;






@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    
	private static String CATALOG_SERVICE="orderService";
	
	@Autowired
	private RestTemplate restTemplate;
	
	/*
	@Autowired
	private WebClient.Builder webClientBuilder;
	*/
	
	@CircuitBreaker(name="CATALOG_SERVICE", fallbackMethod="getCatalogfallback")
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId")String userId){
		
		
		/*
		RestTemplate restTemplate = new RestTemplate(); // used for communication between two or more microservices
		// but this is wrong way ... it will create new instance of restTemplate everytime the API calls are make to this end point
		// better to use dependency injection
		*/
		
		/* Well RestTemplate is one way of interacting with other microservice
		 * other way is the WebClient through which is type of asynchronous calls  */
		
		/*List<Rating> ratings = Arrays.asList(new Rating("1234",4) , new Rating("1245",5));  */
		
		/* need to use loadBalanced service discovery Here */
		
		/*"http://localhost:8083/ratings/users/" --> "http://nameofservice/ratings/users/"
		 * nameofservice is written in application.properties
		 * but this nameofservice is registered with eureka server ..
		 * this catalog service is eurekaclient for eureka server
		 * */
		
		UserRating userRating = restTemplate.getForObject("http://movie-ratings-service/ratings/users/"+userId, UserRating.class);
		
		return userRating.getUserRating().stream().map(rating->{
			
		// -> synchoronus call
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMid(), Movie.class);  // communication between the microservices
		
	  // -> 
	/*		
			Movie movie = webClientBuilder.build()
			                .get()
			                .uri("http://localhost:8082/movies/"+rating.getMid())
			                .retrieve()
			                .bodyToMono(Movie.class)
			                .block();
			*/
			return new CatalogItem(movie.getName(),movie.getDescription(),rating.getRating());
			
			
			
		}).collect(Collectors.toList());
		
		
		
		
	}
	
	/*  So What happens now Fault Tolerance and resilence , if we have requests coming for microservice 
	 * -> If that microservice stopped working --> solution is run multiple instances of that service
	 * -> If that microservice is slow due to call of external microservice 
	 * --> there can be multiple solution exists 
	 * 1. Timeout when the service is slow Throw error, but it's bad solution
	 * 2. Circuit breaker pattern --> in this solution when there are zillion of requests coming to a service 
	 * what we do is 1. set Timeout for the service 2. Break the Service and don't send request to service for sleep time
	 * 3. After sleep time send requests again if again same happens .. 
	 * 4. Return a default fallback to the client
	 * --> this all is done under concurrency programming 
	 * --> to save us Netflix openSourced the Hystrix framework 
	 * Hystrix does this all for us.
	 * 
	 * Hystrix is deprecated from new spring cloud
	 * now we have to use Resilience4j
	 * 
	 *
	 *
	 *
	 * */
	
	
	public List<CatalogItem> getCatalogfallback(Exception e){
		return Arrays.asList(new CatalogItem("fallback Movie","Movie comes under fallback",2));
	}
	
	
	
	
	
}
