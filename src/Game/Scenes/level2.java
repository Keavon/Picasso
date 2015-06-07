package Game.Scenes;

import Game.Scripts.BallMovement;
import Game.Scripts.CameraFollow;
import Game.Scripts.SpinningGoal;
import PicassoEngine.*;

public class level2 extends Scene {
	public void Load(PicassoEngine engine) {
		// Hide mouse cursor
		Application.hideCursor();
		
		// Start physics running
		startPhysics();
		
		// Add sky
		setSky(new Sky("assets/images/sky.jpg"));
		
		// Add ball
		RigidBody ball = new RigidBody("models/ball.obj", new Vector3(0, 1, 0), new Vector3(), new Vector3(0.25, 0.25, 0.25));
		
		// Add camera
		Camera camera = new Camera(new Vector3());
		addGameObject(camera);
		setActiveCamera(camera);
		
		// Add scripts to camera
		CameraFollow cameraFollow = new CameraFollow(camera, ball);
		camera.addScript(cameraFollow);
		
		// Add scripts to ball
		BallMovement ballMovement = new BallMovement(ball, cameraFollow, this, engine, 2, 0);
		ball.addScript(ballMovement);
		addGameObject(ball);
		
		// Add world
		Model track = new Model("models/level parts/level2.obj");
		addGameObject(track);
		
		// Add world's simplified collider
		Model trackCollider = new Model("models/level parts/level2_collider.obj");
		trackCollider.setCollides(true);
		trackCollider.setVisible(false);
		addGameObject(trackCollider);
		
		Model goal = new Model("models/level parts/goal.obj", new Vector3(10, 4.5, -22));
		goal.addScript(new SpinningGoal(goal));
		goal.setCollides(true);
		addGameObject(goal);
		
		ballMovement.setGoal(goal);
	}
}