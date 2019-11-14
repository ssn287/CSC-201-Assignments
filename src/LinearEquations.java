/**
 * LinearEquations class is used to solve 2x2 systems of equations
 * 	@author Shelby Neal
 *	Emplid: 6030859
 *	Email: ssn287@email.vccs.edu
 *	Purpose: Programming Assignment #5 
 */
public class LinearEquations {
	// gets determinant
	public static double getDet(double a, double b, double d, double e) {
		return a * e - b * d;
	}
	/**
	 * Gaussian Elimination (Row Reduction)
	 *	(1) swap two rows
	 * 	(2) multiply a row by a nonzero number
	 * 	(3) add a multiple of one row to another row
	 * 	*** using row operations the matrix is converted into reduced row echelon form (Gauss-Jordan Elimination)
	 * 	*** resulting coefficient matrix = {{1, 0}, {0, 1}}
	 * 	@return {x, y}
	 */
	public static double [] subst(double a, double b, double c, double d, double e, double f) {
		double det = getDet(a, b, d, e);
		//determinant check
		if(det == 0.0) {
			return(null);
		}
		//init matrices
		double [][] A = {{a, b}, {d, e}};
		double [] B = {c, f};
		for(int i = 0; i < 2; i++) {
			//init pivot
			double pivot = A[i][i];
			for(int j = 0; j < 2; j++) {
				A[i][j] = A[i][j] / pivot;
			}
			B[i] = B[i] / pivot;
			for(int j = 0; j < 2; j++) {
				if(j != i) {
					//init factor
					double factor = A[j][i];
					for(int k = 0; k < 2; k++) {
						A[j][k] = A[j][k] - factor * A[i][k];
					}
					B[j] = B[j] - factor * B[i];
				}
			}
		}
		return B;
	}
	/**
	 * Cramer's Rule
	 * 	x = (ce - bf) / (ae - bd)
	 * 	y = (af - cd) / (ae - bd)
	 * 	when the determinant != 0
	 * 	@return {x, y}
	 */
	public static double [] solve(double a, double b, double c, double d, double e, double f) {
		double det = getDet(a, b, d, e);
		//determinant check
		if(det == 0.0) {
			return(null);
		}
		double [] result = {(c * e - f * b) / det, (f * a - c * d) / det};
		return result;
	}
	/**
	 * Chicken McNugget Theorem
	 * 	for any two (2) relatively prime positive integers: m, n
	 * 	the greatest integer that cannot be written in the form am + bn
	 * 	for nonnegative integers a, b is mn - m - n
	 * 	@return mn - m - n
	 */
	public static double chickenMcNugget(double m, double n) {
		return m * n - m - n;
	}
	public static void main(String[] args) {
		//init problems
		double [] a1a = subst(7, -3, -27, 1, 1, -1);
		double [] a1b = subst(1, -5, -62, 6, -5, -72);
		double [] a2a = solve(-1, -3, 9, 2, 5, -15);
		double [] a2b = solve(-1, 3, -11, 2, -5, 19);
		double [] a3 = solve(2, 3, 13, 3, 7, 27);
		double [] a4 = solve(1, 3, 6, 3, 0, 9);
		double [] a5 = solve(1, 2, 4, 6, 12, 6);
		//output with required checks for inconsistent and dependent equations
		System.out.println("\nSolve for both variables by substitution");
		System.out.println("\n1a.\t7x + -3y = -27\n\t x +   y = -1");
		System.out.println("\n\tx = " + a1a[0] + "\n\ty = " + a1a[1]);
		System.out.println("\n1b.\t x + -5y = -62\n\t6x + -5y = -72");
		System.out.println("\n\tx = " + a1b[0] + "\n\ty = " + a1b[1]);
		System.out.println("\nSolve for both variables by using Cramer's Rule");
		System.out.println("\n2a\t-x + 3y = 9\n\t2x + 5y = -15");
		if(a2a == null) {
			System.out.println("\n\tThe Equations are Either Inconsistent or Dependent");
		}
		else {
			System.out.println("\n\tx = " + a2a[0] + "\n\ty = " + a2a[1]);
		}
		System.out.println("\n2b.\t-x + 3y = -11\n\t2x + 5y = 19");
		if(a2b == null) {
			System.out.println("\n\tThe Equations are Either Inconsistent or Dependent");
		}
		else {
			System.out.println("\n\tx = " + a2b[0] + "\n\ty = " + a2b[1]);
		}
		System.out.println("\nSolve for both variables");
		System.out.println("\n3.\t x + 2y + 4 = 3x + 5y - 9\n\t4x + 2y + 6 = 7x + 9y - 21");
		if(a3 == null) {
			System.out.println("\n\tThe Equations are Either Inconsistent or Dependent");
		}
		else {
			System.out.println("\n\tx = " + a3[0] + "\n\ty = " + a3[1]);
		}
		System.out.println("\n4.\t3x + 6y + 7 = 4x + 9y + 1\n\t4x + 8y + 3 = 7x + 8y - 6");
		if(a4 == null) {
			System.out.println("\n\tThe Equations are Either Inconsistent or Dependent");
		}
		else {
			System.out.println("\n\tx = " + a4[0] + "\n\ty = " + a4[1]);
		}
		System.out.println("\n5.\t5x + 8y + 6 =  4x + 6y + 10\n\t4x + 7y + 5 = -2x + 5y + 11");
		if(a5 == null) {
			System.out.println("\n\tThe Equations are Either Inconsistent or Dependent");
		}
		else {
			System.out.println("\n\tx = " + a5[0] + "\n\ty = " + a5[1]);
		}
		System.out.println("\nL.\tBurger King is going to have a new offering called \"Burguettes\", which are small burgers which can\n\tbe ordered only in \"paks.\" A small pak has 7 Burguettes and a large pak has 15 burguettes. What is the\n\tlargest number of Burguettes that cannot be ordered by making some combination of small and large paks?");
		System.out.println("\n\t" + chickenMcNugget(7, 15));
	}
}