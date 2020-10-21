package ac.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.*;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class OrderProduct extends Entity{
	private String id;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	private String product_date;
	
	public String getProduct_date() {
		return this.product_date;
	}
	
	public void setProduct_date(String product_date) {
		this.product_date = product_date;
	}
	private Integer totalAmount;
	
	public Integer getTotalAmount() {
		return this.totalAmount;
	}
	
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
	private String paidWith;
	
	public String getPaidWith() {
		return this.paidWith;
	}
	
	public void setPaidWith(String paidWith) {
		this.paidWith = paidWith;
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

	public String toString() { 
		String result = "";
	
		result = "OrderProduct("+" id: " + id + " product_date: " + product_date + " totalAmount: " + totalAmount + " paidWith: " + paidWith + " products: " + products + " users: " + users + " previousvalue: " + getPreviousValue() + " uuid: " + getUUID() + ")";
		return result;
	}
}

