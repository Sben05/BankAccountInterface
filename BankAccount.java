/**
 * @author Shreeniket Bendre
 * Project: BankAccount
 * Class: BankAccount.java
 */
public abstract class BankAccount {
	
	static int nextAccNum = 1;
	private String name;
	private int accNum;
	private double balance;
	
	public BankAccount(String n) {
		
		name = n;
		accNum = nextAccNum;
		balance = 0;
		nextAccNum++;
	}
	
	public BankAccount(String n, double b) {
		name = n;
		accNum = nextAccNum;
		balance = b;
		nextAccNum++;
	}
	
	public void deposit(double amt) {
		balance+=amt;
	}
	public void withdraw(double amt) {
		balance-=amt;
	}
	public int getAccountNum() {
		return accNum;
	}
	public double getBalance() {
		return balance;
	}
	public String getName() {
		return name;
	}
	public abstract void endOfMonthUpdate();
	public void transfer (BankAccount other, double amt) {
		this.withdraw(amt);
		other.deposit(amt);
	}
	public String toString() {
		return accNum+"\t"+name+"\t$"+balance;
	}
}
