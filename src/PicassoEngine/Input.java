package PicassoEngine;

import java.util.HashMap;
import java.util.Map;

public class Input {
	private static Map<String, Boolean> keys = new HashMap<String, Boolean>();
	private static Map<String, Boolean> keysDown = new HashMap<String, Boolean>();
	public static Vector2 mouseMovement = new Vector2();
	private static int mouseScrollRotation = 0;
	
	//Pre: Key code to get
	//Post: Returns true if the queried key is down, false if up
	public static boolean getKey(String keyCode) {
		return keys.getOrDefault(keyCode, false);
	}
	
	//Pre: Key code to get
	//Post: Returns true if the queried key is down, false if up, for the first frame
	public static boolean getKeyDown(String keyCode) {
		boolean keyPushed = keysDown.getOrDefault(keyCode, false);
		if (!keyPushed && keyCode.equals("Space")) {
			keyPushed = keysDown.getOrDefault(" ", false);
		}
		return keyPushed;
	}
	
	//Pre: Key code to set
	//Post: Sets the key to down
	public static void setKeyDown(String keyCode) {
		keys.put(keyCode, true);
		keysDown.put(keyCode, true);
	}
	
	//Pre: Key code to set
	//Post: Sets the key to up
	public static void setKeyUp(String keyCode) {
		keys.put(keyCode, false);
		keysDown.put(keyCode, false);
	}
	
	//Pre: None
	//Post: Resets the currently keyDown keys so they don’t show as down the next frame
	public static void resetKeysDown() {
		keysDown.clear();
	}
	
	//Pre: Mouse button to set down from Java's weird mapping
	//Post: Adds a key down as Mouse0 (LMB), Mouse1 (RMB), or Mouse2 (MMB)
	public static void setMouseButtonDown(int key) {
		int buttonNumber;
		if (key == 2) buttonNumber = 2;
		else if (key == 3) buttonNumber = 1;
		else buttonNumber = 0;
		
		keys.put("Mouse" + buttonNumber, true);
		keysDown.put("Mouse" + buttonNumber, true);
	}
	
	//Pre: Mouse button to set up from Java's weird mapping
	//Post: Adds a key up as Mouse0 (LMB), Mouse1 (RMB), or Mouse2 (MMB)
	public static void setMouseButtonUp(int key) {
		int buttonNumber;
		if (key == 2) buttonNumber = 2;
		else if (key == 3) buttonNumber = 1;
		else buttonNumber = 0;
		
		keys.put("Mouse" + buttonNumber, false);
		keysDown.put("Mouse" + buttonNumber, false);
	}
	
	//Pre: None
	//Post: Sets the mouse movement back to zero
	public static void resetMouseMovement() {
		mouseMovement = new Vector2();
	}
	
	//Pre: Mouse movement change to add
	//Post: Adds the given mouse movement
	public static void addMouseMovement(Vector2 additionalMouseMovement) {
		mouseMovement.add(additionalMouseMovement);
	}
	
	//Pre: Rotation value
	//Post: Adds to the total rotation value
	public static void addScrollRotation(int rotation) {
		mouseScrollRotation += rotation;
	}
	
	//Pre: None
	//Post: Resets the total rotation value to zero (to be called after a frame)
	public static void resetScrollRotation() {
		mouseScrollRotation = 0;
	}
	
	//Pre: None
	//Post: Returns the scroll rotation
	public static int getMouseScrollRotation() {
		return mouseScrollRotation;
	}
}