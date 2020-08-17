package ac.york.typhon.analytics.commons.datatypes.examples;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class User extends Entity {
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
	
	private Address address;
	
	public Address getAddress() {
		return this.address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	private Comment comments;
	
	public Comment getComments() {
		return this.comments;
	}
	
	public void setComments(Comment comments) {
		this.comments = comments;
	}
	
	private CreditCard paymentsDetails;
	
	public CreditCard getPaymentsDetails() {
		return this.paymentsDetails;
	}
	
	public void setPaymentsDetails(CreditCard paymentsDetails) {
		this.paymentsDetails = paymentsDetails;
	}
	
	private Order orders;
	
	public Order getOrders() {
		return this.orders;
	}
	
	public void setOrders(Order orders) {
		this.orders = orders;
	}
	
	private Review reviews;
	
	public Review getReviews() {
		return this.reviews;
	}
	
	public void setReviews(Review reviews) {
		this.reviews = reviews;
	}
	
	private Basket basket;
	
	public Basket getBasket() {
		return this.basket;
	}
	
	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	public String toString() { 
		String result = "";
	
		result = " id: " + id + " name: " + name;
		return result;
	}
}

