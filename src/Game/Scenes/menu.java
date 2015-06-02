package Game.Scenes;

import Game.Scripts.*;
import PicassoEngine.*;

public class menu extends Scene {
	public void Load(PicassoEngine engine) {
		// Play game in goFullscreen
		Application.goFullscreen();
		
		// Add sky
		setSky(new Sky("assets/images/skybox.png"));
		Camera camera = new Camera(new Vector3());
		camera.addScript(new MenuCamera(camera, this, engine));
		addGameObject(camera);
		setActiveCamera(camera);
	}
}
