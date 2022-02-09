package com.example.movierating.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movierating.model.Rating;
import com.example.movierating.model.UserRating;

@RequestMapping("/ratings")
@RestController
public class RatingsData {
	
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("moviedId") String movieId) {
		return new Rating(movieId,4);
	}
	
	
	
	@RequestMapping("/users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		
		List<Rating> ratings = Arrays.asList(new Rating("100", 4), new Rating("200", 5));
		
		UserRating userRating = new UserRating();
		userRating.setUserRating(ratings);
		return userRating;
		
		
	}

}
