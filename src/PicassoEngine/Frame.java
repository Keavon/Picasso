package PicassoEngine;

import javax.swing.*;
import java.awt.*;

public class Frame {
	private JFrame frame;
	private Canvas canvas;
	private Scene scene;
	
	// Create the window
	public Frame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		scene = new Scene(this);
		canvas = new Canvas(this);
		frame.add(canvas);
		frame.setVisible(true);
		
		// Hide cursor
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor c = toolkit.createCustomCursor(toolkit.getImage("") , new Point(frame.getX(), frame.getY()), "img");
		frame.setCursor (c);
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
}
