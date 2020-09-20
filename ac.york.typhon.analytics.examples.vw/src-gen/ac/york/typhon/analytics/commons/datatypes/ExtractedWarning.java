package ac.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.*;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class ExtractedWarning extends Entity{
	private String ORGANIZATION;
	
	public String getORGANIZATION() {
		return this.ORGANIZATION;
	}
	
	public void setORGANIZATION(String ORGANIZATION) {
		this.ORGANIZATION = ORGANIZATION;
	}
	private String WEATHER_EVENT;
	
	public String getWEATHER_EVENT() {
		return this.WEATHER_EVENT;
	}
	
	public void setWEATHER_EVENT(String WEATHER_EVENT) {
		this.WEATHER_EVENT = WEATHER_EVENT;
	}
	private Double WEATHER_EVENT_INTENSITY;
	
	public Double getWEATHER_EVENT_INTENSITY() {
		return this.WEATHER_EVENT_INTENSITY;
	}
	
	public void setWEATHER_EVENT_INTENSITY(Double WEATHER_EVENT_INTENSITY) {
		this.WEATHER_EVENT_INTENSITY = WEATHER_EVENT_INTENSITY;
	}
	private Integer WEATHER_EVENT_COUNT;
	
	public Integer getWEATHER_EVENT_COUNT() {
		return this.WEATHER_EVENT_COUNT;
	}
	
	public void setWEATHER_EVENT_COUNT(Integer WEATHER_EVENT_COUNT) {
		this.WEATHER_EVENT_COUNT = WEATHER_EVENT_COUNT;
	}
	private Point LOCATION;
	
	public Point getLOCATION() {
		return this.LOCATION;
	}
	
	public void setLOCATION(Point LOCATION) {
		this.LOCATION = LOCATION;
	}
	private Integer WARNING_LEVEL;
	
	public Integer getWARNING_LEVEL() {
		return this.WARNING_LEVEL;
	}
	
	public void setWARNING_LEVEL(Integer WARNING_LEVEL) {
		this.WARNING_LEVEL = WARNING_LEVEL;
	}
	private String DAY;
	
	public String getDAY() {
		return this.DAY;
	}
	
	public void setDAY(String DAY) {
		this.DAY = DAY;
	}
	private String DATE;
	
	public String getDATE() {
		return this.DATE;
	}
	
	public void setDATE(String DATE) {
		this.DATE = DATE;
	}
	private String TIME;
	
	public String getTIME() {
		return this.TIME;
	}
	
	public void setTIME(String TIME) {
		this.TIME = TIME;
	}
	private String DISTANCE;
	
	public String getDISTANCE() {
		return this.DISTANCE;
	}
	
	public void setDISTANCE(String DISTANCE) {
		this.DISTANCE = DISTANCE;
	}
	private String DIRECTION;
	
	public String getDIRECTION() {
		return this.DIRECTION;
	}
	
	public void setDIRECTION(String DIRECTION) {
		this.DIRECTION = DIRECTION;
	}
	private Double SPEED;
	
	public Double getSPEED() {
		return this.SPEED;
	}
	
	public void setSPEED(Double SPEED) {
		this.SPEED = SPEED;
	}
	private String SIZE;
	
	public String getSIZE() {
		return this.SIZE;
	}
	
	public void setSIZE(String SIZE) {
		this.SIZE = SIZE;
	}
	private Double TEMPERATURE;
	
	public Double getTEMPERATURE() {
		return this.TEMPERATURE;
	}
	
	public void setTEMPERATURE(Double TEMPERATURE) {
		this.TEMPERATURE = TEMPERATURE;
	}
	private Integer NUMBER;
	
	public Integer getNUMBER() {
		return this.NUMBER;
	}
	
	public void setNUMBER(Integer NUMBER) {
		this.NUMBER = NUMBER;
	}

	public String toString() { 
		String result = "";
	
		result = "ExtractedWarning("+" ORGANIZATION: " + ORGANIZATION + " WEATHER_EVENT: " + WEATHER_EVENT + " WEATHER_EVENT_INTENSITY: " + WEATHER_EVENT_INTENSITY + " WEATHER_EVENT_COUNT: " + WEATHER_EVENT_COUNT + " LOCATION: " + LOCATION + " WARNING_LEVEL: " + WARNING_LEVEL + " DAY: " + DAY + " DATE: " + DATE + " TIME: " + TIME + " DISTANCE: " + DISTANCE + " DIRECTION: " + DIRECTION + " SPEED: " + SPEED + " SIZE: " + SIZE + " TEMPERATURE: " + TEMPERATURE + " NUMBER: " + NUMBER + " previousvalue: " + getPreviousValue() + " uuid: " + getUUID() + ")";
		return result;
	}
}

