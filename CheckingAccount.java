/**
 * @author Shreeniket Bendre
 * Project: BankAccount
 * Class: CheckingAccount.java
 * Parent: BankAccount.java
 * Notes: Please ignore commented code. It is for the extra credit portion of the project.
 */
public class CheckingAccount extends BankAccount {
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final int FREE_TRANS;

	private int numTransactions;

	public CheckingAccount(String n, double b, double odf, double tf, int freeTrans) {
		super(n, b);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
		numTransactions = 0;
	}
	public CheckingAccount(String n, double odf, double tf, int freeTrans) {
		super(n, 0);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
	public void deposit (double amt) {
		boolean allow = true;
		if (numTransactions>=FREE_TRANS) {
			if (getBalance()-amt-TRANSACTION_FEE<0) {
				allow = false;
			}
		}
		else {
			if (getBalance()+amt<=0) allow = false;
		}
		if (amt<0) {
			allow = false;
		}
		if (!allow) {
			throw new IllegalArgumentException("Not Valid Deposit");
		}
		if (allow) {
			boolean tf = true;
			if (numTransactions<FREE_TRANS) {
				tf = false;
			}
			double amtDep = amt;
			if (tf) amtDep-=TRANSACTION_FEE;
			super.deposit(amtDep);
			numTransactions++;
		}
		
	}
	
	public void withdraw(double amt) {
		boolean allow = true;
		if (amt<0 || getBalance()<0) {
			allow = false;
			throw new IllegalArgumentException("Not Valid Withdrawl");
		}
		if (allow) {
			double amtDep;
			boolean odf = true;
			boolean tf = true;
			if (numTransactions<FREE_TRANS) {
				
				tf = false;
			}
			if (getBalance()-amt>=0 && !tf) {
				odf = false;
			}
			if (getBalance()-amt-TRANSACTION_FEE>=0 && tf) {
				odf = false;
			}
			numTransactions++;
			amtDep = amt;
			if (odf) amtDep+=OVER_DRAFT_FEE;
			if (tf) amtDep+=TRANSACTION_FEE;
			super.withdraw(amtDep);
		}

	}
	
	public void transfer(BankAccount other, double amt) {
		boolean allow = true;
		boolean tf = true;
		if (!getName().equals(other.getName())) allow = false;
		if (amt<0) allow = false;
		if (numTransactions<FREE_TRANS) {
			tf = false;
		}
		if (getBalance()-amt<0) {
				allow = false;	
		}
		if (!allow) {
			throw new IllegalArgumentException("Not Valid Withdrawl");
		}
		if (allow) {
			double amtTrans = amt;
			if (tf) {
				amtTrans-=TRANSACTION_FEE;
			}
			super.transfer(other, amtTrans);
			numTransactions++;
		}
	}
	
	public void endOfMonthUpdate() {
		numTransactions = 0;
		
	}
//	public void setNumTransactions(int num) {
//		numTransactions = num;
//	}
//	public double getODF() {
//		return OVER_DRAFT_FEE;
//	}
//	public double getTF() {
//		return TRANSACTION_FEE;
//	}
//	public int getFreeTrans() {
//		return FREE_TRANS;
//	}
//	public int getNumTransactions() {
//		return numTransactions;
//	}
}
