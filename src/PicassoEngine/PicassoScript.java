package PicassoEngine;

public class PicassoScript {
	protected GameObject gameObject;
	
	public PicassoScript(GameObject gameObject) {
		this.gameObject = gameObject;
	}
	
	public void Awake() {
		// Called when object is instantiated
	}
	
	public void Start() {
		// Calls after Awake
	}
	
	public void Update() {
		// Calls every frame
	}
	
	public void LateUpdate() {
		// Calls every frame after Update()
	}
	
	public void FixedUpdate() {
		// Calls every physics step, every 0.02 seconds
	}
}