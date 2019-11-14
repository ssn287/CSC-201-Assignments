/**
 * ZellersCongruence class is used to calculate the day of the week
 * @author Shelby Neal
 * Emplid: 6030859
 * Email: ssn287@email.vccs.edu
 * Purpose: Programming Assignment #7
 */
import java.util.Scanner;
public class ZellersCongruence {
	public static void main(String[] args) {
		while(true) {
			Scanner input = new Scanner(System.in);
			System.out.print("Enter a year (e.g., 2019): ");
			int y = input.nextInt();
			System.out.print("Enter a month (1-12): ");
			int m = input.nextInt();
			System.out.print("Enter the day of the month (1-31): ");
			int q = input.nextInt();
			if(m == 1 || m == 2) {
				m += 12;
				y--;
			}
			int j = y / 100;
			int k = y % 100;
			int h = (q + 26 * (++m) / 10 + k + k / 4 + j / 4 + 5 * j) % 7;
			String day = "";
			switch(h) {
			case 0:
				day = "Saturday";
				break;
			case 1:
				day = "Sunday";
				break;
			case 2:
				day = "Monday";
				break;
			case 3:
				day = "Tuesday";
				break;
			case 4:
				day = "Wednesday";
				break;
			case 5:
				day = "Thursday";
				break;
			case 6:
				day = "Friday";
				break;
			}
			System.out.println("\nDay of the week is " + day + "\n");
		}
	}
}