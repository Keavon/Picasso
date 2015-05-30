package PicassoEngine;

import javax.swing.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheelInput implements MouseWheelListener {
	public MouseWheelInput(JFrame frame) {
		frame.addMouseWheelListener(this);
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		Input.addScrollRotation(e.getWheelRotation() * e.getScrollAmount());
	}
}