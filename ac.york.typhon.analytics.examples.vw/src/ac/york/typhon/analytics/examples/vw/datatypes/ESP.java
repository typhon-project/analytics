package ac.york.typhon.analytics.examples.vw.datatypes;

import java.util.ArrayList;

public class ESP {
	int VIN;
	String timestamp;
	String position;
	boolean esp_edl;
	ArrayList<VehicleMetadata> metadata;
	
	public ArrayList<VehicleMetadata> getMetadata() {
		return metadata;
	}
	public void setMetadata(ArrayList<VehicleMetadata> metadata) {
		this.metadata = metadata;
	}
	public int getVIN() {
		return VIN;
	}
	public void setVIN(int vIN) {
		VIN = vIN;
	}
	public boolean isEsp_edl() {
		return esp_edl;
	}
	public void setEsp_edl(boolean esp_edl) {
		this.esp_edl = esp_edl;
	}
	public boolean isEsp_iddl() {
		return esp_iddl;
	}
	public void setEsp_iddl(boolean esp_iddl) {
		this.esp_iddl = esp_iddl;
	}
	public boolean isEsp_abs() {
		return esp_abs;
	}
	public void setEsp_abs(boolean esp_abs) {
		this.esp_abs = esp_abs;
	}

	boolean esp_iddl;
	boolean esp_abs;

	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String toString() {
		return "VIN: " + VIN + " Timestamp: " + timestamp + " Position: " + position;
	}

}
