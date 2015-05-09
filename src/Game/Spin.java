package Game;

import PicassoEngine.*;

public class Spin extends PicassoScript {
	public Spin(GameObject gameObject) {
		super(gameObject);
	}
	
	public void Update() {
		Model model = (Model) gameObject;
		model.addRotation(new Vector3(0, Time.deltaTime, 0));
	}
}