package ac.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class Item extends Entity{
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
	
	private ArrayList<BasketItem> basketItems;
	
	public ArrayList<BasketItem> getBasketItems() {
		return this.basketItems;
	}
	
	public void setBasketItems(ArrayList<BasketItem> basketItems) {
		this.basketItems = basketItems;
	}
	
	private ArrayList<Feedback> feedback;
	
	public ArrayList<Feedback> getFeedback() {
		return this.feedback;
	}
	
	public void setFeedback(ArrayList<Feedback> feedback) {
		this.feedback = feedback;
	}

	public String toString() { 
		String result = "";
	
		result = "Item(" + " id: " + id + " name: " + name + " description: " + description + " basketItems: " + basketItems + " feedback: " + feedback + " previousvalue: " + getPreviousValue() + " uuid: " + getUUID() + ")";
		return result;
	}
}

