package PicassoEngine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GUIElement {
	private int depth;
	private Image image;
	private double xPercentage;
	private double yPercentage;
	private int width;
	private int height;
	
	public GUIElement(int depth, String imagePath, double xPercentage, double yPercentage, int width, int height) {
		this.depth = depth;
		this.xPercentage = xPercentage;
		this.yPercentage = yPercentage;
		
		image = null;
		try {
			image = ImageIO.read(new File("assets/" + imagePath));
		} catch (IOException e) {
		}
		
		if (width < 0 && height < 0) {
			this.width = image.getWidth(null);
			this.height = image.getHeight(null);
		} else {
			this.width = width;
			this.height = height;
		}
	}
	
	public GUIElement(int depth, String imagePath, double xPercentage, double yPercentage) {
		this(depth, imagePath, xPercentage, yPercentage, -1, -1);
	}
	
	public int getDepth() {
		return depth;
	}
	
	public Image getImage() {
		return image;
	}
	
	public double getXPercentage() {
		return xPercentage;
	}
	
	public double getYPercentage() {
		return yPercentage;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
