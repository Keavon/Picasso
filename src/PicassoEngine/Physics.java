package PicassoEngine;

import java.util.Timer;
import java.util.TimerTask;

public class Physics implements Runnable {
	private Scene scene;
	private Timer timer;
	
	public Physics(Scene scene) {
		this.scene = scene;
		this.timer = new Timer();
	}
	
	public void run() {
		scene.callFixedUpdate();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				physicsStep();
			}
		}, 0, 20);
	}
	
	public void physicsStep() {
		RigidBody[] rigidBodies = scene.getRigidBodies();
		Model[] colliders = scene.getColliders();
		
		// Act on each rigidbody
		for (RigidBody item : rigidBodies) {
			// Apply constant forces
			Vector3 forcesBeforeCollision = new Vector3();
			// Scene forces (gravity)
			forcesBeforeCollision.add(scene.getGravity());
			// Object forces
			for (Vector3 force : item.getForces()) {
				forcesBeforeCollision.add(force);
			}
			
			Vector3[] collisionPoints = worldCollision(item.getPosition(), item.getRadius());
			Vector3 collisionForces = new Vector3();
			for (Vector3 collisionPoint : collisionPoints) {
				// Face normal, to be implemented
				Vector3 faceNormal = new Vector3(0, 1, 0);
				faceNormal.normalize();
				
				// Collision movement unit vector
				Vector3 actionVector = new Vector3(collisionPoint.x - item.getPosition().x, collisionPoint.y - item.getPosition().y, collisionPoint.z - item.getPosition().z);
				actionVector.normalize();
				
				// Collision movement unit vector reflected on face of collision
				Vector3 reflectionVector = actionVector.getReflection(faceNormal);
				
				// Add collision to the total collision forces
				collisionForces.add(reflectionVector.getProduct(forcesBeforeCollision).getScaled(-1));
				System.out.println(reflectionVector);
				
				// Reflect the velocity
				item.setVelocity(item.getVelocity().getReflection(faceNormal).getScaled(0.5));
			}
			
			// Collision detection
			// Determine collision forces
			// Add collision forces to constant forces
			Vector3 forceSum = new Vector3();
			forceSum.add(forcesBeforeCollision);
			forceSum.add(collisionForces);
			
			// Find acceleration from total forces
			Vector3 acceleration = forceSum.getScaled(1.0 / item.getMass());
			
			// Find velocity from acceleration and mass, adding it to the velocity of the object
			item.addVelocity(acceleration.getScaled(0.02 * item.getMass()));
			
			// Displace rigidbodies with velocity
			item.addPosition(item.getVelocity().getScaled(0.02));
		}
	}
	
	public Vector3[] worldCollision(Vector3 ball, double radius) {
		if (ball.y <= radius) {
			Vector3 collisionPoint = new Vector3(ball.x, 0, ball.z);
			return new Vector3[]{collisionPoint};
		}
		return new Vector3[]{};
	}
	
	public Vector3[] objectCollision(Model object, Vector3 ball, double radius) {
		return null;
	}
	
	public Vector3 planeCollision(Vector3[] planePoints, Vector3 ball, double radius) {
		return null;
	}
}
