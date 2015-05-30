package PicassoEngine;

import java.util.ArrayList;

public class Scene {
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private Vector3 gravity = new Vector3(0, -9.8, 0);
	private Renderer renderer;
	private Camera activeCamera;
	public SkyBox activeSky;
	
	public Scene(Frame frame) {
		this.renderer = new Renderer(frame);
		Physics physics = new Physics(this);
		Thread physicsThread = new Thread(physics);
		physicsThread.start();
	}
	
	public void setActiveSky(SkyBox s) {
		this.activeSky = s;
	}
	
	public void addGameObject(GameObject object) {
		gameObjects.add(object);
	}
	
	public void removeGameObject(GameObject object) {
		gameObjects.remove(gameObjects.indexOf(object));
	}
	
	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	public void clearScene() {
		gameObjects.clear();
	}
	
	public void setActiveCamera(Camera camera) {
		activeCamera = camera;
	}
	
	public Camera getActiveCamera() {
		return activeCamera;
	}
	
	public Renderer getRenderer() {
		return renderer;
	}
	
	public void callUpdate() {
		for (GameObject object : gameObjects) {
			for (PicassoScript script : object.getScripts()) {
				script.Update();
			}
		}
	}
	
	public void callLateUpdate() {
		for (GameObject object : gameObjects) {
			for (PicassoScript script : object.getScripts()) {
				script.LateUpdate();
			}
		}
	}
	
	public void callFixedUpdate() {
		for (GameObject object : gameObjects) {
			for (PicassoScript script : object.getScripts()) {
				script.FixedUpdate();
			}
		}
	}
	
	public RigidBody[] getRigidBodies() {
		ArrayList<RigidBody> rigidBodies = new ArrayList<RigidBody>();
		for (GameObject gameObject : gameObjects) {
			if (gameObject instanceof RigidBody) {
				rigidBodies.add((RigidBody) gameObject);
			}
		}
		
		RigidBody[] result = new RigidBody[rigidBodies.size()];
		for (int i = 0; i < rigidBodies.size(); i++) {
			result[i] = rigidBodies.get(i);
		}
		
		return result;
	}
	
	public Model[] getColliders() {
		ArrayList<Model> colliders = new ArrayList<Model>();
		for (GameObject gameObject : gameObjects) {
			if (gameObject instanceof Model && ((Model) gameObject).isCollides()) {
				colliders.add((Model) gameObject);
			}
		}
		
		Model[] result = new Model[colliders.size()];
		for (int i = 0; i < colliders.size(); i++) {
			result[i] = colliders.get(i);
		}
		
		return result;
	}
	
	public Vector3 getGravity() {
		return gravity;
	}
}
