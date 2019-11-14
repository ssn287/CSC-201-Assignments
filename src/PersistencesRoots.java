/**
 * PersistencesRoots class displays the additive and multiplicative persistences and roots  of a given number
 * @author Shelby Neal
 * Emplid: 6030859
 * Email: ssn287@gmail.com
 * Purpose: Programming Assignment #2
 */
import javax.swing.JOptionPane;
public class PersistencesRoots {
	public static String output = "\nFinding the Additive & Multiplicative Persistences & Roots\n";
	public static int addRoot, multRoot, persAdd, persMult = 0;
	public static void inputDigits(int[] a, int i, int n) {
		if(i >= 0) {
			a[i] = n % 10;
			inputDigits(a, --i, n / 10);
		}
	}
	public static double findSum(int[] a, int i) {
		if(i >= a.length) {
			return 0;
		}
		else {
			return a[i] + findSum(a, ++i);
		}
	}
	public static double findProduct(int[] a, int i) {
		if(i >= a.length) {
			return 1;
		}
		else {
			return a[i] + findProduct(a, ++i);
		}
	}
	public static int addPers(int[] a, int i) {
		persAdd++;
		int pass = (int)findSum(a, 0);
		int counter = 0;
		int tempPass = pass;
		while(tempPass > 0) {
			tempPass /= 10;
			counter++;
		}
		int[] tempDigits = new int[counter];
		while(pass >= 10) {
			inputDigits(tempDigits, --counter, pass);
			return addPers(tempDigits, 0);
		}
		addRoot = pass;
		return persAdd;
	}
	public static int multPers(int[] a, int i) {
		persMult++;
		int pass = (int)findProduct(a, 0);
		int counter = 0;
		int tempPass = pass;
		while(tempPass > 0) {
			tempPass /= 10;
			counter++;
		}
		int[] tempDigits = new int[counter];
		while(pass >= 10) {
			inputDigits(tempDigits, --counter, pass);
			return multPers(tempDigits, 0);
		}
		multRoot = pass;
		return persMult;
	}
	public static void main(String[] args) {
		while(true) {
			int num = Integer.parseInt(JOptionPane.showInputDialog("\nPlease enter an integer number (negative number to quit): "));
			if(num < 0) {
				JOptionPane.showMessageDialog(null, "Thanks for playing");
				System.exit(0);
			}
			int tempDigits = num;
			int counter = 0;
			while(tempDigits > 0) {
				tempDigits /= 10;
				counter++;
			}
			int size = counter;
			int[] digits = new int[size];
			inputDigits(digits, --size, num);
			output += "\nYour input is: " + num;
			output += "\nThe Additive Persistence is: " + addPers(digits, 0);
			output += "\nThe Additive Root is: " + addRoot;
			output += "\nThe Multiplicative Persistence is: " + multPers(digits, 0);
			output += "\nThe Multiplicative Root is: " + multRoot;
			JOptionPane.showMessageDialog(null, output);
			addRoot = 0; multRoot = 0; persAdd = 0; persMult = 0;
		}
	}
}