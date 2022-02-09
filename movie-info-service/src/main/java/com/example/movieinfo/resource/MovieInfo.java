package com.example.movieinfo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.movieinfo.model.Movie;
import com.example.movieinfo.model.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieInfo {
	
	
	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId")String movieId) {
		
		//return new Movie(movieId, "Predators");
		
		MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieSummary.class);
		
		//System.out.print(movieSummary);
		
		
		return new Movie(movieId,movieSummary.getTitle(),movieSummary.getOverview());
		
	}
	

}
