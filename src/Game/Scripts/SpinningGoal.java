package Game.Scripts;

import PicassoEngine.*;

public class SpinningGoal extends PicassoScript {
	public SpinningGoal(GameObject gameObject) {
		super(gameObject);
	}
	
	public void Update() {
		gameObject.addRotation(new Vector3(0, Time.deltaTime, 0));
	}
}
