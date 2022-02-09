package com.example.movie.models;

public class Rating {

	private String mid;
	private int rating;
	
	
	
	
	
	public Rating() {
		super();
	}



	public Rating(String mid, int rating) {
	
		this.mid = mid;
		this.rating = rating;
	}
	
	
	
	public String getMid() {
		return mid;
	}
	public int getRating() {
		return rating;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	
	
}
