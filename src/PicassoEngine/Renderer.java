package PicassoEngine;

import java.awt.*;
import java.awt.image.VolatileImage;
import java.util.*;

public class Renderer {
	private boolean render = true;
	private Frame frame;
	private Camera camera;
	Graphics2D context;
	private int FPS = 0;
	private long lastShown = 0;
	ArrayList<Vector3> debugLineStart = new ArrayList<Vector3>();
	ArrayList<Vector3> debugLineEnd = new ArrayList<Vector3>();
	ArrayList<String> debugLineColor = new ArrayList<String>();
	
	public Renderer(Frame frame) {
		this.frame = frame;
	}
	
	public void startRender() {
		render = true;
	}
	
	public void stopRender() {
		render = false;
	}
	
	public void render(Graphics graphics) {
		if (render) {
			// Set the time at the very start of the frame
			long lastLoopTime = System.nanoTime();
			
			// Initialize frame and context
			VolatileImage frame = this.frame.getFrame().createVolatileImage(this.frame.getFrame().getWidth(), this.frame.getFrame().getHeight());
			context = frame.createGraphics();
			
			// Update - Run game logic
			this.frame.getScene().callUpdate();
			
			// LateUpdate - Run additional game logic
			this.frame.getScene().callLateUpdate();
			
			// Reset mouse position and scroll rotation
			Input.resetScrollRotation();
			Input.resetMouseMovement();
			this.frame.getCanvas().recenterMouse();
			Input.resetKeysDown();
			
			// Draw skybox background
			if (this.frame.getScene().activeSky != null) {
				this.frame.getScene().activeSky.drawBackground(context, camera);
			}
			
			// Render the camera view to the context
			drawCameraView();
			
			// Paint frame to canvas
			graphics.drawImage(frame, 0, 0, this.frame.getFrame());
			
			// Repaint the window which then calls the next frame
			this.frame.getFrame().repaint();
			
			// Slow down if frames are rendering too fast
			if ((System.nanoTime() - lastLoopTime) / 1000000000.0 < 0.016) {
				try {
					Thread.sleep(15 - (System.nanoTime() - lastLoopTime) / 1000000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			// Set deltaTime as the length of time in seconds that this frame took
			Time.deltaTime = (System.nanoTime() - lastLoopTime) / 1000000000.0;
			
			// Log FPS
			FPS++;
			if (lastShown == 0) {
				lastShown = System.nanoTime();
			}
			if (System.nanoTime() - lastShown >= 1000000000) {
				//System.out.println(FPS);
				lastShown = System.nanoTime();
				FPS = 0;
			}
		}
	}
	
	public void drawCameraView() {
		camera = frame.getScene().getActiveCamera();
		if (camera == null) {
			return;
		}
		ArrayList<GameObject> objects = frame.getScene().getGameObjects();
		ArrayList<Model> models = new ArrayList<Model>();
		for (GameObject object : objects) {
			if (object instanceof Model) {
				models.add((Model) object);
			}
		}
		
		// All the polygons in all the models
		int totalFaces = 0;
		for (Model model : models) {
			totalFaces += model.getFaces().length;
		}
		ArrayList<Polygon2D> polygons = new ArrayList<Polygon2D>(totalFaces);
		int polygonIndex = 0;
		
		// Add every face in every model to the list of polygons
		for (Model model : models) {
			// Faces
			Face[] faces = model.getFaces();
			
			// Vertices
			Vector3[] vertices = model.getVertices();
			Vector3[] projectedVertices = new Vector3[vertices.length];
			
			// PicassoEngine.Face centers
			Vector3[] centroids = model.getFaceCenters();
			double[] centroidDepths = new double[centroids.length];
			
			// Project vertices and put them in a corresponding screen space array
			for (int vertex = 0; vertex < vertices.length; vertex++) {
				
				projectedVertices[vertex] = project(vertices[vertex]);
				
			}
			
			// Add all the vertices in all the faces in this model to a polygons ArrayList
			for (int face = 0; face < faces.length; face++) {
				boolean onScreen = false;
				int[] vertexIndexes = faces[face].getVertexIndexes();
				Vector2[] faceVertices = new Vector2[vertexIndexes.length];
				
				for (int i = 0; i < vertexIndexes.length; i++) {
					// Add the projected vertex as a screen point in the 2D screen face
					faceVertices[i] = new Vector2(projectedVertices[vertexIndexes[i]].x, projectedVertices[vertexIndexes[i]].y);
					
					// Check if it's on screen
					if (faceVertices[i].x > 0 && faceVertices[i].x < frame.getFrame().getWidth() && faceVertices[i].y > 0 && faceVertices[i].y < frame.getFrame().getHeight() && projectedVertices[vertexIndexes[i]].z > 0) {
						onScreen = true;
					}
				}
				
				if (onScreen) {
					// Add this face as a polygon in the final array
					Vector3 v1 = vertices[vertexIndexes[0]].getDifference(vertices[vertexIndexes[1]]);
					Vector3 v2 = vertices[vertexIndexes[2]].getDifference(vertices[vertexIndexes[1]]);
					double brightness = v1.getCrossProduct(v2).getAngleDifference(new Vector3(5, 10, 0)) / Math.PI;
					polygons.add(new Polygon2D(faceVertices, project(centroids[face]), faces[face].getColor(), brightness));
				}
			}
		}
		
		Collections.sort(polygons, new Comparator<Polygon2D>() {
			public int compare(Polygon2D p1, Polygon2D p2) {
				if (p1.getProjectedCentroid().z > p2.getProjectedCentroid().z) {
					return -1;
				} else if (p1.getProjectedCentroid().z < p2.getProjectedCentroid().z) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		
		// Draw each polygon
		for (Polygon2D polygon : polygons) {
			int[] x = new int[polygon.getPoints().length];
			int[] y = new int[x.length];
			
			for (int point = 0; point < x.length; point++) {
				x[point] = Math.round((float) polygon.getPoints()[point].x);
				y[point] = Math.round((float) polygon.getPoints()[point].y);
			}
			
			context.setColor(Color.decode("#" + polygon.getColor()));
			context.fillPolygon(x, y, x.length);
//			context.setColor(Color.black); // Set color to black for the below options
//			context.drawPolygon(x, y, x.length); // Draw wireframe outline
//			context.drawOval((int) polygon.getProjectedCentroid().x, (int) polygon.getProjectedCentroid().y, 10, 10); // Draw centroid used in z-sorting
		}
		
		// Draw debug lines
		for (int i = 0; i < debugLineStart.size(); i++) {
			Vector3 start = project(debugLineStart.get(i));
			Vector3 end = project(debugLineEnd.get(i));
			context.setColor(Color.decode("#" + debugLineColor.get(i)));
			context.drawLine((int) start.x, (int) start.y, (int) end.x, (int) end.y);
		}
	}
	
	public void addDebugLine(Vector3 start, Vector3 end, String color) {
		debugLineStart.add(start);
		debugLineEnd.add(end);
		debugLineColor.add(color);
	}
	
	public void clearDebugLines() {
		debugLineStart.clear();
		debugLineEnd.clear();
		debugLineColor.clear();
	}
	
	private Vector3 project(Vector3 point) {
		Vector3 point3d = new Vector3(point.x, point.y, point.z);
		
		Vector3 screenPoint = new Vector3();
		
		screenPoint.x = Math.cos(camera.getRotation().y) * (Math.sin(camera.getRotation().z) * (point3d.y - camera.getPosition().y) + Math.cos(camera.getRotation().z) * (point3d.x - camera.getPosition().x)) - Math.sin(camera.getRotation().y) * (point3d.z - camera.getPosition().z);
		
		screenPoint.y = Math.sin(camera.getRotation().x) * (Math.cos(camera.getRotation().y) * (point3d.z - camera.getPosition().z) + Math.sin(camera.getRotation().y) * (Math.sin(camera.getRotation().z) * (point3d.y - camera.getPosition().y) +
				Math.cos(camera.getRotation().z) * (point3d.x - camera.getPosition().x))) + Math.cos(camera.getRotation().x) * (Math.cos(camera.getRotation().z) * (point3d.y - camera.getPosition().y) - Math.sin(camera.getRotation().z) * (point3d.x - camera.getPosition().x));
		
		screenPoint.z = Math.cos(camera.getRotation().x) * (Math.cos(camera.getRotation().y) * (point3d.z - camera.getPosition().z) + Math.sin(camera.getRotation().y) * (Math.sin(camera.getRotation().z) * (point3d.y - camera.getPosition().y) +
				Math.cos(camera.getRotation().z) * (point3d.x - camera.getPosition().x))) - Math.sin(camera.getRotation().x) * (Math.cos(camera.getRotation().z) * (point3d.y - camera.getPosition().y) - Math.sin(camera.getRotation().z) * (point3d.x - camera.getPosition().x));
		
		Vector3 newPoint = new Vector3();
		
		double aspectRatio = (double) frame.getFrame().getWidth() / frame.getFrame().getHeight();
		double ez = 1 / Math.tan(camera.getFov() / 2);
		double x = (screenPoint.x - .5) * (ez / screenPoint.z) / aspectRatio;
		double y = -(screenPoint.y - .5) * (ez / screenPoint.z);
		
		newPoint.x = (int) (x * frame.getFrame().getWidth() + frame.getFrame().getWidth() / 2);
		newPoint.y = (int) (y * frame.getFrame().getHeight() + frame.getFrame().getHeight() / 2);
		newPoint.z = screenPoint.z;
		
		return newPoint;
	}
}