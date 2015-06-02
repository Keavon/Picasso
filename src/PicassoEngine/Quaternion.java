package PicassoEngine;

// Credit: http://rosettacode.org/wiki/Quaternion_type#Java
public class Quaternion {
	public double a;
	public double b;
	public double c;
	public double d;
	
	public Quaternion() {
		a = 1;
		b = 0;
		c = 0;
		d = 0;
	}
	
	public Quaternion(double a, double b, double c, double d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	
	public Quaternion(Vector3 vector) {
		Quaternion quatAroundX = new Quaternion(new Vector3(1, 0, 0), vector.x);
		Quaternion quatAroundY = new Quaternion(new Vector3(0, 1, 0), vector.y);
		Quaternion quatAroundZ = new Quaternion(new Vector3(0, 0, 1), vector.z);
		Quaternion finalOrientation = quatAroundX.times(quatAroundY).times(quatAroundZ);
		a = finalOrientation.a;
		b = finalOrientation.b;
		c = finalOrientation.c;
		d = finalOrientation.d;
	}
	
	public Quaternion(Vector3 axis, double angle) {
		a = Math.cos(angle / 2);
		Vector3 complexPart = axis.getNormalized().getScaled(Math.sin(angle / 2));
		b = complexPart.x;
		c = complexPart.y;
		d = complexPart.z;
	}
	
	public Quaternion(double r) {
		this(r, 0.0, 0.0, 0.0);
	}
	
	public Vector3 rotatePoint(Vector3 point) {
		Quaternion p = new Quaternion(0, point.x, point.y, point.z);
		Quaternion pPrime = this.times(p).times(this.conjugate());
		return new Vector3(pPrime.b, pPrime.c, pPrime.d);
	}
	
	public Vector3 eulerAngles() {
		Vector3 result = new Vector3();
		result.x = Math.atan2(2 * (a * b + c * d), 1 - 2 * (b * b + c * c));
		result.y = Math.asin(2 * (a * c - d * b));
		result.z = Math.atan2(2 * (a * d + b * c), 1 - 2 * (c * c + d * d));
		return result;
	}
	
	public void scale(double scalar) {
		a *= scalar;
		b *= scalar;
		c *= scalar;
		d *= scalar;
	}
	
	public Quaternion getScaled(double scalar) {
		return new Quaternion(a * scalar, b * scalar, c * scalar, d * scalar);
	}
	
	public double norm() {
		return Math.sqrt(a * a + b * b + c * c + d * d);
	}
	
	public Quaternion negative() {
		return new Quaternion(-a, -b, -c, -d);
	}
	
	public Quaternion conjugate() {
		return new Quaternion(a, -b, -c, -d);
	}
	
	public Quaternion add(double r) {
		return new Quaternion(a + r, b, c, d);
	}
	
	public static Quaternion add(Quaternion q, double r) {
		return q.add(r);
	}
	
	public static Quaternion add(double r, Quaternion q) {
		return q.add(r);
	}
	
	public Quaternion add(Quaternion q) {
		return new Quaternion(a + q.a, b + q.b, c + q.c, d + q.d);
	}
	
	public static Quaternion add(Quaternion q1, Quaternion q2) {
		return q1.add(q2);
	}
	
	public Quaternion times(double r) {
		return new Quaternion(a * r, b * r, c * r, d * r);
	}
	
	public static Quaternion times(Quaternion q, double r) {
		return q.times(r);
	}
	
	public static Quaternion times(double r, Quaternion q) {
		return q.times(r);
	}
	
	public Quaternion times(Quaternion q) {
		return new Quaternion(
				a * q.a - b * q.b - c * q.c - d * q.d,
				a * q.b + b * q.a + c * q.d - d * q.c,
				a * q.c - b * q.d + c * q.a + d * q.b,
				a * q.d + b * q.c - c * q.b + d * q.a
		);
	}
	
	public static Quaternion times(Quaternion q1, Quaternion q2) {
		return q1.times(q2);
	}
	
	public boolean equals(Object obj) {
		if (!(obj instanceof Quaternion)) return false;
		final Quaternion other = (Quaternion) obj;
		if (Double.doubleToLongBits(this.a) != Double.doubleToLongBits(other.a)) return false;
		if (Double.doubleToLongBits(this.b) != Double.doubleToLongBits(other.b)) return false;
		if (Double.doubleToLongBits(this.c) != Double.doubleToLongBits(other.c)) return false;
		if (Double.doubleToLongBits(this.d) != Double.doubleToLongBits(other.d)) return false;
		return true;
	}
	
	public String toString() {
		return String.format("%.8f + %.8fi + %.8fj + %.8fk", a, b, c, d).replaceAll("\\+ -", "- ");
	}
	
	public String toQuadruple() {
		return String.format("(%.2f, %.2f, %.2f, %.2f)", a, b, c, d);
	}
}