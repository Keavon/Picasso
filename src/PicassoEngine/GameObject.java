package PicassoEngine;

import java.util.ArrayList;

public class GameObject {
	public String name;
	public Vector3 position;
	public Vector3 rotation;
	public ArrayList<PicassoScript> scripts;
	
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
}