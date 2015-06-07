package Game.Scripts;

import Game.Scenes.*;
import Game.Scenes.menu;
import PicassoEngine.*;

public class BallMovement extends PicassoScript {
	private Vector3 movementForce = new Vector3();
	private CameraFollow cameraFollow;
	private Model goal;
	private boolean goalAchieved = false;
	private Scene scene;
	private PicassoEngine engine;
	private int levelNumber;
	private long lastJumpTime = 0;
	private int viewResetAngle;
	
	public BallMovement(GameObject gameObject, CameraFollow cameraFollow, Scene scene, PicassoEngine engine, int levelNumber, int viewResetAngle) {
		super(gameObject);
		this.levelNumber = levelNumber;
		this.engine = engine;
		this.scene = scene;
		this.cameraFollow = cameraFollow;
		this.viewResetAngle = viewResetAngle;
		RigidBody ball = (RigidBody) gameObject;
		ball.addForce(movementForce);
	}
	
	public void Update() {
		double angle = cameraFollow.getOrbitAngle();
		Vector3 force = new Vector3();
		
		if (Input.getKey("W")) {
			force.add(new Vector3(Math.cos(angle * Math.PI / 180), 0, Math.sin(angle * Math.PI / 180)));
		}
		if (Input.getKey("S")) {
			force.add(new Vector3(Math.cos((angle + 180) * Math.PI / 180), 0, Math.sin((angle + 180) * Math.PI / 180)));
		}
		if (Input.getKey("A")) {
			force.add(new Vector3(Math.cos((angle + 90) * Math.PI / 180), 0, Math.sin((angle + 90) * Math.PI / 180)));
		}
		if (Input.getKey("D")) {
			force.add(new Vector3(Math.cos((angle - 90) * Math.PI / 180), 0, Math.sin((angle - 90) * Math.PI / 180)));
		}
		movementForce.set(force.getScaled(5));
		
		// Exit game with escape key
		if (Input.getKey("Escape")) {
			engine.loadScene(new menu());
		}
		
		// Ability to stop rotation to fix rotation errors if they occur
		if (Input.getKey("Mouse2")) {
			((RigidBody) gameObject).setAngularVelocity(new Vector3());
		}
		
		// Reset when you fall to your death
		if (gameObject.getPosition().y < -2) {
			((RigidBody) gameObject).setVelocity(new Vector3());
			((RigidBody) gameObject).setAngularVelocity(new Vector3());
			gameObject.setRotation(new Vector3());
			gameObject.setPosition(new Vector3(0, 1, 0));
			cameraFollow.setOrbitAngle(viewResetAngle);
		}
		
		// Show the Level Completed screen when you win
		if (goal != null && !goalAchieved && goal.getPosition().getDifference(gameObject.getPosition()).getMagnitude() < 2.5) {
			goalAchieved = true;
			scene.addGUIElement(new GUIElement(100, "images/GUI/black_transparent.png", 0, 0, 10000, 10000, 50, 50, 0, 0));
			scene.addGUIElement(new GUIElement(101, "images/GUI/level_completed.png", 50, 50, 50, 50, 0, 0));
		}
		
		// Check for input after winning to go back to the menu
		if (goalAchieved && (Input.getKeyDown("Mouse0") || Input.getKeyDown("Space"))) {
			if (levelNumber == 1) {
				engine.loadScene(new level2());
			} else {
				engine.loadScene(new menu());
			}
		}
	}
	
	public void FixedUpdate() {
		// Jump when the space bar is pressed, when the object is colliding, and it's not jumped in the past 30 milliseconds
		Vector3 collisionAngle = ((RigidBody) gameObject).isColliding();
		if (collisionAngle.getMagnitude() < 0.01) return;
		if (Input.getKey("Space") && collisionAngle.getNormalized().getAngleDifference(scene.getGravity().getScaled(-1)) < Math.PI / 3 && System.nanoTime() - lastJumpTime > 30000000) {
			lastJumpTime = System.nanoTime();
			((RigidBody) gameObject).setColliding(new Vector3());
			((RigidBody) gameObject).addImpulse(new Vector3(0, 0.075, 0));
		}
	}
	
	public void setGoal(Model goal) {
		this.goal = goal;
	}
}
