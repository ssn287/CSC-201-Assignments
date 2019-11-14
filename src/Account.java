/**
 * Account class is used for Account objects
 * @author Shelby Neal
 * Emplid: 6030859
 * Email: ssn287@email.vccs.edu
 * Purpose: Programming Assignment #6
 */
public class Account {
	protected String mName;
	protected int mId;
	protected double mBalance;
	public Account() {
	}
	public Account(int id, double balance) {
		this();
		mId = id;
		mBalance = balance;
	}
	public Account(String name, int id, double balance) {
		this(id, balance);
		mName = name;
	}
	public int getId() {
		return mId;
	}
	public void setId(int id) {
		mId = id;
	}
	public double getBalance() {
		return mBalance;
	}
	public void setBalance(double balance) {
		mBalance = balance;
	}
	public void withdraw(double amount) {
		mBalance -= amount;
	}
	public void deposit(double amount) {
		mBalance += amount;
	}
	@Override
	public String toString( ) {
		return "Account name: " + mName;
	}
}