package PicassoEngine;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseClickInput implements MouseListener {
	public MouseClickInput(JFrame frame) {
		frame.addMouseListener(this);
	}
	
	public void mouseClicked(MouseEvent e) {
	}
	
	public void mousePressed(MouseEvent e) {
		Input.setMouseButtonDown(e.getButton());
	}
	
	public void mouseReleased(MouseEvent e) {
		Input.setMouseButtonUp(e.getButton());
	}
	
	public void mouseEntered(MouseEvent e) {
	}
	
	public void mouseExited(MouseEvent e) {
	}
}