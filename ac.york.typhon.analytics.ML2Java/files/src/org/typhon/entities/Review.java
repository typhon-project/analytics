package entities;

import java.util.ArrayList;
import java.util.Date;

public class Review {
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
	
	private Product product;
	
	public Product getProduct() {
		return this.product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	private ArrayList<Comment> comments;
	
	public ArrayList<Comment> getComments() {
		return this.comments;
	}
	
	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
	
	private User user;
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public String toString() { 
		String result = "";
	
		result = " id: " + id + " content: " + content;
		return result;
	}
}

