package Game;

import PicassoEngine.*;

public class MarbleGame {
	public static void main(String[] args) {
		Frame frame = new Frame();
		Scene scene = frame.getScene();
		
		SkyBox skybox = new SkyBox("assets/images/skybox.png", frame);
		scene.setActiveSky(skybox);
		// Create model
		Model plane = new Model("models/scene.obj");
		plane.setCollides(true);
		//plane.addScript(new Spin(plane));
		scene.addGameObject(plane);
		
		// Create ball
		RigidBody ball = new RigidBody("models/ball.obj", new Vector3(7, 5, 0), new Vector3(), new Vector3(0.25, 0.25, 0.25));
		ball.setVelocity(new Vector3(0, 0, 0));
		scene.addGameObject(ball);
		
		// Create camera
		Camera camera = new Camera(new Vector3(0, 3, -15));
		CameraFollow cameraFollow = new CameraFollow(camera, ball);
		camera.addScript(cameraFollow);
		scene.addGameObject(camera);
		scene.setActiveCamera(camera);
		
		// Add ball movement script
		ball.addScript(new BallMovement(ball, cameraFollow));
	}
}