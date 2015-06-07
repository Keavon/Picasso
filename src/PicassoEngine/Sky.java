package PicassoEngine;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sky {
	private BufferedImage skyImage;
	
	public Sky(String image) {
		try {
			this.skyImage = ImageIO.read(new File(image));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void drawBackground(Graphics2D context, Camera camera) {
		int viewPortWidth = skyImage.getWidth() / 4;
		int viewPortHeight = skyImage.getHeight() / 4;
		if (camera == null) {
			return;
		}
		
		Vector3 camRot = camera.getRotation().getEulerAngles();
		// Fix flipped orientations
		if (Math.abs(camRot.z) > 0.01) {
			camRot.x += Math.PI;
			if (Math.abs(camRot.x - Math.PI * 2) < 0.01) {
				camRot.x = 0;
			}
			camRot.y = Math.PI - camRot.y;
			camRot.z = 0;
		}

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
			
			context.drawImage(skyImage.getSubimage((int) (skyImage.getWidth() - leftSideWidth), (int) yCoord / 2 - viewPortHeight / 2, (int) leftSideWidth, viewPortHeight), 0, 0, (int) (Application.getWidth() * (leftSideWidth / viewPortWidth)), Application.getHeight(), null);
			context.drawImage(skyImage.getSubimage(0, (int) yCoord / 2 - viewPortHeight / 2, 1 + (int) (viewPortWidth - leftSideWidth), viewPortHeight), (int) (Application.getWidth() * (leftSideWidth / viewPortWidth)), 0, 1 + Application.getWidth() - (int) (Application.getWidth() * (leftSideWidth / viewPortWidth)), Application.getHeight(), null);
		} else if (xCoord < viewPortWidth / 2) {
			double startingPixel = Application.getWidth() - (Application.getWidth() * ((xCoord + viewPortWidth / 2) / viewPortWidth));
			
			context.drawImage(skyImage.getSubimage(0, (int) yCoord / 2 - viewPortHeight / 2, viewPortWidth - (int) (viewPortWidth / 2 - xCoord), viewPortHeight), (int) startingPixel, 0, (int) (Application.getWidth() - startingPixel), Application.getHeight(), null);
			int newW = viewPortWidth - (int) (viewPortWidth / 2 - xCoord);
			int wOfNew = viewPortWidth - newW;
			if (wOfNew != 0) {
				context.drawImage(skyImage.getSubimage(skyImage.getWidth() - wOfNew, (int) yCoord / 2 - viewPortHeight / 2, wOfNew, viewPortHeight), 0, 0, Application.getWidth() - (int) (Application.getWidth() - startingPixel), Application.getHeight(), null);
			}
		} else {
			context.drawImage(skyImage.getSubimage((int) xCoord - viewPortWidth / 2, (int) yCoord / 2 - viewPortHeight / 2, viewPortWidth, viewPortHeight), 0, 0, Application.getWidth(), Application.getHeight(), null);
		}
	}
}
