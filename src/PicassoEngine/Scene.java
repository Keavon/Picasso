package PicassoEngine;

import java.util.ArrayList;

public class Scene {
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private Renderer renderer;
	private Camera activeCamera;
	
	public Scene(Frame frame) {
		this.renderer = new Renderer(frame);
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
		for (int object = 0; object < gameObjects.size(); object++) {
			for (int script = 0; script < gameObjects.get(object).getScripts().size(); script++) {
				gameObjects.get(object).getScripts().get(script).LateUpdate();
			}
		}
	}
}
