package PicassoEngine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
	public void keyTyped(KeyEvent e) {
	}
	
	public void keyPressed(KeyEvent e) {
		Input.setKeyDown(KeyEvent.getKeyText(e.getKeyCode()));
	}
	
	public void keyReleased(KeyEvent e) {
		Input.setKeyUp(KeyEvent.getKeyText(e.getKeyCode()));
	}
}