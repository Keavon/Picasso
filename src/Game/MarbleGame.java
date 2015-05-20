package Game;

import PicassoEngine.*;

public class MarbleGame {
	public static void main(String[] args) {
		Frame frame = new Frame();
		Scene scene = frame.getScene();
		
		// Create camera
		Camera camera = new Camera(new Vector3(0, 3, -15));
		camera.addScript(new FirstPersonCamera(camera));
		scene.addGameObject(camera);
		scene.setActiveCamera(camera);
		
		// Create model
		Model plane = new Model("models/plane.obj");
		scene.addGameObject(plane);
		
		// Create ball
		RigidBody ball = new RigidBody("models/ball.obj", new Vector3(0, 5, 0));
		ball.setVelocity(new Vector3(0, 0, 0));
		//ball.addScript(new Spin(ball));
		scene.addGameObject(ball);
	}
}