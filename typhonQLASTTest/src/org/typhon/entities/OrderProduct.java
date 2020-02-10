package org.typhon.entities;

import org.typhon.entities.Entity;

import engineering.swat.typhonql.ast.DateTime.Date;

import java.util.ArrayList;

public class OrderProduct extends Entity {

	private String id;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	private Date date;
	
	public Date getDate() {
		return this.date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	private int totalAmount;
	
	public int getTotalAmount() {
		return this.totalAmount;
	}
	
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public OrderProduct(String id, Date date, int totalAmount) {
		this.id = id;
this.date = date;
this.totalAmount = totalAmount;
	}
	
	
	private ArrayList<Product> products;
	
	public ArrayList<Product> getProducts() {
		return this.products;
	}
	
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
	private User users;
	
	public User getUsers() {
		return this.users;
	}
	
	public void setUsers(User users) {
		this.users = users;
	}
	
	private CreditCard paidWith;
	
	public CreditCard getPaidWith() {
		return this.paidWith;
	}
	
	public void setPaidWith(CreditCard paidWith) {
		this.paidWith = paidWith;
	}

}
