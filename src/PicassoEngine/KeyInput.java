package PicassoEngine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
	public KeyInput(Canvas canvas) {
		canvas.addKeyListener(this);
	}
	
	public void keyTyped(KeyEvent e) {
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) Input.setKeyDown("Space");
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) Input.setKeyDown("Left");
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) Input.setKeyDown("Right");
		else if (e.getKeyCode() == KeyEvent.VK_UP) Input.setKeyDown("Up");
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) Input.setKeyDown("Down");
		else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) Input.setKeyDown("Escape");
		else Input.setKeyDown(KeyEvent.getKeyText(e.getKeyCode()));
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) Input.setKeyUp("Space");
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) Input.setKeyUp("Left");
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) Input.setKeyUp("Right");
		else if (e.getKeyCode() == KeyEvent.VK_UP) Input.setKeyUp("Up");
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) Input.setKeyUp("Down");
		else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) Input.setKeyUp("Escape");
		else Input.setKeyUp(KeyEvent.getKeyText(e.getKeyCode()));
	}
}