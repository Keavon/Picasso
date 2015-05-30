package PicassoEngine;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SkyBox {
	private BufferedImage skyImage;
	private Frame frame;
	
	public SkyBox(String image, Frame frame) {
		this.frame = frame;
		try {
			this.skyImage = ImageIO.read(new File(image));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void drawBackground(Graphics2D context, Camera camera) {
		int h = frame.getFrame().getHeight();
		int w = frame.getFrame().getWidth();
		int viewPortWidth = skyImage.getWidth() / 4;
		int viewPortHeight = skyImage.getHeight() / 4;
		if (camera == null) {
			return;
		}
		
		Vector3 camRot = camera.getRotation();
		double rotX = camRot.y + 3 * Math.PI / 4;
		rotX %= 2 * Math.PI;
		if (rotX < 0) {
			rotX = Math.PI * 2 + rotX;
		}
		double xCoord = rotX / (2 * Math.PI) * skyImage.getWidth();
		
		double rotY = camRot.x - Math.PI / 1.999999;
		if (rotY < 0) {
			rotY = Math.abs(Math.PI + rotY);
		}
		
		double yCoord = rotY / (1 * Math.PI) * skyImage.getHeight() + skyImage.getHeight() / 4;
		
		if (xCoord + viewPortWidth / 2 > skyImage.getWidth()) {
			double leftSideWidth = (skyImage.getWidth() - xCoord + viewPortWidth / 2);
			
			context.drawImage(skyImage.getSubimage((int) (skyImage.getWidth() - leftSideWidth), (int) yCoord / 2 - viewPortHeight / 2, (int) leftSideWidth, viewPortHeight), 0, 0, (int) (w * (leftSideWidth / viewPortWidth)), h, null);
			context.drawImage(skyImage.getSubimage(0, (int) yCoord / 2 - viewPortHeight / 2, 1 + (int) (viewPortWidth - leftSideWidth), viewPortHeight), (int) (w * (leftSideWidth / viewPortWidth)), 0, 1 + w - (int) (w * (leftSideWidth / viewPortWidth)), h, null);
		} else if (xCoord < viewPortWidth / 2) {
			double startingPixel = w - (w * ((xCoord + viewPortWidth / 2) / viewPortWidth));
			
			context.drawImage(skyImage.getSubimage(0, (int) yCoord / 2 - viewPortHeight / 2, viewPortWidth - (int) (viewPortWidth / 2 - xCoord), viewPortHeight), (int) startingPixel, 0, (int) (w - startingPixel), h, null);
			int newW = viewPortWidth - (int) (viewPortWidth / 2 - xCoord);
			int wOfNew = viewPortWidth - newW;
			if (wOfNew != 0) {
				context.drawImage(skyImage.getSubimage(skyImage.getWidth() - wOfNew, (int) yCoord / 2 - viewPortHeight / 2, wOfNew, viewPortHeight), 0, 0, w - (int) (w - startingPixel), h, null);
			}
		} else {
			context.drawImage(skyImage.getSubimage((int) xCoord - viewPortWidth / 2, (int) yCoord / 2 - viewPortHeight / 2, viewPortWidth, viewPortHeight), 0, 0, w, h, null);
		}
	}
}
