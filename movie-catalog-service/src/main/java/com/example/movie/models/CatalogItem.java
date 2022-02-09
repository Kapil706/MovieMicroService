package com.example.movie.models;

public class CatalogItem {
	
	
	private String mname;
	private String mdesc;
	private int rating;
	
	
	
	
	
	public CatalogItem(String mname, String mdesc, int rating) {
		
		this.mname = mname;
		this.mdesc = mdesc;
		this.rating = rating;
	}
	
	public String getMname() {
		return mname;
	}
	public String getMdesc() {
		return mdesc;
	}
	public int getRating() {
		return rating;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public void setMdesc(String mdesc) {
		this.mdesc = mdesc;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	

}
