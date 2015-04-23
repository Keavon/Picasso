public class Face
{
	private int[] triangleIndexes;
	private String color;
	
	public Face(int[] triangleIndexes, String color) {
		this.triangleIndexes = triangleIndexes;
		this.color = color;
	}
	
	public int[] getTriangleIndexes() {
		return triangleIndexes;
	}
	
	public String getColor() {
		return color;
	}
	
	public String toString() {
		String string = "Face with color #" + color + " and vertex indexes:";
		for (int i = 0; i < triangleIndexes.length; i++) {
			string += triangleIndexes[i];
			if (i < triangleIndexes.length - 1) {
				string += ", ";
			}
		}
		return string;
	}
}
