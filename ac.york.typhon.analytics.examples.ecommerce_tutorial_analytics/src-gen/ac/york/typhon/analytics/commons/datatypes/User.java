package ac.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.*;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class User extends Entity{
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
	
	private ArrayList<OrderProduct> orders;
	
	public ArrayList<OrderProduct> getOrders() {
		return this.orders;
	}
	
	public void setOrders(ArrayList<OrderProduct> orders) {
		this.orders = orders;
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
	
		result = "User("+" id: " + id + " name: " + name + " comments: " + comments + " paymentsDetails: " + paymentsDetails + " orders: " + orders + " address: " + address + " previousvalue: " + getPreviousValue() + " uuid: " + getUUID() + ")";
		return result;
	}
}

