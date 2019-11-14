/**
 * ATM class is used to simulate an ATM machine
 * @author Shelby Neal
 * Emplid: 6030859
 * Email: ssn287@email.vccs.edu
 * Purpose: Programming Assignment #6
 */
import java.util.Scanner;
public class ATM {
	private static int askForId(Account[] accounts) {
		Scanner input = new Scanner(System.in);
		int id = 0;
		boolean isValidId = false;
		while(!isValidId) {
			System.out.print("Enter an ID: ");
			id = input.nextInt();
			if(!hasId(accounts, id)) {
				System.out.println("Invalid ID");
			}
			else {
				isValidId = true;
			}
		}
		return id;
	}
	private static Account getAccount(Account[] accounts, int id) {
		for(Account account : accounts) {
			if(id == account.getId()) {
				return account;
			}
		}
		return null;
	}
	private static boolean hasId(Account[] accounts, int id) {
		for(Account account : accounts) {
			if(id == account.getId()) {
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Account[] accounts = new Account[10];
		for(int i = 0; i < accounts.length; i++) {
			accounts[i] = new Account(i, 100);
		}
		int option = 0;
		int id = askForId(accounts);
		while(option != 4) {
			Account user = getAccount(accounts, id);
			System.out.print("Main Menu\n1: Check Balance\n2: Withdraw\n3: Deposit\n4: Exit\nEnter a Choice: ");
			option = input.nextInt();
			switch(option) {
			case 1:
				System.out.println("The balance is $" + user.getBalance() + "\n");
				break;
			case 2:
				System.out.print("Enter an amount to withdraw: ");
				user.withdraw(input.nextDouble());
				break;
			case 3:
				System.out.print("Enter an amount to deposit: ");
				user.deposit(input.nextDouble());
				break;
			case 4:
				System.out.println("Logged out...\n");
				id = askForId(accounts);
				option = 0;
				break;
			default:
				System.out.println("Invalid Option");
			}
		}
	}
}