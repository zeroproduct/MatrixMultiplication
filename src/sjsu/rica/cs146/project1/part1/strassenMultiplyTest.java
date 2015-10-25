package sjsu.rica.cs146.project1.part1;

import static org.junit.Assert.*;

import org.junit.Test;

public class strassenMultiplyTest {

	@Test
	public void test() {
		int n = 64;
		
		int[][] a = new int[n][n];
		int[][] b = new int[n][n];
		int[][] c = new int[n][n];

		Matrix test = new Matrix(c);
		
		test.fillRandom(a);
		test.fillRandom(b);
		
		int[][] output = test.strassenMultiply(a, b);
		assertArrayEquals(test.matrixMultiply(a, b), output);
		
		System.out.println("Done.");
	}

}
