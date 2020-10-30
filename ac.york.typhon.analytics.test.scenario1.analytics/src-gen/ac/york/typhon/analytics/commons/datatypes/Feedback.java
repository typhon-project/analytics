package ac.york.typhon.analytics.commons.datatypes;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class Feedback extends Entity{
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
	
	private Item item;
	
	public Item getItem() {
		return this.item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}

	public String toString() { 
		String result = "";
	
		result = "Feedback(" + " id: " + id + " content: " + content + " item: " + item + " previousvalue: " + getPreviousValue() + " uuid: " + getUUID() + ")";
		return result;
	}
}

