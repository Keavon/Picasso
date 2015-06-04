package PicassoEngine;

import javax.swing.*;
import java.awt.*;

public class Application {
	private static Frame frame;
	private static Canvas canvas;
	private static PicassoEngine engine;
	private static boolean notFullscreen = true;
	
	public Application(JFrame f, Canvas c, PicassoEngine e) {
		frame = f;
		canvas = c;
		engine = e;
	}
	
	public static void hideCursor() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		frame.setCursor(toolkit.createCustomCursor(toolkit.getImage(""), new Point(frame.getX(), frame.getY()), "img"));
		canvas.setRecenterMouse(true);
	}
	
	public static void showCursor() {
		frame.setCursor(Cursor.getDefaultCursor());
		canvas.setRecenterMouse(false);
	}
	
	public static void quit() {
		// Stop showing window
		frame.setVisible(false);
		
		// Destroy the window
		frame.dispose();
		
		// End the application process
		System.exit(0);
	}
	
	public static void goFullscreen() {
		if (notFullscreen) {
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setUndecorated(true);
			notFullscreen = false;
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
