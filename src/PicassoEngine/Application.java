package PicassoEngine;

import javax.swing.*;
import java.awt.*;

public class Application {
	private static Frame frame;
	private static Canvas canvas;
	private static PicassoEngine engine;
	private static boolean notFullscreen = true;
	
	// Pre: Frame, canvas, and engine
	//Post: Stores the references
	public Application(JFrame f, Canvas c, PicassoEngine e) {
		frame = f;
		canvas = c;
		engine = e;
	}
	
	//Pre: None
	//Post: Turns the cursor invisible and makes mouse stay centered
	public static void hideCursor() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		frame.setCursor(toolkit.createCustomCursor(toolkit.getImage(""), new Point(frame.getX(), frame.getY()), "img"));
		canvas.setRecenterMouse(true);
	}
	
	//Pre: Cursor is hidden
	//Post: Makes the cursor visible and stops recentering it
	public static void showCursor() {
		frame.setCursor(Cursor.getDefaultCursor());
		canvas.setRecenterMouse(false);
	}
	
	//Pre: None
	//Post: Quits the game
	public static void quit() {
		// Stop showing window
		frame.setVisible(false);
		
		// Destroy the window
		frame.dispose();
		
		// End the application process
		System.exit(0);
	}
	
	//Pre: Game is in windowed mode (otherwise nothing happens)
	//Post: Game turns fullscreen if it was not already
	public static void goFullscreen() {
		if (notFullscreen) {
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setUndecorated(true);
			notFullscreen = false;
		}
	}
	
	//Pre: Width and height
	//Post: Sets the window to that resolution when windowed
	public static void setResolution(int width, int height) {
		frame.setSize(width, height);
	}
	
	//Pre: None
	//Post: Returns the width of the window
	public static int getWidth() {
		return frame.getWidth();
	}
	
	//Pre: None
	//Post: Returns the height of the window
	public static int getHeight() {
		return frame.getHeight();
	}
}
