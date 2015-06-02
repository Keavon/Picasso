package Game.Scripts;

import PicassoEngine.*;

public class CameraFollow extends PicassoScript {
	private double distance = 3.5;
	private GameObject target;
	
	private double orbitAngle;
	private double elevationAngle = 120;
	
	double orbitSpeedKeys = 100;
	double elevationSpeedKeys = 100;
	double orbitSpeedMouse = 0.1;
	double elevationSpeedMouse = 0.1;
	
	public CameraFollow(GameObject gameObject, RigidBody target) {
		super(gameObject);
		this.target = target;
	}
	
	public void LateUpdate() {
		if (target != null) {
			// Camera view orbit
			orbitAngle -= Input.mouseMovement.x * orbitSpeedMouse;
			if (Input.GetKey("Left")) orbitAngle -= orbitSpeedKeys * Time.deltaTime;
			if (Input.GetKey("Right")) orbitAngle += orbitSpeedKeys * Time.deltaTime;
			
			// Camera view elevation
			if (Input.GetKey("Mouse1")) elevationAngle += Input.mouseMovement.y * elevationSpeedMouse;
			if (Input.GetKey("Up") && elevationAngle <= 179.9) elevationAngle += elevationSpeedKeys * Time.deltaTime;
			if (Input.GetKey("Down") && elevationAngle >= 90) elevationAngle -= elevationSpeedKeys * Time.deltaTime;
			
			// Clamp angles
			if (elevationAngle > 179.9) elevationAngle = 179.9;
			else if (elevationAngle < 90) elevationAngle = 90;
			
			// Scroll wheel camera zoom
			distance += Input.getMouseScrollRotation() * 0.25;
			if (distance < 2) distance = 2;
			else if (distance > 7.5) distance = 7.5;
			
			// Set positions
			double xCord = distance * Math.sin(elevationAngle * Math.PI / 180) * Math.cos(orbitAngle * Math.PI / 180);
			double zCord = distance * Math.sin(elevationAngle * Math.PI / 180) * Math.sin(orbitAngle * Math.PI / 180);
			double yCord = distance * Math.cos(elevationAngle * Math.PI / 180);
			gameObject.setPosition(new Vector3(target.getPosition().x - xCord, target.getPosition().y - yCord, target.getPosition().z - zCord));
			
			// Set rotations
			Vector3 angleDifference = gameObject.getPosition().getDifference(target.getPosition());
			Quaternion horizontalRotation = new Quaternion(new Vector3(0, Math.atan2(angleDifference.x, angleDifference.z), 0));
			Quaternion verticalRotation = new Quaternion(new Vector3(-Math.asin(angleDifference.y / distance), 0, 0));
			gameObject.setRotation(horizontalRotation.times(verticalRotation));
		}
	}
	
	public double getOrbitAngle() {
		return orbitAngle;
	}
}
