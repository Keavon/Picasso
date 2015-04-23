import java.util.*;
import java.io.*;

public class Model
{
	// Array of vertices and of face lists where each item contains three integers corresponding to the index of vertices
	private Face[] faces;
	private Vector3[] vertices;
	
	public Vector3 position;
	public Vector3 rotation;
	
	public Vector3 velocity;
	public Vector3 angularVelocity;
	
	public Model(String file) {
		loadObj(file);
		position = new Vector3();
		rotation = new Vector3();
		velocity = new Vector3();
		angularVelocity = new Vector3();
	}
	
	// Loads an OBJ file from a specified path into into this Model
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
			
			// Initialize the vertices and faces of this Model
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
	
	public String toString() {
		String string = "";
		for (int i = 0; i < faces.length; i++) {
			for (int p = 0; p < faces[i].getTriangleIndexes().length; p++) {
				string += vertices[faces[i].getTriangleIndexes()[p]] + ", ";
			}
			string += "#" + faces[i].getColor() + "\n";
		}
		return string;
	}
}
