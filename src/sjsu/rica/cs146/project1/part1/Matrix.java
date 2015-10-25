package sjsu.rica.cs146.project1.part1;

public class Matrix {

	private int[][] matrix;
	private int[][] completeMatrix;
	
	public Matrix(int[][] mat){
		mat = matrix;
	}
	
	/**
	 * Multiplies two matrices, matrixA and matrixB, and returns a matrixC
	 * @param matrixA
	 * @param matrixB
	 * @return matrixC
	 */
    public int[][] matrixMultiply(int[][] matrixA, int[][] matrixB) {
        int n = matrixB.length;
        int[][] matrixC = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                for (int j = 0; j < n; j++) {
                    matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        completeMatrix = matrixC;
        return matrixC;
    }
    
    /**
     * Fills random integers from 0 - 10 to fill a matrix
     * @param mat
     */
    public void fillRandom(int[][] matrix) {
    	for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix.length; j++){
				matrix[i][j] = (int) Math.abs(Math.random() * 10);
			}
		}
    }
    
    /**
     * Print method for matrix
     */
    public void printMatrix() {
    	for (int i = 0; i < completeMatrix.length; i++) {
            for (int j = 0; j < completeMatrix[0].length; j++) {
					System.out.print(completeMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Add method used in Strassen method
     * @param matrixA
     * @param matrixB
     * @return matrixC - The added matrix
     */
    private static int[][] add(int[][] matrixA, int[][] matrixB) {
        int n = matrixA.length;
        int[][] matrixC = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
            	matrixC[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
        return matrixC;
    }

    /**
     * Subtract method used in Strassen method
     * @param matrixA
     * @param matrixB
     * @return matrixC - The subtracted matrix
     */
    private static int[][] subtract(int[][] matrixA, int[][] matrixB) {
        int n = matrixA.length;
        int[][] matrixC = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
            	matrixC[i][j] = matrixA[i][j] - matrixB[i][j];
            }
        }
        return matrixC;
    }

    /**
     * Strassen's algorithm implemented
     * @param matrixA
     * @param matrixB
     * @return matrixC - The multiplied matrix
     */
    public int[][] strassenMultiply(int[][] matrixA, int[][] matrixB) {
        int n = matrixA.length;
        int[][] matrixC = new int[n][n];
        
		if (n == 1) {
			matrixC[0][0] = matrixA[0][0] * matrixB[0][0];
		} else {

			//Initializes sub-matricies
            int newSize = n / 2;
            int[][] a11 = new int[newSize][newSize];
            int[][] a12 = new int[newSize][newSize];
            int[][] a21 = new int[newSize][newSize];
            int[][] a22 = new int[newSize][newSize];

            int[][] b11 = new int[newSize][newSize];
            int[][] b12 = new int[newSize][newSize];
            int[][] b21 = new int[newSize][newSize];
            int[][] b22 = new int[newSize][newSize];

            // Divides the matrices into 4 sub-matrices
            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    a11[i][j] = matrixA[i][j]; 
                    a12[i][j] = matrixA[i][j + newSize]; 
                    a21[i][j] = matrixA[i + newSize][j]; 
                    a22[i][j] = matrixA[i + newSize][j + newSize]; 

                    b11[i][j] = matrixB[i][j]; 
                    b12[i][j] = matrixB[i][j + newSize]; 
                    b21[i][j] = matrixB[i + newSize][j]; 
                    b22[i][j] = matrixB[i + newSize][j + newSize]; 
                }
            }

            // Calculates p1 to p7
            int[][] m1 = strassenMultiply(add(a11, a22), add(b11, b22)); // p1 = (a11+a22) * (b11+b22)

            int[][] m2 = strassenMultiply(add(a21, a22), b11); // p2 = (a21+a22) * (b11)

            int[][] m3 = strassenMultiply(a11, subtract(b12, b22)); // p3 = (a11) * (b12 - b22)

            int[][] m4 = strassenMultiply(a22, subtract(b21, b11)); // p4 = (a22) * (b21 - b11)

            int[][] m5 = strassenMultiply(add(a11, a12), b22); // p5 = (a11+a12) * (b22)

            int[][] m6 = strassenMultiply(subtract(a21, a11), add(b11, b12)); // p6 = (a21-a11) * (b11+b12)

            int[][] m7 = strassenMultiply(subtract(a12, a22), add(b21, b22)); // p7 = (a12-a22) * (b21+b22)

            
            // calculating c21, c21, c11 e c22:
            int[][] c12 = add(m3, m5); // c12 = p3 + p5
            int[][] c21 = add(m2, m4); // c21 = p2 + p4
            int[][] c11 = subtract(add(add(m1, m4), m7), m5); // c11 = p1 + p4 - p5 + p7
            int[][] c22 = subtract(add(add(m1, m3), m6), m2); // c22 = p1 + p3 - p2 + p6

            
            // Grouping the results obtained in a single matrix:
            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                	matrixC[i][j] = c11[i][j];
                	matrixC[i][j + newSize] = c12[i][j];
                	matrixC[i + newSize][j] = c21[i][j];
                	matrixC[i + newSize][j + newSize] = c22[i][j];
                }
            }
        }
		return matrixC;
    }
    
}