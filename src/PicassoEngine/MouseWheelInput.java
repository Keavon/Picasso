package PicassoEngine;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheelInput implements MouseWheelListener {
	public MouseWheelInput(Canvas canvas) {
		canvas.addMouseWheelListener(this);
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		Input.addScrollRotation(e.getWheelRotation() * e.getScrollAmount());
	}
}