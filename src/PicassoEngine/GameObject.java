package PicassoEngine;

import java.util.ArrayList;

public class GameObject {
	private String name;
	private Vector3 position;
	private Vector3 rotation;
	private ArrayList<PicassoScript> scripts;
	
	public GameObject(String name) {
		this(name, new Vector3(), new Vector3());
	}
	
	public GameObject(String name, Vector3 position) {
		this(name, position, new Vector3());
	}
	
	public GameObject(String name, Vector3 position, Vector3 rotation) {
		this.name = name;
		this.position = position;
		this.rotation = rotation;
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
		setPosition(getPosition().sum(deltaPosition));
	}
	
	public Vector3 getRotation() {
		return rotation;
	}
	
	public void setRotation(Vector3 rotation) {
		this.rotation = rotation;
	}
	
	public void addRotation(Vector3 deltaRotation) {
		setRotation(getRotation().sum(deltaRotation));
	}
	
	public ArrayList<PicassoScript> getScripts() {
		return scripts;
	}
}