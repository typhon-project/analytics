package org.typhon.entities;

import org.typhon.entities.Entity;
import java.util.ArrayList;

public class Review extends Entity {

	public Review() {
	}

	public String toString() {
		return "Review:id=" + id + ":rating=" + rating;
	}

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

	public Review(String id, String content) {
		this.id = id;
		this.content = content;
	}

	private Product product;

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	private int rating;

}
