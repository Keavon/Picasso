package PicassoEngine;

import java.util.HashMap;
import java.util.Map;

public class Input {
	private static Map<String, Boolean> keys = new HashMap<String, Boolean>();
	public static Vector2 mouseMovement = new Vector2();
	
	public static boolean GetKey(String keyCode) {
		return keys.getOrDefault(keyCode, false);
	}
	
	public static void setKeyDown(String keyCode) {
		keys.put(keyCode, true);
	}
	
	public static void setKeyUp(String keyCode) {
		keys.put(keyCode, false);
	}
	
	public static void setMouseMovement(Vector2 newMouseMovement) {
		mouseMovement = newMouseMovement;
	}
	
	public static void resetMouseMovement() {
		mouseMovement = new Vector2();
	}
	
	public static void addMouseMovement(Vector2 additionalMouseMovement) {
		mouseMovement.add(additionalMouseMovement);
	}
}