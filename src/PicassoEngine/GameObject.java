package PicassoEngine;

import java.util.ArrayList;

public class GameObject {
	private String name;
	private Vector3 position;
	private Quaternion rotation;
	private Vector3 scale;
	private ArrayList<PicassoScript> scripts;
	
	//Pre: Name of the gameobject
	//Post: Creates a named gameobject in the origin without rotation
	public GameObject(String name) {
		this(name, new Vector3(), new Vector3(), new Vector3(1, 1, 1));
	}
	
	//Pre: Name and position
	//Post: Sets given with default rotation and scale
	public GameObject(String name, Vector3 position) {
		this(name, position, new Vector3(), new Vector3(1, 1, 1));
	}
	
	//Pre: Name, position, and rotation
	//Post: Sets given with default scale
	public GameObject(String name, Vector3 position, Vector3 rotation) {
		this(name, position, rotation, new Vector3(1, 1, 1));
	}
	
	//Pre: Name, position, rotation, and scale
	//Post: Sets the given values
	public GameObject(String name, Vector3 position, Vector3 rotation, Vector3 scale) {
		this.name = name;
		this.position = position;
		this.rotation = new Quaternion(rotation);
		this.scale = scale;
		this.scripts = new ArrayList<PicassoScript>();
	}
	
	//Pre: Script to add
	//Post: Adds a script to the gameobject
	public void addScript(PicassoScript script) {
		scripts.add(script);
	}
	
	//Pre: None
	//Post: Returns the name of the gameobject
	public String getName() {
		return name;
	}
	
	//Pre: None
	//Post: Returns the position of the gameobject
	public Vector3 getPosition() {
		return position;
	}
	
	//Pre: Position in 3D space
	//Post: Sets the gameobject to the given position
	public void setPosition(Vector3 position) {
		this.position = position;
	}
	
	//Pre: Position to move by
	//Post: Adds this to the existing position
	public void addPosition(Vector3 deltaPosition) {
		setPosition(getPosition().getSum(deltaPosition));
	}
	
	//Pre: None
	//Post: Returns the current rotation
	public Quaternion getRotation() {
		return rotation;
	}
	
	//Pre: Rotation to set
	//Post: Sets the rotation with euler angles
	public void setRotation(Vector3 rotation) {
		this.rotation = new Quaternion(rotation);
	}
	
	//Pre: Rotation to set
	//Post: Sets the rotation with quaternions
	public void setRotation(Quaternion rotation) {
		this.rotation = rotation;
	}
	
	//Pre: Rotation to move by
	//Post: Adds to the rotation
	public void addRotation(Vector3 deltaRotation) {
		setRotation(getRotation().getProduct(new Quaternion(deltaRotation)));
	}
	
	//Pre: None
	//Post: Returns the scale of the object
	public Vector3 getScale() {
		return scale;
	}
	
	//Pre: Scale to set
	//Post: Sets the object to the given scale
	public void setScale(Vector3 scale) {
		this.scale = scale;
	}
	
	//Pre: None
	//Post: Returns the scripts attached to this gameobject
	public ArrayList<PicassoScript> getScripts() {
		return scripts;
	}
}