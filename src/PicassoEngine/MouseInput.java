package PicassoEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseMotionListener {
	private double oldMouseX;
	private double oldMouseY;
	private Canvas canvas;
	private Robot robot;
	
	public MouseInput(Canvas canvas) {
		this.canvas = canvas;
		canvas.addMouseMotionListener(this);
		
		oldMouseY = canvas.getWidth() / 2;
		oldMouseX = canvas.getHeight() / 2;
		
		try {
			robot = new java.awt.Robot();
		} catch (java.awt.AWTException ex) {
			ex.printStackTrace();
		}
	}
	
	public void recenterMouse() {
		if (robot != null) {
			Point p = new Point(canvas.getWidth() / 2, canvas.getHeight() / 2);
			SwingUtilities.convertPointToScreen(p, canvas);
			robot.mouseMove(p.x, p.y);
			oldMouseX = canvas.getWidth() / 2;
			oldMouseY = canvas.getHeight() / 2;
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		Input.addMouseMovement(new Vector2(e.getX() - oldMouseX, e.getY() - oldMouseY));
		oldMouseX = e.getX();
		oldMouseY = e.getY();
		Input.setMouseX(e.getX());
		Input.setMouseY(e.getY());
	}
	
	public void mouseMoved(MouseEvent e) {
		mouseDragged(e);
	}
}