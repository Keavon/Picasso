package Game;

import PicassoEngine.*;

public class BallMovement extends PicassoScript {
	private Vector3 movementForce = new Vector3();
	private CameraFollow cameraFollow;
	
	public BallMovement(GameObject gameObject, CameraFollow cameraFollow) {
		super(gameObject);
		this.cameraFollow = cameraFollow;
		RigidBody ball = (RigidBody) gameObject;
		ball.addForce(movementForce);
	}
	
	public void Update() {
		double angle = cameraFollow.getOrbitAngle();
		Vector3 force = new Vector3();
		
		if (Input.GetKey("W")) {
			force.add(new Vector3(Math.cos(angle * Math.PI / 180), 0, Math.sin(angle * Math.PI / 180)));
		}
		if (Input.GetKey("S")) {
			force.add(new Vector3(Math.cos((angle + 180) * Math.PI / 180), 0, Math.sin((angle + 180) * Math.PI / 180)));
		}
		if (Input.GetKey("A")) {
			force.add(new Vector3(Math.cos((angle + 90) * Math.PI / 180), 0, Math.sin((angle + 90) * Math.PI / 180)));
		}
		if (Input.GetKey("D")) {
			force.add(new Vector3(Math.cos((angle - 90) * Math.PI / 180), 0, Math.sin((angle - 90) * Math.PI / 180)));
		}
		if (Input.getKeyDown("Space")) {
			((RigidBody) gameObject).addImpulse(new Vector3(0, 0.15, 0));
		}
		movementForce.set(force.getScaled(5));
	}
}
