package PicassoEngine;

public class Camera extends GameObject {
	public double fov = Math.PI / 2;
	
	public Camera(Vector3 position) {
		super("Main Camera", position);
	}
	
	public Camera(Vector3 position, Vector3 rotation) {
		super("Main Camera", position, rotation);
	}
	
	
	public double getFov() {
		return fov;
	}
	
	public void setFov(double fov) {
		this.fov = fov;
	}
}
