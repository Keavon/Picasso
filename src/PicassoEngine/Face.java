package PicassoEngine;

public class Face {
	private int[] vertexIndexes;
	private String color;
	
	public Face(int[] triangleIndexes, String color) {
		this.vertexIndexes = triangleIndexes;
		this.color = color;
	}
	
	public int[] getVertexIndexes() {
		return vertexIndexes;
	}
	
	public String getColor() {
		return color;
	}
	
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
