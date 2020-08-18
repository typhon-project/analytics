package ac.york.typhon.analytics.commons.datatypes.examples;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class Review extends Entity {
	private String id;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	private String content;
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	private ArrayList<Product> product;
	
	public ArrayList<Product> getProduct() {
		return this.product;
	}
	
	public void setProduct(ArrayList<Product> product) {
		this.product = product;
	}
	
	private ArrayList<Comment> comments;
	
	public ArrayList<Comment> getComments() {
		return this.comments;
	}
	
	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
	
	private ArrayList<User> user;
	
	public ArrayList<User> getUser() {
		return this.user;
	}
	
	public void setUser(ArrayList<User> user) {
		this.user = user;
	}

	public String toString() { 
		String result = "";
	
		result = " id: " + id + " content: " + content;
		return result;
	}
}

