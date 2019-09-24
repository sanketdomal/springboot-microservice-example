package org.moviecatlog.moviecatalogservice.models;

public class CatalogItem {
	
	private String name;
	private String decription;
	private int rating;
	
	public CatalogItem(String name, String decription, int rating) {
		super();
		this.name = name;
		this.decription = decription;
		this.rating = rating;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

}
