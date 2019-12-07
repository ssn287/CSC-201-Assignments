/**
 * Strassen class finds the product of two N x N matrices
 * @author Shelby Neal
 * Emplid: 6030859
 * Email: ssn287@email.vccs.edu
 * Purpose: Programming Assignment #10
 */
public class Strassen {
	private final static int N = 8; // matrix dimension *value must be a power of two where N = 2^n and n > 0*
	public static void print(int[][] a) {
		System.out.println();
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a.length; j++) {
				System.out.print(a[i][j] + "\t");
			}
			System.out.println();
		}
	}
	public static int[][] add(int[][] a, int[][] b) {
		int[][] result = new int[a.length][a.length];
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a.length; j++) {
				result[i][j] = a[i][j] + b[i][j];
			}
		}
		return result;
	}
	public static int[][] sub(int[][] a, int[][] b) {
		int[][] result = new int[a.length][a.length];
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a.length; j++) {
				result[i][j] = a[i][j] - b[i][j];
			}
		}
		return result;
	}
	public static int[][] mul(int[][] a, int[][] b) {
		int[][] product = new int[a.length][a.length];
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a.length; j++) {
				for(int k = 0; k < a.length; k++) {
					product[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		return product;
	}
	/**
	 * Standard method operates in O(n^3) time
	 * Strassen's algorithm operates in O(n^(log2(7)+o(1)) time (~ O(n^2.8074))
	 * Strassen's algorithm is asymptotically faster, standard method saves memory
	 */
	public static int[][] strassen(int[][] a, int[][] b) {
		if(a.length <= 1) { // base case
			return mul(a, b);
		}
		else {
			int n = a.length;
			int h = n / 2;
			// initializing sub-matrices
			int[][] a11 = new int[h][h];
			int[][] a12 = new int[h][h];
			int[][] a21 = new int[h][h];
			int[][] a22 = new int[h][h];
			int[][] b11 = new int[h][h];
			int[][] b12 = new int[h][h];
			int[][] b21 = new int[h][h];
			int[][] b22 = new int[h][h];
			int[][] A = new int[h][h];
			int[][] B = new int[h][h];
			// dividing the matrices into sub-matrices
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < h; j++) {
					a11[i][j] = a[i][j]; // top left
					a12[i][j] = a[i][j + h]; // top right
					a21[i][j] = a[i + h][j]; // bottom left
					a22[i][j] = a[i + h][j + h]; // bottom right
					b11[i][j] = b[i][j]; // top left
					b12[i][j] = b[i][j + h]; // top right
					b21[i][j] = b[i + h][j]; // bottom left
					b22[i][j] = b[i + h][j + h]; // bottom right
				}
			}
			// calculating p1 to p7
			// A = {{a, b}, {c, d}}
			// B = {{e, f}, {g, h}}
			A = add(a11, a22);
			B = add(b11, b22);
			int[][] p1 = strassen(A, B); // p1 = a(f - h)
			A = add(a21, a22);
			int[][] p2 = strassen(A, b11); // p2 = (a + b)h
			B = sub(b12, b22);
			int[][] p3 = strassen(a11, B); // p3 = (c + d)e
			B = sub(b21, b11);
			int[][] p4 = strassen(a22, B); // p4 = d(g - e)
			A = add(a11, a12);
			int[][] p5 = strassen(A, b22); // p5 = (a + d)(e + h)
			A = sub(a21, a11);
			B = add(b11, b12);
			int[][] p6 = strassen(A, B); // p6 = (b - d)(g + h)
			A = sub(a12, a22);
			B = add(b21, b22);
			int[][] p7 = strassen(A, B); // p7 = (a - c)(e + f)
			// calculating product sub-matrices
			int[][] c12 = add(p3, p5);
			int[][] c21 = add(p2, p4);
			A = add(p1, p4);
			B = add(A, p7);
			int[][] c11 = sub(B, p5);
			A = add(p1, p3);
			B = add(A, p6);
			int[][] c22 = sub(B, p2);
			// combining product sub-matrices into a single matrix
			int[][] C = new int[n][n];
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < h; j++) {
					C[i][j] = c11[i][j];
					C[i][j + h] = c12[i][j];
					C[i + h][j] = c21[i][j];
					C[i + h][j + h] = c22[i][j];
				}
			}
			return C;
		}
	}
	public static void main(String[] args) {
		int[][] x = new int[N][N];
		int[][] y = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				x[i][j] = (int)(Math.random() * 1000);
				y[i][j] = (int)(Math.random() * 1000);
			}
		}
		System.out.println("\nMatrix A"); print(x);
		System.out.println("\nMatrix B"); print(y);
		int[][] z = strassen(x, y);
		System.out.println("\nProduct (Strassen's)"); print(z);
	}
}