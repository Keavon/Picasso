package Game;

import PicassoEngine.*;

public class FirstPersonCamera extends PicassoScript {
	public FirstPersonCamera(GameObject gameObject) {
		super(gameObject);
	}
	
	public void Update() {
		double movementSpeed = 1;
		double mouseSpeed = 1;
		
		Camera camera = (Camera) gameObject;
		
		// Rotate camera with mouse movement
		gameObject.rotation.y += (Input.mouseMovement.x * mouseSpeed * 1000) / (camera.getFov() * 250000);
		gameObject.rotation.x += (Input.mouseMovement.y * mouseSpeed * 1000) / (camera.getFov() * 250000);
		
		// Cap up/down camera view rotation
		if (gameObject.rotation.x > Math.PI / 2) gameObject.rotation.x = Math.PI / 2;
		else if (gameObject.rotation.x < -Math.PI / 2) gameObject.rotation.x = -Math.PI / 2;
		
		Vector3 movement = new Vector3();
		if (Input.GetKey("W")) movement.z += 1;
		if (Input.GetKey("S")) movement.z -= 1;
		if (Input.GetKey("D")) movement.x += 1;
		if (Input.GetKey("A")) movement.x -= 1;
		if (Input.GetKey("E")) movement.y += 1;
		if (Input.GetKey("Q")) movement.y -= 1;
		
		// Scale to a speed
		movement.scale(movementSpeed * 5 * Time.deltaTime);
		
		// Movement forwards/backwards
		gameObject.position.x += movement.z * Math.sin(gameObject.rotation.y);
		gameObject.position.z += movement.z * Math.cos(gameObject.rotation.y);
		gameObject.position.y -= movement.z * Math.sin(gameObject.rotation.x);
		
		// Movement left/right
		gameObject.position.x += movement.x * Math.cos(gameObject.rotation.y);
		gameObject.position.z -= movement.x * Math.sin(gameObject.rotation.y);
		
		// Movement up/down
		gameObject.position.y += movement.y;
	}
}
