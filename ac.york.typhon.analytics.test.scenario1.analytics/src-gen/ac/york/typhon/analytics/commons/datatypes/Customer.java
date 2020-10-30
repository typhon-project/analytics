package ac.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class Customer extends Entity{
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
	private String comments;
	
	public String getComments() {
		return this.comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	private String paymentsDetails;
	
	public String getPaymentsDetails() {
		return this.paymentsDetails;
	}
	
	public void setPaymentsDetails(String paymentsDetails) {
		this.paymentsDetails = paymentsDetails;
	}
	
	private ArrayList<BasketItem> basketItems;
	
	public ArrayList<BasketItem> getBasketItems() {
		return this.basketItems;
	}
	
	public void setBasketItems(ArrayList<BasketItem> basketItems) {
		this.basketItems = basketItems;
	}
	
	private Address address;
	
	public Address getAddress() {
		return this.address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}

	public String toString() { 
		String result = "";
	
		result = "Customer(" + " id: " + id + " name: " + name + " comments: " + comments + " paymentsDetails: " + paymentsDetails + " basketItems: " + basketItems + " address: " + address + " previousvalue: " + getPreviousValue() + " uuid: " + getUUID() + ")";
		return result;
	}
}

