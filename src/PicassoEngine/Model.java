package PicassoEngine;

import java.util.*;
import java.io.*;

public class Model extends GameObject {
	// Array of all vertices in this model
	private Vector3[] vertices;
	// Array of all vertices in this model after transformation
	private Vector3[] transformedVertices;
	// Array of all the faces in this model
	private Face[] faces;
	// Array containing the coordinate of the center of each face
	private Vector3[] faceCenters;
	// Array containing the coordinate of the center of each face after transformation
	private Vector3[] transformedFaceCenters;
	// Says whether or not this object is a collider
	private boolean collides = false;
	// Says whether or not this object is a trigger region
	private boolean trigger = false;
	// Order to paint where the higher value is painted later (on top)
	private int paintOrder = 0;
	// Determines if the object is rendered
	private boolean visible = true;
	
	public Model(String file) {
		this(file, new Vector3(), new Vector3(), new Vector3(1, 1, 1));
	}
	
	public Model(String file, Vector3 position) {
		this(file, position, new Vector3(), new Vector3(1, 1, 1));
	}
	
	public Model(String file, Vector3 position, Vector3 rotation) {
		this(file, position, rotation, new Vector3(1, 1, 1));
	}
	
	public Model(String file, Vector3 position, Vector3 rotation, Vector3 scale) {
		super(file, position, rotation, scale);
		loadObj(file);
		findCentroids();
		setRotation(rotation);
	}
	
	public int getPaintOrder() {
		return paintOrder;
	}
	
	public void setPaintOrder(int paintOrder) {
		this.paintOrder = paintOrder;
	}
	
	public boolean isCollides() {
		return collides;
	}
	
	public void setCollides(boolean collides) {
		this.collides = collides;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public Vector3[] getVertices() {
		if (super.getPosition().x == 0 && super.getPosition().y == 0 && super.getPosition().z == 0) {
			return transformedVertices;
		} else {
			Vector3[] result = new Vector3[transformedVertices.length];
			for (int i = 0; i < transformedVertices.length; i++) {
				result[i] = transformedVertices[i].getSum(super.getPosition());
			}
			return result;
		}
	}
	
	public Face[] getFaces() {
		return faces;
	}
	
	public Vector3[] getFaceCenters() {
		if (super.getPosition().x == 0 && super.getPosition().y == 0 && super.getPosition().z == 0) {
			return transformedFaceCenters;
		} else {
			Vector3[] result = new Vector3[transformedFaceCenters.length];
			for (int i = 0; i < transformedFaceCenters.length; i++) {
				result[i] = transformedFaceCenters[i].getSum(super.getPosition());
			}
			return result;
		}
	}
	
	public void setRotation(Vector3 newRotation) {
		super.setRotation(newRotation);
		
		// Rotate vertices
		for (int i = 0; i < vertices.length; i++) {
//			transformedVertices[i].x = vertices[i].x * (Math.cos(super.getRotation().y) * Math.cos(super.getRotation().z)) + vertices[i].y * (Math.cos(super.getRotation().x) * Math.sin(super.getRotation().z) + Math.sin(super.getRotation().x) * Math.sin(super.getRotation().y) * Math.cos(super.getRotation().z)) + vertices[i].z * (Math.sin(super.getRotation().x) * Math.sin(super.getRotation().z) - Math.cos(super.getRotation().x) * Math.sin(super.getRotation().y) * Math.cos(super.getRotation().z));
//			transformedVertices[i].y = vertices[i].x * (-Math.cos(super.getRotation().y) * Math.sin(super.getRotation().z)) + vertices[i].y * (Math.cos(super.getRotation().x) * Math.cos(super.getRotation().z) - Math.sin(super.getRotation().x) * Math.sin(super.getRotation().y) * Math.sin(super.getRotation().z)) + vertices[i].z * (Math.sin(super.getRotation().x) * Math.cos(super.getRotation().z) + Math.cos(super.getRotation().x) * Math.sin(super.getRotation().y) * Math.sin(super.getRotation().z));
//			transformedVertices[i].z = vertices[i].x * (Math.sin(super.getRotation().y)) + vertices[i].y * (-Math.sin(super.getRotation().x) * Math.cos(super.getRotation().y)) + vertices[i].z * (Math.cos(super.getRotation().x) * Math.cos(super.getRotation().y));
			
			// Rotate
			transformedVertices[i] = getRotation().rotatePoint(vertices[i]);
			
			// Scale
			transformedVertices[i] = transformedVertices[i].getProduct(getScale());
		}
		
		// Rotate face centroids
		for (int i = 0; i < faceCenters.length; i++) {
//			transformedFaceCenters[i].x = faceCenters[i].x * (Math.cos(super.getRotation().y) * Math.cos(super.getRotation().z)) + faceCenters[i].y * (Math.cos(super.getRotation().x) * Math.sin(super.getRotation().z) + Math.sin(super.getRotation().x) * Math.sin(super.getRotation().y) * Math.cos(super.getRotation().z)) + faceCenters[i].z * (Math.sin(super.getRotation().x) * Math.sin(super.getRotation().z) - Math.cos(super.getRotation().x) * Math.sin(super.getRotation().y) * Math.cos(super.getRotation().z));
//			transformedFaceCenters[i].y = faceCenters[i].x * (-Math.cos(super.getRotation().y) * Math.sin(super.getRotation().z)) + faceCenters[i].y * (Math.cos(super.getRotation().x) * Math.cos(super.getRotation().z) - Math.sin(super.getRotation().x) * Math.sin(super.getRotation().y) * Math.sin(super.getRotation().z)) + faceCenters[i].z * (Math.sin(super.getRotation().x) * Math.cos(super.getRotation().z) + Math.cos(super.getRotation().x) * Math.sin(super.getRotation().y) * Math.sin(super.getRotation().z));
//			transformedFaceCenters[i].z = faceCenters[i].x * (Math.sin(super.getRotation().y)) + faceCenters[i].y * (-Math.sin(super.getRotation().x) * Math.cos(super.getRotation().y)) + faceCenters[i].z * (Math.cos(super.getRotation().x) * Math.cos(super.getRotation().y));
			
			// Rotate
			transformedFaceCenters[i] = getRotation().rotatePoint(faceCenters[i]);
			
			// Scale
			transformedFaceCenters[i] = transformedFaceCenters[i].getProduct(super.getScale());
		}
	}
	
	public void setRotation(Quaternion newRotation) {
		super.setRotation(newRotation);
		// Rotate vertices
		for (int i = 0; i < vertices.length; i++) {
			// Rotate
			transformedVertices[i] = newRotation.rotatePoint(vertices[i]);
			
			// Scale
			transformedVertices[i] = transformedVertices[i].getProduct(getScale());
		}
		
		// Rotate face centroids
		for (int i = 0; i < faceCenters.length; i++) {
			// Rotate
			transformedFaceCenters[i] = newRotation.rotatePoint(faceCenters[i]);
			
			// Scale
			transformedFaceCenters[i] = transformedFaceCenters[i].getProduct(super.getScale());
		}
	}
	
	// Loads an OBJ file from a specified path into into this PicassoEngine.Model
	public void loadObj(String file) {
		ArrayList<Vector3> verts = new ArrayList<Vector3>();
		ArrayList<Face> faceLists = new ArrayList<Face>();
		String currentColor = "";
		
		try {
			// Read the file
			Scanner inputFile = new Scanner(new File("assets/" + file));
			
			// Go through each line
			while (inputFile.hasNext()) {
				String line = inputFile.nextLine();
				
				if (line.startsWith("v ")) {
					// Remove "v " from start
					line = line.substring(2);
					
					// Get the X, Y, Z points
					double x = Double.parseDouble(line.substring(0, line.indexOf(" ")));
					line = line.substring(line.indexOf(" ") + 1);
					double y = Double.parseDouble(line.substring(0, line.indexOf(" ")));
					line = line.substring(line.indexOf(" ") + 1);
					double z = Double.parseDouble(line);
					
					// Add the vertex to the list of vertices
					verts.add(new Vector3(x, y, z));
				} else if (line.startsWith("usemtl ")) {
					currentColor = line.substring(7);
				} else if (line.startsWith("f ")) {
					// Remove "f " from start
					line = line.substring(2);
					
					ArrayList<Integer> points = new ArrayList<Integer>();
					
					while (line.contains(" ")) {
						points.add(Integer.parseInt(line.substring(0, line.indexOf(" "))) - 1);
						line = line.substring(line.indexOf(" ") + 1);
					}
					
					points.add(Integer.parseInt(line) - 1);
					
					int[] pointsArray = new int[points.size()];
					
					for (int i = 0; i < pointsArray.length; i++) {
						pointsArray[i] = points.get(i);
					}
					
					// Add the face to the list of faces
					faceLists.add(new Face(pointsArray, currentColor));
				}
			}
			
			// Initialize the vertices and faces of this PicassoEngine.Model
			vertices = new Vector3[verts.size()];
			transformedVertices = new Vector3[vertices.length];
			faces = new Face[faceLists.size()];
			
			// Sets the vertices 
			for (int i = 0; i < vertices.length; i++) {
				Vector3 vertex = verts.get(i);
				vertices[i] = vertex;
				transformedVertices[i] = new Vector3(vertex.x, vertex.y, vertex.z);
			}
			for (int i = 0; i < faces.length; i++) {
				faces[i] = faceLists.get(i);
			}
		} catch (IOException e) {
			// Catch error reading file and print the error message
			System.err.println(e.getMessage());
		}
	}
	
	public void findCentroids() {
		// Declare length
		faceCenters = new Vector3[faces.length];
		transformedFaceCenters = new Vector3[faces.length];
		
		// Go through every face
		for (int face = 0; face < faces.length; face++) {
			int[] verts = faces[face].getVertexIndexes();
			
			double centerX = 0;
			double centerY = 0;
			double centerZ = 0;
			
			// Find area of polygon
			double twiceAreaXY = 0;
			double twiceAreaYZ = 0;
			double twiceAreaZX = 0;
			
			// Find areas
			for (int i = 0; i < verts.length - 1; i++) {
				twiceAreaXY += vertices[verts[i]].x * vertices[verts[i + 1]].y - vertices[verts[i + 1]].x * vertices[verts[i]].y;
				twiceAreaYZ += vertices[verts[i]].y * vertices[verts[i + 1]].z - vertices[verts[i + 1]].y * vertices[verts[i]].z;
				twiceAreaZX += vertices[verts[i]].z * vertices[verts[i + 1]].x - vertices[verts[i + 1]].z * vertices[verts[i]].x;
			}
			// Final case that loops from the last to the first vertex in the array
			twiceAreaXY += vertices[verts[verts.length - 1]].x * vertices[verts[0]].y - vertices[verts[0]].x * vertices[verts[verts.length - 1]].y;
			twiceAreaYZ += vertices[verts[verts.length - 1]].y * vertices[verts[0]].z - vertices[verts[0]].y * vertices[verts[verts.length - 1]].z;
			twiceAreaZX += vertices[verts[verts.length - 1]].z * vertices[verts[0]].x - vertices[verts[0]].z * vertices[verts[verts.length - 1]].x;
			
			// Find center of XY
			if (Math.abs(twiceAreaXY) > 0.0001) {
				for (int i = 0; i < verts.length - 1; i++) {
					double xy = vertices[verts[i]].x * vertices[verts[i + 1]].y - vertices[verts[i + 1]].x * vertices[verts[i]].y;
					centerX += (vertices[verts[i]].x + vertices[verts[i + 1]].x) * xy;
					centerY += (vertices[verts[i]].y + vertices[verts[i + 1]].y) * xy;
				}
				double xy = vertices[verts[verts.length - 1]].x * vertices[verts[0]].y - vertices[verts[0]].x * vertices[verts[verts.length - 1]].y;
				centerX += (vertices[verts[verts.length - 1]].x + vertices[verts[0]].x) * xy;
				centerY += (vertices[verts[verts.length - 1]].y + vertices[verts[0]].y) * xy;
				
				// Scale
				centerX /= twiceAreaXY * 3;
				centerY /= twiceAreaXY * 3;
			}
			
			// Find center of YZ
			if (Math.abs(twiceAreaYZ) > 0.0001) {
				centerY = 0;
				for (int i = 0; i < verts.length - 1; i++) {
					double yz = vertices[verts[i]].y * vertices[verts[i + 1]].z - vertices[verts[i + 1]].y * vertices[verts[i]].z;
					centerY += (vertices[verts[i]].y + vertices[verts[i + 1]].y) * yz;
					centerZ += (vertices[verts[i]].z + vertices[verts[i + 1]].z) * yz;
				}
				double yz = vertices[verts[verts.length - 1]].y * vertices[verts[0]].z - vertices[verts[0]].y * vertices[verts[verts.length - 1]].z;
				centerY += (vertices[verts[verts.length - 1]].y + vertices[verts[0]].y) * yz;
				centerZ += (vertices[verts[verts.length - 1]].z + vertices[verts[0]].z) * yz;
				
				// Scale
				centerY /= twiceAreaYZ * 3;
				centerZ /= twiceAreaYZ * 3;
			}
			
			// Find center of YZ
			if (Math.abs(twiceAreaZX) > 0.0001) {
				centerZ = 0;
				centerX = 0;
				for (int i = 0; i < verts.length - 1; i++) {
					double zx = vertices[verts[i]].z * vertices[verts[i + 1]].x - vertices[verts[i + 1]].z * vertices[verts[i]].x;
					centerZ += (vertices[verts[i]].z + vertices[verts[i + 1]].z) * zx;
					centerX += (vertices[verts[i]].x + vertices[verts[i + 1]].x) * zx;
				}
				double zx = vertices[verts[verts.length - 1]].z * vertices[verts[0]].x - vertices[verts[0]].z * vertices[verts[verts.length - 1]].x;
				centerZ += (vertices[verts[verts.length - 1]].z + vertices[verts[0]].z) * zx;
				centerX += (vertices[verts[verts.length - 1]].x + vertices[verts[0]].x) * zx;
				
				// Scale
				centerZ /= twiceAreaZX * 3;
				centerX /= twiceAreaZX * 3;
			}
			
			// Set center position of face
			faceCenters[face] = new Vector3(centerX, centerY, centerZ);
			
			// X doesn't exist
			if (Math.abs(twiceAreaZX) <= 0.0001 && Math.abs(twiceAreaXY) <= 0.0001) {
				faceCenters[face].x = vertices[verts[0]].x;
			}
			// Y doesn't exist
			if (Math.abs(twiceAreaXY) <= 0.0001 && Math.abs(twiceAreaYZ) <= 0.0001) {
				faceCenters[face].y = vertices[verts[0]].y;
			}
			// Z doesn't exist
			if (Math.abs(twiceAreaYZ) <= 0.0001 && Math.abs(twiceAreaZX) <= 0.0001) {
				faceCenters[face].z = vertices[verts[0]].z;
			}
			
			transformedFaceCenters[face] = new Vector3(faceCenters[face].x, faceCenters[face].y, faceCenters[face].z);
		}
	}
	
	public String toString() {
		return getName();
	}
}