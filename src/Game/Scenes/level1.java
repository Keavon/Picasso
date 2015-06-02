package Game.Scenes;

import Game.Scripts.BallMovement;
import Game.Scripts.CameraFollow;
import Game.Scripts.SpinningGoal;
import PicassoEngine.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.FileInputStream;
import java.io.IOException;

public class level1 extends Scene {
	public void Load(PicassoEngine engine) {
		// Play game in goFullscreen
		Application.goFullscreen();
		
		// Hide mouse cursor
		Application.hideCursor();
		
		// Start physics running
		startPhysics();
		
		// Add sky
		setSky(new Sky("assets/images/skybox.png"));
		
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
		BallMovement ballMovement = new BallMovement(ball, cameraFollow, this, engine, 1);
		ball.addScript(ballMovement);
		addGameObject(ball);
		
		// Add world
		Model track = new Model("models/level parts/level1.obj");
		addGameObject(track);
		
		// Add world's simplified collider
		Model trackCollider = new Model("models/level parts/level1_collider.obj");
		trackCollider.setCollides(true);
		trackCollider.setVisible(false);
		addGameObject(trackCollider);
		
		Model goal = new Model("models/level parts/goal.obj", new Vector3(7.5, 2.5, -18));
		goal.addScript(new SpinningGoal(goal));
		goal.setCollides(true);
		addGameObject(goal);
		
		ballMovement.setGoal(goal);
		
		// Play music
		try {
			AudioPlayer.player.start(new AudioStream(new FileInputStream("assets/WildWaters.wav")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}