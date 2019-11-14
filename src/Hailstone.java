/**
 * Hailstone class displays the Hailstone sequence of a given number
 * @author Shelby Neal
 * Emplid: 6030859
 * Email: ssn287@gmail.com
 * Purpose: Programming Assignment #1
 */
import javax.swing.JOptionPane;
public class Hailstone {
	public static String output = "\nUsing the Hailstone Sequence\n";
	public static int counter = 0;
	public static String sequence(int n) {
		String product = "" + n;
		if(n != 1) {
			if(n % 2 == 0) {
				counter++;
				return product + " is even so I take half: " + (n / 2) + "\n" + sequence(n / 2);
			}
			else {
				counter++;
				return product + " is odd, so I make 3n + 1: " + ((3 * n) + 1) + "\n" + sequence((3 * n) + 1);
			}
		}
		else {
			return "The process took " + counter + " to reach 1\n";
		}
	}
	public static void main(String[] args) {
		while(true) {
			int n = Integer.parseInt(JOptionPane.showInputDialog("\nEnter a number: "));
			System.out.print(output + sequence(n));
			counter = 0;
		}
	}
}