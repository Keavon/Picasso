package Game.Scenes;

import Game.Scripts.*;
import PicassoEngine.*;

public class menu extends Scene {
	public void Load(PicassoEngine engine) {
		// Play game in goFullscreen
		Application.goFullscreen();
		Application.showCursor();
		
		// Add sky
		setSky(new Sky("assets/images/sky.jpg"));
		Camera camera = new Camera(new Vector3());
		camera.addScript(new MenuCamera(camera, this, engine));
		addGameObject(camera);
		setActiveCamera(camera);
	}
}
