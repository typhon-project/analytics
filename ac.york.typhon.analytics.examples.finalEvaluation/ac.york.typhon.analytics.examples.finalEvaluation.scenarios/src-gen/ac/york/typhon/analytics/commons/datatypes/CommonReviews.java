package ac.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.*;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import org.apache.flink.api.java.functions.KeySelector;

public class CommonReviews extends Entity{
	private Integer count;
	public Integer getCount() {
		return this.count;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
	private Customer source;
	
	public Customer getSource() {
		return this.source;
	}
	
	public void setSource(Customer source) {
		this.source = source;
	}
	
	private Customer target;
	
	public Customer getTarget() {
		return this.target;
	}
	
	public void setTarget(Customer target) {
		this.target = target;
	}

	public String toString() { 
		String result = "";
	
		result = "CommonReviews(" + " count: " + count + " source: " + source + " target: " + target + " previousvalue: " + getPreviousValue() + " uuid: " + getUUID() + ")";
		return result;
	}
	
	public static KeySelector<CommonReviews, String> getKeySelector(String keyName) {
		
		return new CommonReviewsKeySelector(keyName);
	}
}

