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
	
	public Vector3 getScaled(double scalar) {
		return new Vector3(x * scalar, y * scalar, z * scalar);
	}
	
	public void scale(double scalar) {
		this.x *= scalar;
		this.y *= scalar;
		this.z *= scalar;
	}
	
	public Vector3 getSum(Vector3 other) {
		return new Vector3(x + other.x, y + other.y, z + other.z);
	}
	
	public Vector3 getProduct(Vector3 other) {
		return new Vector3(x * other.x, y * other.y, z * other.z);
	}
	
	public Vector3 getQuotient(Vector3 other) {
		return new Vector3(x / other.x, y / other.y, z / other.z);
	}
	
	public void add(Vector3 other) {
		this.x += other.x;
		this.y += other.y;
		this.z += other.z;
	}
	
	public Vector3 getDifference(Vector3 other) {
		return new Vector3(other.x - x, other.y - y, other.z - z);
	}
	
	public Vector3 getCrossProduct(Vector3 other) {
		return new Vector3(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x);
	}
	
	public double getDotProduct(Vector3 other) {
		return other.x * x + other.y * y + other.z * z;
	}
	
	public double getMagnitude() {
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	public double getAngleDifference(Vector3 other) {
		return Math.acos(getDotProduct(other) / (getMagnitude() * other.getMagnitude()));
	}
	
	public void normalize() {
		scale(1.0 / getMagnitude());
	}
	
	public Vector3 getReflection(Vector3 normal) {
		return this.getSum(normal.getScaled(-2 * this.getDotProduct(normal)));
	}
	
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
}
