package org.typhon.entities;

import org.typhon.entities.Entity;
import java.util.ArrayList;
import java.util.List;

public class User extends Entity {

	public User() {
	}

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

	public User(String id, String name) {
		this.id = id;
		this.name = name;
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

	private ArrayList<OrderProduct> orders;

	public ArrayList<OrderProduct> getOrders() {
		return this.orders;
	}

	public void setOrders(ArrayList<OrderProduct> orders) {
		this.orders = orders;
	}

	private List<Review> reviews = new ArrayList<>();
	
	public void setReview(Review r) {
		reviews.add(r);
	}
	
}
