package PicassoEngine;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
	private MouseInput mouseListener;
	private PicassoEngine engine;
	private boolean recenterMouse = false;
	
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
	
	public void paintComponent(Graphics graphics) {
		engine.getRenderer().render(graphics);
	}
	
	public void recenterMouse() {
		if (recenterMouse) {
			mouseListener.recenterMouse();
		}
	}
	
	public void setRecenterMouse(boolean recenter) {
		recenterMouse = recenter;
	}
}