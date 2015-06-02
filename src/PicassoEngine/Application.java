package PicassoEngine;

import javax.swing.*;
import java.awt.*;

public class Application {
	private static Frame frame;
	
	public Application(JFrame f) {
		frame = f;
	}
	
	public static void hideCursor() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		frame.setCursor(toolkit.createCustomCursor(toolkit.getImage(""), new Point(frame.getX(), frame.getY()), "img"));
	}
	
	public static void showCursor() {
		frame.setCursor(Cursor.getDefaultCursor());
	}
	
	public static void quit() {
		// Stop showing window
		frame.setVisible(false);
		
		// Destroy the window
		frame.dispose();
		
		// End the application process
		System.exit(0);
	}
	
	public static void fullscreen(boolean fullscreen) {
		if (fullscreen) {
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setUndecorated(true);
		} else {
			frame.setExtendedState(JFrame.NORMAL);
			frame.setUndecorated(false);
		}
	}
	
	public static void setResolution(int width, int height) {
		frame.setSize(width, height);
	}
	
	public static int getWidth() {
		return frame.getWidth();
	}
	
	public static int getHeight() {
		return frame.getHeight();
	}
}
