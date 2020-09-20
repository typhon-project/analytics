package ac.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.*;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class WarningWeatherData extends Entity{
	private String time_start;
	
	public String getTime_start() {
		return this.time_start;
	}
	
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	private String time_end;
	
	public String getTime_end() {
		return this.time_end;
	}
	
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
	private String warningType;
	
	public String getWarningType() {
		return this.warningType;
	}
	
	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}
	private Integer severity;
	
	public Integer getSeverity() {
		return this.severity;
	}
	
	public void setSeverity(Integer severity) {
		this.severity = severity;
	}
	private Polygon area;
	
	public Polygon getArea() {
		return this.area;
	}
	
	public void setArea(Polygon area) {
		this.area = area;
	}

	public String toString() { 
		String result = "";
	
		result = "WarningWeatherData("+" time_start: " + time_start + " time_end: " + time_end + " warningType: " + warningType + " severity: " + severity + " area: " + area + " previousvalue: " + getPreviousValue() + " uuid: " + getUUID() + ")";
		return result;
	}
}

