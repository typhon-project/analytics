package ac.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.*;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class Product extends Entity{
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
	
	private ArrayList<OrderProduct> orders;
	
	public ArrayList<OrderProduct> getOrders() {
		return this.orders;
	}
	
	public void setOrders(ArrayList<OrderProduct> orders) {
		this.orders = orders;
	}
	
	private ArrayList<Review> review;
	
	public ArrayList<Review> getReview() {
		return this.review;
	}
	
	public void setReview(ArrayList<Review> review) {
		this.review = review;
	}

	public String toString() { 
		String result = "";
	
		result = "Product("+" id: " + id + " name: " + name + " description: " + description + " orders: " + orders + " review: " + review + " previousvalue: " + getPreviousValue() + " uuid: " + getUUID() + ")";
		return result;
	}
}

