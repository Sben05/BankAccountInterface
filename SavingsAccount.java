/**
 * @author Shreeniket Bendre
 * Project: BankAccount
 * Class: SavingsAccount.java
 * Parent: BankAccount.java
 * Notes: Please ignore commented code. It is for the extra credit portion of the project.
 */
public class SavingsAccount extends BankAccount{
	private double intRate;
	private int numTransfers;
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;
	private final int MAX_TRANSFER = 6;
	private final double TRANSACTION_FEE = 10;

	public SavingsAccount(String n, double b, double r, double mb, double mbf) {
		super(n,b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	public SavingsAccount(String n, double r, double mb, double mbf) {
		super(n,0);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}

	public void withdraw (double amt) {
		boolean allow = true;
		boolean mbf = false;
		if (amt<0) allow = false;
		if (getBalance()-amt<0) allow = false;
		if (!allow) throw new IllegalArgumentException("Invalid Withdraw");
		if (getBalance()-amt<MIN_BAL && allow)mbf=true;

		if(allow) {
			double amtDraw = amt;
			if (mbf) amtDraw += MIN_BAL_FEE;
			super.withdraw(amtDraw);
		}
	}

	public void deposit (double amt) {
		if(amt>=0) {
			super.deposit(amt);
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	public void transfer(BankAccount other, double amt) {
		boolean allow = true;
		boolean ca = false;
		boolean tf = false;
		boolean mbf = false;
		
		if (amt<0) allow = false;
		if (getBalance()-amt<0) allow = false;
		if (!getName().equals(other.getName())) allow = false;
		
		if (!allow) throw new IllegalArgumentException("Invalid Transfer");
		else {
			if (other instanceof CheckingAccount) ca = true;
			if (numTransfers >= MAX_TRANSFER && ca) tf = true;
			if (getBalance()-amt < MIN_BAL) mbf = true;
			
			double amtTran = amt;
			if (tf) amtTran+=TRANSACTION_FEE;
			if (mbf) amtTran+=MIN_BAL_FEE;
			super.transfer(other, amtTran);
			numTransfers++;
		}
		
	}
	
	public void addInterest() {
		double interest = getBalance()+(getBalance()*intRate);
		super.deposit(interest);
	}
	public void endOfMonthUpdate() {
		double interest = getBalance()+(getBalance()*intRate);
		super.deposit(interest);
		numTransfers = 0;
	}
//	public void setNumTransfers(int num) {
//		numTransfers = num;
//	}
//	public double getRate() {
//		return intRate;
//	}
//	public double getMB() {
//		return MIN_BAL;
//	}
//	public double getMBF() {
//		return MIN_BAL_FEE;
//	}
//	public int getNumTransfers() {
//		return numTransfers;
//	}
	
	
}
