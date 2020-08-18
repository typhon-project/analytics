package ac.york.typhon.analytics.commons.datatypes;

public class VehicleMetadata {
	
	public long getVIN() {
		return VIN;
	}
	public void setVIN(long vIN) {
		VIN = vIN;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getConstr_year() {
		return constr_year;
	}
	public void setConstr_year(int constr_year) {
		this.constr_year = constr_year;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getT_sensor_h() {
		return t_sensor_h;
	}
	public void setT_sensor_h(int t_sensor_h) {
		this.t_sensor_h = t_sensor_h;
	}
	public String getEngine_type() {
		return engine_type;
	}
	public void setEngine_type(String engine_type) {
		this.engine_type = engine_type;
	}
	long VIN;
	String brand;
	String model;
	int constr_year;
	String color;
	int t_sensor_h;
	String engine_type;
	
	public String toString() {
		return "VIN: " + VIN + " Brand: " + brand + " Model: " + model + " Constr Year: " + constr_year + " Color: " + color + " Sensor H: " + t_sensor_h + " Engine Type: " + engine_type;
	}
}
