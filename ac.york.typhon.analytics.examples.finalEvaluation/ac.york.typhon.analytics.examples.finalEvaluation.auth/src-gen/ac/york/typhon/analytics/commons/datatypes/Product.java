package ac.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.*;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import org.apache.flink.api.java.functions.KeySelector;

public class Product extends Entity{
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
	
	private ArrayList<Order> orders;
	
	public ArrayList<Order> getOrders() {
		return this.orders;
	}
	
	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
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
	
		result = "Product(" + " name: " + name + " description: " + description + " orders: " + orders + " reviews: " + reviews + " previousvalue: " + getPreviousValue() + " uuid: " + getUUID() + ")";
		return result;
	}
	
	public static KeySelector<Product, String> getKeySelector(String keyName) {
		
		return new ProductKeySelector(keyName);
	}
}

