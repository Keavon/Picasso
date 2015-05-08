package PicassoEngine;

import java.awt.*;

public class Polygon2D {
	private Vector2[] points;
	private String color;
	private Vector3 projectedCentroid;
	private double brightness;
	
	public Polygon2D(Vector2[] points, Vector3 projectedCentroid, String color, double brightness) {
		this.points = points;
		this.projectedCentroid = projectedCentroid;
		this.color = color;
		this.brightness = brightness;
	}
	
	public Vector2[] getPoints() {
		return points;
	}
	
	public Vector3 getProjectedCentroid() {
		return projectedCentroid;
	}
	
	public String getColor() {
		Color workingColor = Color.decode("#" + color);
		float[] hsbComponents = Color.RGBtoHSB(workingColor.getRed(), workingColor.getGreen(), workingColor.getBlue(), null);
		Color shadedColor = Color.getHSBColor(hsbComponents[0], hsbComponents[1], (float) (hsbComponents[2] * brightness));
		return String.format("%02x%02x%02x", shadedColor.getRed(), shadedColor.getGreen(), shadedColor.getBlue());
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
