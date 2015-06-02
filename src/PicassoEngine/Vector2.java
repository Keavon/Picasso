package PicassoEngine;

public class Vector2 {
	public double x;
	public double y;
	
	public Vector2() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void add(Vector2 toAdd) {
		this.x += toAdd.x;
		this.y += toAdd.y;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
