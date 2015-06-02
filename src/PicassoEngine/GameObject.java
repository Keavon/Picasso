package PicassoEngine;

import java.util.ArrayList;

public class GameObject {
	private String name;
	private Vector3 position;
	private Quaternion rotation;
	private Vector3 scale;
	private ArrayList<PicassoScript> scripts;
	
	// Name with default transformations
	public GameObject(String name) {
		this(name, new Vector3(), new Vector3(), new Vector3(1, 1, 1));
	}
	
	// Name and position with default rotation and scale
	public GameObject(String name, Vector3 position) {
		this(name, position, new Vector3(), new Vector3(1, 1, 1));
	}
	
	// Name, position, and rotation with default scale
	public GameObject(String name, Vector3 position, Vector3 rotation) {
		this(name, position, rotation, new Vector3(1, 1, 1));
	}
	
	// Name, position, rotation, and scale
	public GameObject(String name, Vector3 position, Quaternion rotation, Vector3 scale) {
		this.name = name;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.scripts = new ArrayList<PicassoScript>();
	}
	
	public GameObject(String name, Vector3 position, Vector3 rotation, Vector3 scale) {
		this.name = name;
		this.position = position;
		this.rotation = new Quaternion(rotation);
		this.scale = scale;
		this.scripts = new ArrayList<PicassoScript>();
	}
	
	public void addScript(PicassoScript script) {
		scripts.add(script);
	}
	
	public String getName() {
		return name;
	}
	
	public Vector3 getPosition() {
		return position;
	}
	
	public void setPosition(Vector3 position) {
		this.position = position;
	}
	
	public void addPosition(Vector3 deltaPosition) {
		setPosition(getPosition().getSum(deltaPosition));
	}
	
	public Quaternion getRotation() {
		return rotation;
	}
	
	public void setRotation(Vector3 rotation) {
		this.rotation = new Quaternion(rotation);
	}
	
	public void setRotation(Quaternion rotation) {
		this.rotation = rotation;
	}
	
	public void addRotation(Vector3 deltaRotation) {
		setRotation(getRotation().times(new Quaternion(deltaRotation)));
	}
	
	public Vector3 getScale() {
		return scale;
	}
	
	public void setScale(Vector3 scale) {
		this.scale = scale;
	}
	
	public ArrayList<PicassoScript> getScripts() {
		return scripts;
	}
}