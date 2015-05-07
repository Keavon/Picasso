package PicassoEngine;

public class Polygon2D {
	private Vector2[] points;
	private String color;
	private Vector3 projectedCentroid;
	
	public Polygon2D(Vector2[] points, Vector3 projectedCentroid, String color) {
		this.points = points;
		this.projectedCentroid = projectedCentroid;
		this.color = color;
	}
	
	public Vector2[] getPoints() {
		return points;
	}
	
	public Vector3 getProjectedCentroid() {
		return projectedCentroid;
	}
	
	public String getColor() {
		return color;
	}
	
	public String toString() {
		String string = "2D polygon with color #" + color + " and screen points:";
		for (int i = 0; i < points.length; i++) {
			string += points[i];
			if (i < points.length - 1) {
				string += ", ";
			}
		}
		return string;
	}
}
