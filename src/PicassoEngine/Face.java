package PicassoEngine;

public class Face {
	private int[] vertexIndexes;
	private String color;
	
	//Pre: Array of indexes of the vertices for the face and the color for the face
	//Post: Stores the arguments
	public Face(int[] triangleIndexes, String color) {
		this.vertexIndexes = triangleIndexes;
		this.color = color;
	}
	
	//Pre: None
	//Post: Returns the array of vertex indexes
	public int[] getVertexIndexes() {
		return vertexIndexes;
	}
	
	//Pre: None
	//Post: Returns the color of this face
	public String getColor() {
		return color;
	}
	
	//Pre: None
	//Post: Returns a string describing the color and indexes
	public String toString() {
		String string = "PicassoEngine.Face with color #" + color + " and vertex indexes:";
		for (int i = 0; i < vertexIndexes.length; i++) {
			string += vertexIndexes[i];
			if (i < vertexIndexes.length - 1) {
				string += ", ";
			}
		}
		return string;
	}
}
