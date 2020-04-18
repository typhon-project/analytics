package entities;

import java.util.ArrayList;
import java.util.Date;

public class Product {
	private String id;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	private String name;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	private String description;
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	private Category category;
	
	public Category getCategory() {
		return this.category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	private ArrayList<Review> reviews;
	
	public ArrayList<Review> getReviews() {
		return this.reviews;
	}
	
	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	public String toString() { 
		String result = "";
	
		result = " id: " + id + " name: " + name + " description: " + description;
		return result;
	}
}

