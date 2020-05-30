package ac.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class Comment extends Entity {
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
	
	private ArrayList<Review> review;
	
	public ArrayList<Review> getReview() {
		return this.review;
	}
	
	public void setReview(ArrayList<Review> review) {
		this.review = review;
	}
	
	private ArrayList<Comment> responses;
	
	public ArrayList<Comment> getResponses() {
		return this.responses;
	}
	
	public void setResponses(ArrayList<Comment> responses) {
		this.responses = responses;
	}

	public String toString() { 
		String result = "";
	
		result = " id: " + id + " content: " + content;
		return result;
	}
}

