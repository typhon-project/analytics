package org.typhon.entities;

import org.typhon.entities.Entity;
import java.util.ArrayList;

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
	public Comment(String id, String content) {
		this.id = id;
this.content = content;
	}
	
	
	private ArrayList<Comment> responses;
	
	public ArrayList<Comment> getResponses() {
		return this.responses;
	}
	
	public void setResponses(ArrayList<Comment> responses) {
		this.responses = responses;
	}

}
