package entities;

import java.util.ArrayList;
import java.util.Date;

public class Comment {
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
	
	private Review review;
	
	public Review getReview() {
		return this.review;
	}
	
	public void setReview(Review review) {
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

