package PicassoEngine;

import java.util.ArrayList;

public class RigidBody extends Model {
	private double radius = 1;
	private double mass = 1;
	private Vector3 velocity = new Vector3();
	private Vector3 angularVelocity = new Vector3(); // normalized = axis of rotation, magnitude = velocity of rotation
	private Vector3 torque = new Vector3();
	private Vector3 impulse = new Vector3();
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
	
	public RigidBody(String file, Vector3 position, Vector3 rotation, Vector3 scale) {
		super(file, position, rotation, scale);
		radius = (scale.x + scale.y + scale.z) / 3;
	}
	
	public Vector3 getTorque() {
		return torque;
	}
	
	public void setTorque(Vector3 torque) {
		this.torque = torque;
	}
	
	public void addTorque(Vector3 torque) {
		this.torque.add(torque);
	}
	
	public void resetTorque() {
		this.torque = new Vector3();
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
	
	public Vector3 getAngularVelocity() {
		return angularVelocity;
	}
	
	public void setAngularVelocity(Vector3 angularVelocity) {
		this.angularVelocity = angularVelocity;
	}
	
	public void addAngularVelocity(Vector3 angularVelocity) {
		this.angularVelocity.add(angularVelocity);
	}
	
	public void addForce(Vector3 force) {
		forces.add(force);
	}
	
	public void addForceAtPosition(Vector3 force, Vector3 position) {
		//addForce(force);
		addTorque(position.getCrossProduct(force));
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
	
	public void setImpulse(Vector3 newImpulse) {
		impulse = newImpulse;
	}
	
	public void resetImpulse() {
		impulse = new Vector3();
	}
	
	public void addImpulse(Vector3 impulseToAdd) {
		impulse.add(impulseToAdd);
	}
	
	public Vector3 getImpulse() {
		return impulse;
	}
}
