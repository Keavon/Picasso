package PicassoEngine;

public class Quaternion {
	private double w;
	private double x;
	private double y;
	private double z;
	
	public Quaternion(double w, double x, double y, double z) {
		this.w = w;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	// From quaternion representation
	public Quaternion(Vector3 vector) {
		Quaternion quatAroundX = new Quaternion(new Vector3(1, 0, 0), vector.x);
		Quaternion quatAroundY = new Quaternion(new Vector3(0, 1, 0), vector.y);
		Quaternion quatAroundZ = new Quaternion(new Vector3(0, 0, 1), vector.z);
		Quaternion finalOrientation = quatAroundX.getProduct(quatAroundY).getProduct(quatAroundZ);
		w = finalOrientation.w;
		x = finalOrientation.x;
		y = finalOrientation.y;
		z = finalOrientation.z;
	}
	
	// From axis-angle representation
	public Quaternion(Vector3 axis, double angle) {
		w = Math.cos(angle / 2);
		Vector3 complexPart = axis.getNormalized().getScaled(Math.sin(angle / 2));
		x = complexPart.x;
		y = complexPart.y;
		z = complexPart.z;
	}
	
	public Vector3 getRotatedPoint(Vector3 point) {
		Quaternion p = new Quaternion(0, point.x, point.y, point.z);
		Quaternion pPrime = this.getProduct(p).getProduct(this.getConjugate());
		return new Vector3(pPrime.x, pPrime.y, pPrime.z);
	}
	
	public Vector3 getEulerAngles() {
		Vector3 result = new Vector3();
		result.x = Math.atan2(2 * (w * x + y * z), 1 - 2 * (x * x + y * y));
		result.y = Math.asin(2 * (w * y - z * x));
		result.z = Math.atan2(2 * (w * z + x * y), 1 - 2 * (y * y + z * z));
		return result;
	}
	
	public Quaternion getConjugate() {
		return new Quaternion(w, -x, -y, -z);
	}
	
	public Quaternion getProduct(Quaternion q) {
		return new Quaternion(
				w * q.w - x * q.x - y * q.y - z * q.z,
				w * q.x + x * q.w + y * q.z - z * q.y,
				w * q.y - x * q.z + y * q.w + z * q.x,
				w * q.z + x * q.y - y * q.x + z * q.w
		);
	}
}