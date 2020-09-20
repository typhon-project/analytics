package ac.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.*;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class TextWarning extends Entity{
	private String timeStamp;
	
	public String getTimeStamp() {
		return this.timeStamp;
	}
	
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	private String warning;
	
	public String getWarning() {
		return this.warning;
	}
	
	public void setWarning(String warning) {
		this.warning = warning;
	}
	private String extractedWarning;
	
	public String getExtractedWarning() {
		return this.extractedWarning;
	}
	
	public void setExtractedWarning(String extractedWarning) {
		this.extractedWarning = extractedWarning;
	}

	public String toString() { 
		String result = "";
	
		result = "TextWarning("+" timeStamp: " + timeStamp + " warning: " + warning + " extractedWarning: " + extractedWarning + " previousvalue: " + getPreviousValue() + " uuid: " + getUUID() + ")";
		return result;
	}
}

