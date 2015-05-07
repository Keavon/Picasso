package PicassoEngine;

import java.util.*;
import java.io.*;

public class Model extends GameObject
{
	// Array of all vertices in this model
	private Vector3[] vertices;
	// Array of all the faces in this model
	private Face[] faces;
	// Array containing the coordinate of the center of each face
	private Vector3[] faceCenters;
	
	// Says whether or not this object is a collider
	private boolean collides = false;
	
	// Says whether or not this object is a trigger region
	private boolean trigger = false;
	
	public Model(String file) {
		super(file);
		loadObj(file);
		findCentroids();
	}
	
	public Model(String file, Vector3 position) {
		super(file, position);
		loadObj(file);
		findCentroids();
	}
	
	public Model(String file, Vector3 position, Vector3 rotation) {
		super(file, position, rotation);
		loadObj(file);
		findCentroids();
	}
	
	public Vector3[] getVertices() {
		return vertices;
	}
	
	public Face[] getFaces() {
		return faces;
	}
	
	public Vector3[] getFaceCenters() {
		return faceCenters;
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
			faces = new Face[faceLists.size()];
			
			// Sets the vertices 
			for (int i = 0; i < vertices.length; i++) {
				vertices[i] = verts.get(i);
			}
			for (int i = 0; i < faces.length; i++) {
				faces[i] = faceLists.get(i);
			}
		} catch (IOException e) {
			// Catch error reading file and print the error message
			System.out.println(e.getMessage());
		}
	}
	
	public void findCentroids() {
		// Declare length
		faceCenters = new Vector3[faces.length];
		
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
		}
	}
	
	public String toString() {
		String string = "";
		for (Face face : faces) {
			for (int p = 0; p < face.getVertexIndexes().length; p++) {
				string += vertices[face.getVertexIndexes()[p]] + ", ";
			}
			string += "#" + face.getColor() + "\n";
		}
		return string;
	}
}
