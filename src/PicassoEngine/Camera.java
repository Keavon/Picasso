package PicassoEngine;

public class Camera extends GameObject {
	public double fov = Math.PI / 2;
	
	//Pre: Location in world space
	//Post: Creates a camera at a given position
	public Camera(Vector3 position) {
		super("Main Camera", position);
	}
	
	//Pre: Location in world space and rotation
	//Post: Creates a camera at a given position and rotation
	public Camera(Vector3 position, Vector3 rotation) {
		super("Main Camera", position, rotation);
	}
	
	//Pre: None
	//Post: Returns the camera’s field of view
	public double getFov() {
		return fov;
	}
	
	//Pre: Field of view of the camera, greater than 0 and less than pi
	//Post: Sets the camera’s field of view
	public void setFov(double fov) {
		this.fov = fov;
	}
}
