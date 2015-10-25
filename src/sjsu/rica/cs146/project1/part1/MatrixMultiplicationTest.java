package sjsu.rica.cs146.project1.part1;

public class MatrixMultiplicationTest {

	public static void main(String[] args) {
		
		int n = 4;
		
		int[][] a = new int[n][n];
		int[][] b = new int[n][n];
		int[][] c = new int[n][n];

		Matrix matrix = new Matrix(c);
		
		matrix.fillRandom(a);
		matrix.fillRandom(b);
		
		matrix.matrixMultiply(a, b);
		System.out.println("Regular");
		matrix.printMatrix();
		System.out.println();
		
		matrix.strassenMultiply(a, b);
		System.out.println("Strassen");
		matrix.printMatrix();
		System.out.println();
		
		System.out.println("Done.");
	}
}
