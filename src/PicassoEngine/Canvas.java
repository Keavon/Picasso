package PicassoEngine;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
	private Frame frame;
	private MouseInput mouseListener;
	
	public Canvas(Frame frame) {
		this.frame = frame;
		KeyInput keyListener = new KeyInput();
		mouseListener = new MouseInput(frame.getFrame());
		addKeyListener(keyListener);
		setFocusable(true);
		repaint();
	}
	
	public void paintComponent(Graphics graphics) {
		frame.getScene().getRenderer().render(graphics);
	}
	
	// maybe it stutters because multiple repaint loops are being run at once
	
	public MouseInput getMouseListener() {
		return mouseListener;
	}
}