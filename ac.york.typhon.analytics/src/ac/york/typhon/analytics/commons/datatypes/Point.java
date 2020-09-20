package ac.york.typhon.analytics.commons.datatypes;

public class Point {

	private double x;
	private double y;

	public String toString() {
		String result = "";

		result = "Point: " + x + ", " + y;
		return result;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
}
