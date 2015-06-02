package Game;

import Game.Scenes.level1;
import PicassoEngine.*;

public class Sphaera {
	public static void main(String[] args) {
		new PicassoEngine(new level1());

//		// Create ball
//		RigidBody ball = new RigidBody("models/ball.obj", new Vector3(7, 5, 0), new Vector3(), new Vector3(0.25, 0.25, 0.25));
//		scene.addGameObject(ball);
//		
//		// Create camera
//		Camera camera = new Camera(new Vector3(0, 3, -15));
//		CameraFollow cameraFollow = new CameraFollow(camera, ball);
//		camera.addScript(cameraFollow);
//		scene.addGameObject(camera);
//		scene.setActiveCamera(camera);
//		
//		// Add ball movement script
//		ball.addScript(new BallMovement(ball, cameraFollow));
	}
}