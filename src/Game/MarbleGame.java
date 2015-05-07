package Game;

import PicassoEngine.*;

public class MarbleGame {
	public static void main(String[] args) {
		Frame frame = new Frame();
		Scene scene = frame.getScene();
		
		// Create camera
		Camera camera = new Camera(new Vector3(0, 3, -15));
		camera.addScript(new FirstPersonCamera(camera));
		scene.addGameObject(camera);
		scene.setActiveCamera(camera);
		
		// Create model
		Model cube = new Model("models/scene.obj");
		scene.addGameObject(cube);
	}
}