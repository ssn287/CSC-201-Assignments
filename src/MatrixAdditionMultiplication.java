/**
 * MatrixAdditionMultiplication class calculates the sum and/or product of two given matrices
 * @author Shelby Neal
 * Emplid: 6030859
 * Email: ssn287@gmail.com
 * Purpose: Programming Assignment #3
 */
import javax.swing.JOptionPane;
public class MatrixAdditionMultiplication {
	public static String output = "";
	public static int[][] createMatrix(int[][] a) {
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				a[i][j] = Integer.parseInt(JOptionPane.showInputDialog("Enter the # for Row " + (i + 1) + " Col " + (j + 1)));
			}
		}
		return a;
	}
	public static int[][] addMatrix(int[][] a, int[][] b) {
		int[][] result = new int[a.length][b.length];
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				result[i][j] = a[i][j] + b[i][j];
				output += result[i][j] + " ";
			}
			output += "\n";
		}
		return result;
	}
	public static int[][] multMatrix(int[][] a, int[][] b) {
		int[][] result = new int[a.length][b[0].length];
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < b[0].length; j++) {
				for(int k = 0; k < a[0].length; k++) {
					result[i][j] = result[i][j] + a[i][k] * b[k][j];
				}
				output += result[i][j] + " ";
			}
			output += "\n";
		}
		return result;
	}
	public static void main(String[] args) {
		while(true) {
			int r1 = Integer.parseInt(JOptionPane.showInputDialog("Enter the # of Rows for Matrix 1: "));
			int c1 = Integer.parseInt(JOptionPane.showInputDialog("Enter the # of Colums for Matrix 1: "));
			int r2 = Integer.parseInt(JOptionPane.showInputDialog("Enter the # of Rows for Matrix 2: "));
			int c2 = Integer.parseInt(JOptionPane.showInputDialog("Enter the # of Columns for Matrix 2: "));
			int[][] m1 = new int[r1][c1];
			int[][] m2 = new int[r2][c2];
			JOptionPane.showMessageDialog(null, "Please enter the Array Elements for Matrix #1");
			createMatrix(m1);
			JOptionPane.showMessageDialog(null, "Please enter the Array Elements for Matrxi #2");
			createMatrix(m2);
			if(r1 == r2 && c1 == c2) {
				output += "The Sum of the Matrices is:\n";
				addMatrix(m1, m2);
				output += "\nThe Product of the Matrices is:\n";
				multMatrix(m1, m2);
			}
			else if((r1 != r2 || c1 != c2) && c1 == r2) {
				output += "*Could not Compute Sum*\n";
				output += "\nThe Product of the Matrices is:\n";
				multMatrix(m1, m2);
			}
			else {
				output += "*Could not Compute Sum*\n";
				output += "\n*Could not Compute Product*\n";
			}
			JOptionPane.showMessageDialog(null, output);
			output = "";
		}
	}
}