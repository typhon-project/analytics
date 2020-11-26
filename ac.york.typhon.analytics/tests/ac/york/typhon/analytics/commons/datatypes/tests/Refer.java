package ac.york.typhon.analytics.commons.datatypes.tests;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.*;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import org.apache.flink.api.java.functions.KeySelector;

public class Refer extends Entity{
	private Integer r;
	
	public Integer getR() {
		return this.r;
	}
	
	public void setR(Integer r) {
		this.r = r;
	}

	public String toString() { 
		String result = "";
	
		result = "Refer(" + " r: " + r + " previousvalue: " + getPreviousValue() + " uuid: " + getUUID() + ")";
		return result;
	}
	
	public static KeySelector<Refer, String> getKeySelector(String keyName) {
		
		return new ReferKeySelector(keyName);
	}
}

