package entities;

import java.util.ArrayList;
import java.util.Date;

public class Order {
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

	public String toString() { 
		String result = "";
	
		result = " id: " + id + " order_date: " + order_date + " totalAmount: " + totalAmount;
		return result;
	}
}

