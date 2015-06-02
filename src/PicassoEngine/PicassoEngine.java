package PicassoEngine;

import javax.swing.*;

public class PicassoEngine {
	private JFrame frame;
	private Canvas canvas;
	private Scene scene;
	private Renderer renderer;
	
	// Create the window
	public PicassoEngine(Scene scene) {
		// Create window frame
		frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		new Application(frame);
		
		// Store the scene
		this.scene = scene;
		scene.Load();
		
		// Create the renderer
		renderer = new Renderer(this);
		
		// Create the canvas
		canvas = new Canvas(this);
		frame.add(canvas);
		
		// Show window
		frame.setVisible(true);
	}
	
	// Return the drawing canvas
	public Canvas getCanvas() {
		return canvas;
	}
	
	// Return the frame
	public JFrame getFrame() {
		return frame;
	}
	
	// Return the 3D scene
	public Scene getScene() {
		return scene;
	}
	
	public Renderer getRenderer() {
		return renderer;
	}
	
	public void loadScene(Scene scene) {
		this.scene = scene;
	}
}
