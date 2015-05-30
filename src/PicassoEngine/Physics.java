package PicassoEngine;

import java.util.ArrayList;
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
				if (collisionPoint != null) {
					// Collision movement unit vector
					Vector3 collisionNormal = collisionPoint.getDifference(item.getPosition());
					double surfacePenetration = item.getRadius() - collisionNormal.getMagnitude();
					collisionNormal.normalize();
					
					// Add collision to the total collision forces
					Vector3 normalForce = collisionNormal.getScaled(Math.abs(item.getMass() * forcesBeforeCollision.getDotProduct(collisionNormal)));
					collisionForces.add(normalForce);
					
					// Reflect velocity
					Vector3 normalComponent = collisionNormal.getScaled(item.getVelocity().getDotProduct(collisionNormal));
					Vector3 tangentComponent = normalComponent.getDifference(item.getVelocity());
					normalComponent.scale(-1);
					
					// Scale by bounciness of ball
					normalComponent.scale(0.4);
					if (normalComponent.getMagnitude() < 0.1) {
						normalComponent.scale(0);
					}
					
					// Set bounced velocity
					item.setVelocity(normalComponent.getSum(tangentComponent));
					
					// Move item to surface so it isn't penetrating the collider
					// NOTE: this might cause bugs with multiple sources of collision and may violate the conservation of energy
					item.addPosition(collisionNormal.getNormalized().getScaled(surfacePenetration));
				}
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
			
			// Apply impulse
			item.addVelocity(item.getImpulse().getScaled(1 / (0.02 * item.getMass())));
			item.resetImpulse();
			
			// Displace rigidbodies with velocity
			item.addPosition(item.getVelocity().getScaled(0.02));
		}
	}
	
	private Vector3[] worldCollision(Vector3 ball, double radius) {
		ArrayList<Vector3> collisionPoints = new ArrayList<Vector3>();
		for (Model object : scene.getColliders()) {
			Vector3[] theseCollisions = objectCollision(object, ball, radius);
			for (Vector3 c : theseCollisions) {
				collisionPoints.add(c);
			}
		}
		
		Vector3[] result = new Vector3[collisionPoints.size()];
		for (int i = 0; i < collisionPoints.size(); i++) {
			result[i] = collisionPoints.get(i);
		}
		
		return result;
	}
	
	private Vector3[] objectCollision(Model object, Vector3 ball, double radius) {
		// Stores all the collision points
		ArrayList<Vector3> collisionPoints = new ArrayList<Vector3>();
		
		// Go through each face in the model
		for (int face = 0; face < object.getFaces().length; face++) {
			// Gather the vertices in this face
			int[] vertexIndexes = object.getFaces()[face].getVertexIndexes();
			Vector3[] orderedVertices = new Vector3[vertexIndexes.length];
			for (int i = 0; i < vertexIndexes.length; i++) {
				orderedVertices[i] = object.getVertices()[vertexIndexes[i]];
			}
			
			// Find any collisions on the face
			collisionPoints.add(planeCollision(orderedVertices, ball, radius));
		}
		
		// Return ArrayList of collision points as an array
		Vector3[] result = new Vector3[collisionPoints.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = collisionPoints.get(i);
		}
		return result;
	}
	
	private Vector3 planeCollision(Vector3[] planePoints, Vector3 ball, double radius) {
		// Find unit vector normal to the face
		Vector3 v1 = planePoints[2].getDifference(planePoints[1]);
		Vector3 v2 = planePoints[0].getDifference(planePoints[1]);
		Vector3 faceNormal = v1.getCrossProduct(v2);
		faceNormal.normalize();
		
		// Find closest distance from center of ball to plane
		double plane = -(faceNormal.x * planePoints[0].x + faceNormal.y * planePoints[0].y + faceNormal.z * planePoints[0].z);
		double distanceToPlane = faceNormal.x * ball.x + faceNormal.y * ball.y + faceNormal.z * ball.z + plane;
		
		// No collision if the collision distance is further than the ball's radius from the plane
		if (Math.abs(distanceToPlane) > radius) {
			return null;
		}
		
		// Find and return 3D point of collision
		Vector3 ballCenterToCollisionPoint = faceNormal.getScaled(-distanceToPlane);
		Vector3 collisionPoint = ball.getSum(ballCenterToCollisionPoint);
		if (collisionOnFace(planePoints, collisionPoint)) {
			return collisionPoint;
		}
		
		// If there's no collision on a face, check each edge
		for (int i = 0; i < planePoints.length; i++) {
			Vector3 edgeCollisionPoint = edgeCollisionPoint(planePoints[i], planePoints[(i + 1) % planePoints.length], ball);
			if (edgeCollisionPoint.getDifference(ball).getMagnitude() <= radius) {
				return edgeCollisionPoint;
			}
		}
		
		return null;
	}
	
	public boolean collisionOnFace(Vector3[] planePoints, Vector3 collisionPoint) {
		// Find area of polygon
		double areaXY = 0;
		double areaYZ = 0;
		double areaZX = 0;
		
		// Find areas
		for (int i = 0; i < planePoints.length - 1; i++) {
			areaXY += planePoints[i].x * planePoints[i + 1].y - planePoints[i + 1].x * planePoints[i].y;
			areaYZ += planePoints[i].y * planePoints[i + 1].z - planePoints[i + 1].y * planePoints[i].z;
			areaZX += planePoints[i].z * planePoints[i + 1].x - planePoints[i + 1].z * planePoints[i].x;
		}
		// Final case that loops from the last to the first vertex in the array
		areaXY += planePoints[planePoints.length - 1].x * planePoints[0].y - planePoints[0].x * planePoints[planePoints.length - 1].y;
		areaYZ += planePoints[planePoints.length - 1].y * planePoints[0].z - planePoints[0].y * planePoints[planePoints.length - 1].z;
		areaZX += planePoints[planePoints.length - 1].z * planePoints[0].x - planePoints[0].z * planePoints[planePoints.length - 1].x;
		
		// Project 3D plane into 2D
		Vector2[] points2D = new Vector2[planePoints.length];
		double a;
		double b;
		if (areaXY > areaYZ && areaXY > areaZX) {
			for (int i = 0; i < points2D.length; i++) {
				points2D[i] = new Vector2(planePoints[i].x, planePoints[i].y);
			}
			a = collisionPoint.x;
			b = collisionPoint.y;
		} else if (areaYZ > areaZX) {
			for (int i = 0; i < points2D.length; i++) {
				points2D[i] = new Vector2(planePoints[i].y, planePoints[i].z);
			}
			a = collisionPoint.y;
			b = collisionPoint.z;
		} else {
			for (int i = 0; i < points2D.length; i++) {
				points2D[i] = new Vector2(planePoints[i].z, planePoints[i].x);
			}
			a = collisionPoint.z;
			b = collisionPoint.x;
		}
		Vector2 collisionPoint2D = new Vector2(a, b);
		
		int intersections = 0;
		for (int i = 0; i < points2D.length - 1; i++) {
			if (pointAboveEdge(points2D[i], points2D[i + 1], collisionPoint2D)) {
				intersections++;
			}
		}
		if (pointAboveEdge(points2D[points2D.length - 1], points2D[0], collisionPoint2D)) {
			intersections++;
		}
		
		// An odd number of intersections means we're in the polygon while an even number means we're outside
		return intersections % 2 == 1;
	}
	
	public Vector3 edgeCollisionPoint(Vector3 pointA, Vector3 pointB, Vector3 ball) {
		// Vector from A to P
		Vector3 AP = pointA.getDifference(ball);
		// Vector from A to B
		Vector3 AB = pointA.getDifference(pointB);
		
		// Magnitude of AB vector (length squared)
		double magnitudeAB = AB.getMagnitudeSquared();
		// The dot product of A-to-P and A-to-B
		double ABAPProduct = AP.getDotProduct(AB);
		// The normalized "distance" from a to your closest point
		double distance = ABAPProduct / magnitudeAB;
		
		// Check if P projection is over vectorAB
		if (distance < 0) {
			return pointA;
		} else if (distance > 1) {
			return pointB;
		} else {
			return pointA.getSum(AB.getScaled(distance));
		}
	}
	
	private boolean pointAboveEdge(Vector2 pointA, Vector2 pointB, Vector2 point) {
		if ((pointA.x < point.x && point.x < pointB.x) || (pointA.x > point.x && point.x > pointB.x)) {
			// Find slope
			double m = (pointB.y - pointA.y) / (pointB.x - pointA.x);
			// Find Y-intercept
			double b = pointA.y - m * pointA.x;
			// Find Y value at the point we're checking
			double yValueAtPoint = m * point.x + b;
			
			// Return true if the Y value of the point we're checking is greater than the Y value at the line
			return point.y > yValueAtPoint;
		}
		return false;
	}
}
