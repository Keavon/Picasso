package PicassoEngine;

import java.util.HashMap;
import java.util.Map;

public class Input {
	private static Map<String, Boolean> keys = new HashMap<String, Boolean>();
	private static Map<String, Boolean> keysDown = new HashMap<String, Boolean>();
	public static Vector2 mouseMovement = new Vector2();
	private static int mouseScrollRotation = 0;
	
	public static boolean GetKey(String keyCode) {
		return keys.getOrDefault(keyCode, false);
	}
	
	public static void setKeyDown(String keyCode) {
		keys.put(keyCode, true);
		keysDown.put(keyCode, true);
	}
	
	public static void setKeyUp(String keyCode) {
		keys.put(keyCode, false);
		keysDown.put(keyCode, false);
	}
	
	public static boolean getKeyDown(String keyCode) {
		return keysDown.getOrDefault(keyCode, false);
	}
	
	public static void resetKeysDown() {
		keysDown.clear();
	}
	
	public static void setMouseButtonDown(int key) {
		int buttonNumber;
		if (key == 2) buttonNumber = 2;
		else if (key == 3) buttonNumber = 1;
		else buttonNumber = 0;
		
		keys.put("Mouse" + buttonNumber, true);
		keysDown.put("Mouse" + buttonNumber, true);
	}
	
	public static void setMouseButtonUp(int key) {
		int buttonNumber;
		if (key == 2) buttonNumber = 2;
		else if (key == 3) buttonNumber = 1;
		else buttonNumber = 0;
		
		keys.put("Mouse" + buttonNumber, false);
		keysDown.put("Mouse" + buttonNumber, false);
	}
	
	public static void resetMouseMovement() {
		mouseMovement = new Vector2();
	}
	
	public static void addMouseMovement(Vector2 additionalMouseMovement) {
		mouseMovement.add(additionalMouseMovement);
	}
	
	public static void addScrollRotation(int rotation) {
		mouseScrollRotation += rotation;
	}
	
	public static void resetScrollRotation() {
		mouseScrollRotation = 0;
	}
	
	public static int getMouseScrollRotation() {
		return mouseScrollRotation;
	}
}