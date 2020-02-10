package org.typhon.entities;

import org.typhon.entities.Entity;
import java.util.ArrayList;

public class Product extends Entity {

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
	
	
	private ArrayList<Review> review;
	
	public ArrayList<Review> getReview() {
		return this.review;
	}
	
	public void setReview(ArrayList<Review> review) {
		this.review = review;
	}
	
	private ArrayList<OrderProduct> orders;
	
	public ArrayList<OrderProduct> getOrders() {
		return this.orders;
	}
	
	public void setOrders(ArrayList<OrderProduct> orders) {
		this.orders = orders;
	}

}
