package PicassoEngine;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
	private Frame frame;
	private MouseInput mouseListener;
	
	public Canvas(Frame frame) {
		// Add input listeners
		mouseListener = new MouseInput(frame.getFrame());
		new KeyInput(this);
		new MouseWheelInput(frame.getFrame());
		new MouseClickInput(frame.getFrame());
		
		// Set up the frame
		this.frame = frame;
		setFocusable(true);
		repaint();
	}
	
	public void paintComponent(Graphics graphics) {
		frame.getScene().getRenderer().render(graphics);
	}
	
	public void recenterMouse() {
		mouseListener.recenterMouse();
	}
}