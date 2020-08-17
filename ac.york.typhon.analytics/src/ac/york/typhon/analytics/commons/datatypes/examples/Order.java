package ac.york.typhon.analytics.commons.datatypes.examples;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class Order extends Entity {
	private String id;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	private Date order_date;
	
	public Date getOrder_date() {
		return this.order_date;
	}
	
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	private int totalAmount;
	
	public int getTotalAmount() {
		return this.totalAmount;
	}
	
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	private ArrayList<OrderedProduct> orderedProducts;
	
	public ArrayList<OrderedProduct> getOrderedProducts() {
		return this.orderedProducts;
	}
	
	public void setOrderedProducts(ArrayList<OrderedProduct> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}
	
	private ArrayList<User> users;
	
	public ArrayList<User> getUsers() {
		return this.users;
	}
	
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	private ArrayList<CreditCard> paidWith;
	
	public ArrayList<CreditCard> getPaidWith() {
		return this.paidWith;
	}
	
	public void setPaidWith(ArrayList<CreditCard> paidWith) {
		this.paidWith = paidWith;
	}

	public String toString() { 
		String result = "";
	
		result = " id: " + id + " order_date: " + order_date + " totalAmount: " + totalAmount;
		return result;
	}
}

