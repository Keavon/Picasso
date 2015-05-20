package PicassoEngine;

import java.util.ArrayList;

public class RigidBody extends Model {
	private double radius = 1;
	private double mass = 1;
	private Vector3 velocity;
	private Vector3 angularVelocity;
	private ArrayList<Vector3> forces = new ArrayList<Vector3>();
	
	public RigidBody(String file) {
		super(file);
	}
	
	public RigidBody(String file, Vector3 position) {
		super(file, position);
	}
	
	public RigidBody(String file, Vector3 position, Vector3 rotation) {
		super(file, position, rotation);
	}
	
	public Vector3 getAngularVelocity() {
		return angularVelocity;
	}
	
	public Vector3 getVelocity() {
		return velocity;
	}
	
	public void addVelocity(Vector3 other) {
		velocity.add(other);
	}
	
	public void setVelocity(Vector3 velocity) {
		this.velocity = velocity;
	}
	
	public void setAngularVelocity(Vector3 angularVelocity) {
		this.angularVelocity = angularVelocity;
	}
	
	public void addForce(Vector3 force) {
		forces.add(force);
	}
	
	public ArrayList<Vector3> getForces() {
		return forces;
	}
	
	public void clearForces() {
		forces.clear();
	}
	
	public double getMass() {
		return mass;
	}
	
	public void setMass(double mass) {
		this.mass = mass;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
	}
}
