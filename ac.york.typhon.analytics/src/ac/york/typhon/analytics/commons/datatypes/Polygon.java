package ac.york.typhon.analytics.commons.datatypes;

import java.util.LinkedList;
import java.util.List;

public class Polygon {

	private List<Point> points;

	public String toString() {
		String result = "";

		result = "Polygon: " + points;
		return result;
	}

	public Polygon() {
		points = new LinkedList<Point>();
	}

	public Polygon(List<Point> points) {
		setPoints(points);
	}

	public void addPoint(Point p) {
		points.add(p);
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}
}
