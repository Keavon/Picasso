package PicassoEngine;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
	private MouseInput mouseListener;
	PicassoEngine engine;
	
	//Pre: The frame
	//Post: Sets the frame, creates the mouse and keyboard listeners, and starts drawing
	public Canvas(PicassoEngine engine) {
		this.engine = engine;
		
		// Add input listeners
		mouseListener = new MouseInput(engine.getFrame());
		new KeyInput(this);
		new MouseWheelInput(engine.getFrame());
		new MouseClickInput(engine.getFrame());
		
		// Set up the frame
		setFocusable(true);
		
		// Initiate rendering
		repaint();
	}
	
	//Pre: Graphics object for the canvas passed by Swing
	//Post: Calls to render a frame
	public void paintComponent(Graphics graphics) {
		engine.getRenderer().render(graphics);
	}
	
	//Pre: None
	//Post: Returns mouse listener
	public void recenterMouse() {
		mouseListener.recenterMouse();
	}
}