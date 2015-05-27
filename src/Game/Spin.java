package Game;

import PicassoEngine.*;

public class Spin extends PicassoScript {
	public Spin(GameObject gameObject) {
		super(gameObject);
	}
	
	public void Update() {
		if (Input.GetKey("T")) {
			gameObject.addRotation(new Vector3(0, 0, -Time.deltaTime));
		}
		if (Input.GetKey("Y")) {
			gameObject.addRotation(new Vector3(0, 0, Time.deltaTime));
		}
	}
}