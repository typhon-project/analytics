package org.typhon.entities;

import org.typhon.entities.Entity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Product extends Entity {
	
	public String toString() {
		return "Name: " + name + " review: " + review;
	}
	
	public Product() {}

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
	public Product(String id, String name, String description) {
		this.id = id;
this.name = name;
this.description = description;
	}
	
	
	private ArrayList<Review> review = new ArrayList<Review>();
	
	public ArrayList<Review> getReview() {
		return this.review;
	}
	
	//FIXME: Change the tamplate to produce overloaded 
	public void setReview(ArrayList<Review> review) {
		this.review = review;
	}

	public void setReview(Review review) {
		this.review.add(review);
	}

	private ArrayList<OrderProduct> orders;
	
	public ArrayList<OrderProduct> getOrders() {
		return this.orders;
	}
	
	public void setOrders(ArrayList<OrderProduct> orders) {
		this.orders = orders;
	}

}
