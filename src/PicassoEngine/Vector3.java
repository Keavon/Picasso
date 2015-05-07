package PicassoEngine;

public class Vector3
{
	public double x;
	public double y;
	public double z;
	
	public Vector3() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void scale(double scalar) {
		this.x *= scalar;
		this.y *= scalar;
		this.z *= scalar;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
}
