package Game.Scenes;

import Game.Scripts.BallMovement;
import Game.Scripts.CameraFollow;
import Game.Scripts.GameUI;
import PicassoEngine.*;

public class level1 extends Scene {
	public void Load() {
		// Play game in fullscreen
		Application.fullscreen(true);
		
		// Hide mouse cursor
		Application.hideCursor();
		
		// Start physics running
		startPhysics();
		
		addGUIElement(new GUIElement(0, "images/GUI/powerup.png", 0, 0));
		addGUIElement(new GUIElement(1, "images/GUI/rocket_powerup.png", 0, 0));
		
		// Add sky
		setSky(new Sky("assets/images/skybox.png"));
		
		// Add ball
		RigidBody ball = new RigidBody("models/ball.obj", new Vector3(7, 5, 0), new Vector3(), new Vector3(0.25, 0.25, 0.25));
		
		// Add camera
		Camera camera = new Camera(new Vector3(0, 3, -15));
		camera.addScript(new GameUI(camera));
		addGameObject(camera);
		setActiveCamera(camera);
		
		// Add scripts to camera
		CameraFollow cameraFollow = new CameraFollow(camera, ball);
		camera.addScript(cameraFollow);
		
		// Add scripts to ball
		ball.addScript(new BallMovement(ball, cameraFollow));
		addGameObject(ball);
		
		// Add world
		Model track = new Model("models/scene.obj");
		track.setCollides(true);
		addGameObject(track);
	}
}