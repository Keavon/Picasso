package PicassoEngine;

public class Vector3 {
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
	
	public Vector3 difference(Vector3 other) {
		return new Vector3(other.x - x, other.y - y, other.z - z);
	}
	
	public Vector3 cross(Vector3 other) {
		return new Vector3(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x);
	}
	
	public double dot(Vector3 other) {
		return other.x * x + other.y * y + other.z * z;
	}
	
	public double magnitude() {
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	public double angle(Vector3 other) {
		return Math.acos(dot(other) / (magnitude() * other.magnitude()));
	}
	
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
}
