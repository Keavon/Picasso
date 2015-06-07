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
	private double horizontalCenterPercentage;
	private double verticalCenterPercentage;
	private int horizontalOffset;
	private int verticalOffset;
	private boolean visible = true;
	
	public GUIElement(int depth, String imagePath, double xPercentage, double yPercentage, int width, int height, double horizontalCenterPercentage, double verticalCenterPercentage, int horizontalOffset, int verticalOffset) {
		this.depth = depth;
		this.xPercentage = xPercentage;
		this.yPercentage = yPercentage;
		this.horizontalCenterPercentage = horizontalCenterPercentage;
		this.verticalCenterPercentage = verticalCenterPercentage;
		this.horizontalOffset = horizontalOffset;
		this.verticalOffset = verticalOffset;
		
		image = null;
		try {
			image = ImageIO.read(new File("assets/" + imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (width < 0 && height < 0) {
			this.width = image.getWidth(null);
			this.height = image.getHeight(null);
		} else {
			this.width = width;
			this.height = height;
		}
	}
	
	public GUIElement(int depth, String imagePath, double xPercentage, double yPercentage, double horizontalCenterPercentage, double verticalCenterPercentage, int horizontalOffset, int verticalOffset) {
		this(depth, imagePath, xPercentage, yPercentage, -1, -1, horizontalCenterPercentage, verticalCenterPercentage, horizontalOffset, verticalOffset);
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
	
	public double getHorizontalCenterPercentage() {
		return horizontalCenterPercentage;
	}
	
	public double getVerticalCenterPercentage() {
		return verticalCenterPercentage;
	}
	
	public int getHorizontalOffset() {
		return horizontalOffset;
	}
	
	public int getVerticalOffset() {
		return verticalOffset;
	}
	
	public boolean mouseHover() {
		if (Application.isCursorVisible() && visible) {
			if (Input.getMouseX() < (xPercentage / 100 * Application.getWidth()) - (horizontalCenterPercentage / 100 * width) + horizontalOffset || Input.getMouseX() > (xPercentage / 100 * Application.getWidth()) + ((100 - horizontalCenterPercentage) / 100 * width) + horizontalOffset) {
				return false;
			} else if (Input.getMouseY() < (yPercentage / 100 * Application.getHeight()) - (verticalCenterPercentage / 100 * height) + verticalOffset || Input.getMouseY() > (yPercentage / 100 * Application.getHeight()) + ((100 - verticalCenterPercentage) / 100 * height) + verticalOffset) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	public boolean mouseDown() {
		return mouseHover() && Input.getKeyDown("Mouse0");
	}
	
	public boolean mouseUp() {
		return mouseHover() && Input.getKeyUp("Mouse0");
	}
	
	public boolean mouseClick() {
		return mouseHover() && Input.getKey("Mouse0");
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void hide() {
		visible = false;
	}
	
	public void show() {
		visible = true;
	}
}
