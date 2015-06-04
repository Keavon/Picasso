package PicassoEngine;

import javax.swing.*;

public class PicassoEngine {
	private JFrame frame;
	private Canvas canvas;
	private Scene scene;
	private Renderer renderer;
	
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
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public Renderer getRenderer() {
		return renderer;
	}
	
	public void loadScene(Scene scene) {
		this.scene = scene;
		scene.Load(this);
	}
}
