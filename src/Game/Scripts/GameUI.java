package Game.Scripts;

import PicassoEngine.Application;
import PicassoEngine.GameObject;
import PicassoEngine.Input;
import PicassoEngine.PicassoScript;

public class GameUI extends PicassoScript {
	public GameUI(GameObject gameObject) {
		super(gameObject);
	}
	
	public void Update() {
		if (Input.GetKey("Escape")) {
			Application.quit();
		}
	}
}
