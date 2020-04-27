package entities;

import java.util.ArrayList;
import java.util.Date;

public class User {
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
	
	private ArrayList<Comment> comments;
	
	public ArrayList<Comment> getComments() {
		return this.comments;
	}
	
	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
	
	private ArrayList<CreditCard> paymentsDetails;
	
	public ArrayList<CreditCard> getPaymentsDetails() {
		return this.paymentsDetails;
	}
	
	public void setPaymentsDetails(ArrayList<CreditCard> paymentsDetails) {
		this.paymentsDetails = paymentsDetails;
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

