package Game.Scripts;

import Game.Scenes.*;
import PicassoEngine.*;

public class MenuCamera extends PicassoScript {
	
	private PicassoEngine engine;
	
	public MenuCamera(GameObject gameObject, Scene scene, PicassoEngine engine) {
		super(gameObject);
		this.engine = engine;
		scene.addGUIElement(new GUIElement(0, "images/GUI/splash.png", 50, 50));
	}
	
	public void Update() {
		if (Input.getKey("Escape")) {
			Application.quit();
		}
		if (Input.getKey("1")) {
			engine.loadScene(new level1());
		} else if (Input.getKey("2")) {
			engine.loadScene(new level2());
		}
	}
}
