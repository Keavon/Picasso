package PicassoEngine;

import javax.swing.*;

public class PicassoEngine {
	private JFrame frame;
	private Canvas canvas;
	private Scene scene;
	private Renderer renderer;
	
	//Pre: None
	//Post: Creates the frame and sets up everything necessary to run the game, creating the canvas and the scene
	public PicassoEngine(Scene scene) {
		// Create window frame
		frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		
		// Create the canvas
		canvas = new Canvas(this);
		frame.add(canvas);
		new Application(frame, canvas, this);
		
		// Store the scene
		loadScene(scene);
		
		// Create the renderer
		renderer = new Renderer(this);
		
		frame.setVisible(true);
	}
	
	//Pre: None
	//Post: Returns the canvas
	public Canvas getCanvas() {
		return canvas;
	}
	
	//Pre: None
	//Post: Returns the Frame
	public JFrame getFrame() {
		return frame;
	}
	
	//Pre: None
	//Post: Returns the Scene
	public Scene getScene() {
		return scene;
	}
	
	//Pre: None
	//Post: Returns the renderer
	public Renderer getRenderer() {
		return renderer;
	}
	
	//Pre: Scene to be loaded
	//Post: Loads the passed scene over the current scene
	public void loadScene(Scene scene) {
		this.scene = scene;
		scene.Load(this);
	}
}
