package PicassoEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseMotionListener {
	private double oldMouseX;
	private double oldMouseY;
	private JFrame frame;
	private Robot robot;
	
	public MouseInput(JFrame frame) {
		this.frame = frame;
		frame.addMouseMotionListener(this);
		
		oldMouseY = frame.getWidth() / 2;
		oldMouseX = frame.getHeight() / 2;
		
		try {
			robot = new java.awt.Robot();
		} catch (java.awt.AWTException ex) {
			ex.printStackTrace();
		}
	}
	
	public void recenterMouse() {
		if (robot != null) {
			Point p = new Point(frame.getWidth() / 2, frame.getHeight() / 2);
			SwingUtilities.convertPointToScreen(p, frame);
			robot.mouseMove(p.x, p.y);
			oldMouseX = frame.getWidth() / 2;
			oldMouseY = frame.getHeight() / 2;
		}
	}
	
	public void mouseDragged(MouseEvent arg0) {
	}
	
	public void mouseMoved(MouseEvent e) {
		Input.addMouseMovement(new Vector2(e.getX() - oldMouseX, e.getY() - oldMouseY));
		oldMouseX = e.getX();
		oldMouseY = e.getY();
	}
}