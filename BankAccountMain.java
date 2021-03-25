/**
 * @author Shreeniket Bendre
 * Project: BankAccount
 * Class: BankAccountMain.java
 * Notes: Please ignore commented code. It is for the extra credit portion of the project.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountMain {

	private static ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
	private final static double OVER_DRAFT_FEE = 15;
	private final static double RATE = .0025;
	private final static double TRANSACTION_FEE = 1.5;
	private final static double MIN_BAL = 300;
	private final static double MIN_BAL_FEE = 10;
	private final static int FREE_TRANSACTIONS = 10;

	public static void main(String[] args) {

		Scanner scan = new Scanner (System.in);
		boolean terminate = false;
//		boolean fileHasContents = false;
//		File file = null;
//		Scanner read = null;
//		try {
//			file = new File("accounts.txt");
//			if (file.createNewFile());
//			read = new Scanner (file);
//		} catch (IOException e) {
//			System.out.println("An error occurred.");
//		}
//
//		if (file.length() > 0) {
//			fileHasContents = true;
//		}
//		if (fileHasContents) {
//			int numberOfAccounts = Integer.parseInt(read.nextLine());
//			for (int i = 0; i<numberOfAccounts; i++) {
//				String accInfoString = read.nextLine();
//				String[] accInfo = accInfoString.split(",");
//				if(accInfo[0].equals("C")) {
//					double b = Double.parseDouble(accInfo[1]);
//					String name = accInfo[2];
//					double odf = Double.parseDouble(accInfo[3]);
//					double tf = Double.parseDouble(accInfo[4]);
//					int freeTrans = Integer.parseInt(accInfo[5]);
//					int numTransactions = Integer.parseInt(accInfo[6]);
//					CheckingAccount account = new CheckingAccount(name,b,odf,tf,freeTrans);
//					account.setNumTransactions(numTransactions);
//					accounts.add(account);
//				}
//				else {
//					double b = Double.parseDouble(accInfo[1]);
//					String name = accInfo[2];
//					double rate = Double.parseDouble(accInfo[3]);
//					double mb = Double.parseDouble(accInfo[4]);
//					double mbf = Double.parseDouble(accInfo[5]);
//					int numTransfers = Integer.parseInt(accInfo[6]);
//					SavingsAccount account = new SavingsAccount(name,b,rate,mb,mbf);
//					account.setNumTransfers(numTransfers);
//					accounts.add(account);
//				}
//			}
//		}
		while(!terminate) {
			System.out.println("Would you like to (A)dd an account, (M)ake a transaction, or (T)erminate the program? ");
			boolean validInput = false;
			String input = "";

			while (!validInput) {
				input = scan.nextLine();
				boolean valid = true;
				if (input.length() != 1) valid = false;
				else if (!(input.equals("A") || input.equals("M") || input.equals("T"))) valid = false;
				if (valid) {
					validInput = true;
				}
				else {
					System.out.println("Invalid input. Please re-enter. Would you like to (A)dd an account, (M)ake a transaction, or (T)erminate the program?");
					input = "";
				}
			}

			if (input.equals("T")) {
//				String accInfo = accounts.size()+"\n";
//				for(int i = 0; i<accounts.size(); i++) {
//					BankAccount account = accounts.get(i);
//					double accBalance = account.getBalance();
//					String accName = account.getName();
//					String type = "";
//					double odfOrRate = 0;
//					double tfOrMb = 0;
//					int nums = 0;
//					int freeTrans = 0;
//					double mbf = 0;
//					if (account instanceof CheckingAccount) {
//						type = "C";
//						odfOrRate = ((CheckingAccount)account).getODF();
//						tfOrMb = ((CheckingAccount)account).getTF();
//						freeTrans = ((CheckingAccount)account).getFreeTrans();
//						nums = ((CheckingAccount)account).getNumTransactions();
//						accInfo += type+","+accBalance+","+accName+","+odfOrRate+","+tfOrMb+","+freeTrans+","+nums+"\n";
//					}
//					else {
//						type = "S";
//						odfOrRate = ((SavingsAccount)account).getRate();
//						tfOrMb = ((SavingsAccount)account).getMB();
//						mbf = ((SavingsAccount)account).getMBF();
//						nums = ((SavingsAccount)account).getNumTransfers();
//						accInfo += type+","+accBalance+","+accName+","+odfOrRate+","+tfOrMb+","+mbf+","+nums+"\n";
//					}
//					try {
//						FileWriter myWriter = new FileWriter("accounts.txt");
//						myWriter.write(accInfo);
//						myWriter.close();
//					} 
//					catch (IOException e) {
//						System.out.println("An error occurred.");
//					}
//				}
//
				System.out.println("Have a nice day.");
				terminate = true;

			}
			else if (input.equals("A")) {
				boolean validAcc = false;
				String accType = "";
				while (!validAcc) {
					System.out.println("Would you like a (C)heckings or (S)avings Account?");
					accType = scan.nextLine();
					if (!(accType.equals("C")||accType.equals("S"))) {
						System.out.println("Invalid Input. Please re-enter.");
					}
					else validAcc = true;
				}
				if (accType.equals("C")){
					System.out.println("Account Name: ");
					String name = scan.nextLine();

					System.out.println("Account Balance: ");
					while(!scan.hasNextDouble()) {
						scan.nextLine();
						System.out.println("Invalid. Re enter.");
					}
					double b = scan.nextDouble();
					scan.nextLine();

					CheckingAccount checkingAccount = new CheckingAccount(name, b, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS);
					accounts.add(checkingAccount);
				}
				else {
					System.out.println("Account Name: ");
					String name = scan.nextLine();

					System.out.println("Account Balance: ");
					while(!scan.hasNextDouble()) {
						scan.nextLine();
						System.out.println("Invalid. Re enter.");
					}
					double b = scan.nextDouble();
					scan.nextLine();

					SavingsAccount savingsAccount = new SavingsAccount(name, b, RATE, MIN_BAL, MIN_BAL_FEE);
					accounts.add(savingsAccount);
				}
			}
			else {
				boolean validOption = false;
				String userChoice = "";
				while (!validOption) {
					System.out.println("Would you like to (W)ithdraw, (D)eposit, (T)ransfer, or (G)et account numbers?");
					userChoice = scan.nextLine();
					switch (userChoice) {
					case "W":
						boolean validNum = false;
						boolean foundName = true;
						BankAccount accWithdraw = null;
						while (!validNum) {
							System.out.println("Please enter account number:");
							while(!scan.hasNextInt()) {
								scan.nextLine();
								System.out.println("Account numbers must be integers. Try again, and if you have the incorrect account number, we can help you recover it.");
							}
							int accNum = scan.nextInt();
							scan.nextLine();
							if (getAccountByNumber (accNum) != null) {
								accWithdraw = getAccountByNumber(accNum);
								validNum = true;
							}
							else {
								boolean validUserInput = false;
								String incorrectAccNumChoice = "";
								while (!validUserInput) {
									System.out.println("Invalid. Would you like to (R)e-enter, or (S)earch by name?");
									incorrectAccNumChoice = scan.nextLine();
									if (!(incorrectAccNumChoice.equals("R")||incorrectAccNumChoice.equals("S"))) {
										System.out.print("Not R or S. ");
									}
									else validUserInput = true;
								}
								if (incorrectAccNumChoice.equals("R")) {
									validNum = false;
								}
								else {
									System.out.println("Please enter your name:");
									String name = scan.nextLine();
									if (getAccountsByName(name)!=null) {
										ArrayList<BankAccount> accountsByName = getAccountsByName(name);
										for (int i = 0; i<accountsByName.size(); i++) {
											if (accounts.get(i) instanceof CheckingAccount)
												System.out.println("Checkings Account|Account Number:"+(accountsByName.get(i).toString()));
											else
												System.out.println("Savings Account  |Account Number:"+(accountsByName.get(i).toString()));
										}
										System.out.println("Which account would you like (ENTER ACCOUNT NUMBER)");
										while(!scan.hasNextInt()) {
											scan.nextLine();
											System.out.println("Invalid. Re enter.");
										}
										accNum = scan.nextInt();
										while (getAccountByNumber(accNum)==null) {
											System.out.println("Not a valid account number. Re enter.");
											if (scan.hasNextInt())
												accNum = scan.nextInt();

										}
										accWithdraw = getAccountByNumber(accNum);
										validNum = true;
									}
									else {
										System.out.println("\nNO ACCOUNTS UNDER THAT NAME\n");
										validNum = true;
										foundName = false;
										validOption = true;
									}
								}

							}
						}
						if (foundName) {
							System.out.println("How much would you like to withdraw?");
							while(!scan.hasNextDouble()) {
								scan.nextLine();
								System.out.println("How much would you like to withdraw?");
							}
							double amt = scan.nextDouble();
							scan.nextLine();
							try {
								accWithdraw.withdraw(amt);
								validOption = true;
							}
							catch(IllegalArgumentException e) {
								System.out.println("Transaction Not Authorized.");
								validOption = false;
							}
						}
						break;
					case "D":
						validNum = false;
						foundName = true;
						BankAccount accDeposit = null;
						while (!validNum) {
							System.out.println("Please enter account number:");
							while(!scan.hasNextInt()) {
								scan.nextLine();
								System.out.println("Account numbers must be integers. Try again, and if you have the incorrect account number, we can help you recover it.");
							}
							int accNum = scan.nextInt();
							scan.nextLine();
							if (getAccountByNumber (accNum) != null) {
								accDeposit = getAccountByNumber(accNum);
								validNum = true;
							}
							else {
								boolean validUserInput = false;
								String incorrectAccNumChoice = "";
								while (!validUserInput) {
									System.out.println("Invalid. Would you like to (R)e-enter, or (S)earch by name?");
									incorrectAccNumChoice = scan.nextLine();
									if (!(incorrectAccNumChoice.equals("R")||incorrectAccNumChoice.equals("S"))) {
										System.out.print("Not R or S. ");
									}
									else validUserInput = true;
								}
								if (incorrectAccNumChoice.equals("R")) {
									validNum = false;
								}
								else {
									System.out.println("Please enter your name:");
									String name = scan.nextLine();
									if (getAccountsByName(name)!=null) {
										ArrayList<BankAccount> accountsByName = getAccountsByName(name);
										for (int i = 0; i<accountsByName.size(); i++) {
											if (accounts.get(i) instanceof CheckingAccount)
												System.out.println("Checkings Account|Account Number:"+(accountsByName.get(i).toString()));
											else
												System.out.println("Savings Account  |Account Number:"+(accountsByName.get(i).toString()));
										}
										System.out.println("Which account would you like (ENTER ACCOUNT NUMBER)");
										while(!scan.hasNextInt()) {
											scan.nextLine();
											System.out.println("Invalid. Re enter.");
										}
										accNum = scan.nextInt();
										while (getAccountByNumber(accNum)==null) {
											System.out.println("Not a valid account number. Re enter.");
											if (scan.hasNextInt())
												accNum = scan.nextInt();

										}
										accDeposit = getAccountByNumber(accNum);
										validNum = true;
									}
									else {
										System.out.println("\nNO ACCOUNTS UNDER THAT NAME\n");
										validNum = true;
										foundName = false;
										validOption = true;
									}
								}

							}
						}
						if (foundName) {
							System.out.println("How much would you like to deposit?");
							boolean firstRun = true;
							while(!scan.hasNextDouble()) {
								scan.nextLine();
								System.out.println("How much would you like to deposit?");
							}
							double amt = scan.nextDouble();
							scan.nextLine();
							try {
								accDeposit.deposit(amt);
								validOption = true;
							}
							catch(IllegalArgumentException e) {
								System.out.println("Transaction Not Authorized");
								validOption = false;
							}
						}
						break;
					case "T":
						validNum = false;
						foundName = true;
						BankAccount accOne = null;
						BankAccount accTwo = null;
						while (!validNum) {
							System.out.println("Please enter account number to transfer money FROM:");
							while(!scan.hasNextInt()) {
								scan.nextLine();
								System.out.println("Account numbers must be integers. Try again, and if you have the incorrect account number, we can help you recover it.");
							}
							int accNum = scan.nextInt();
							scan.nextLine();
							if (getAccountByNumber (accNum) != null) {
								accOne = getAccountByNumber(accNum);
								validNum = true;
							}
							else {
								boolean validUserInput = false;
								String incorrectAccNumChoice = "";
								while (!validUserInput) {
									System.out.println("Invalid. Would you like to (R)e-enter, or (S)earch by name?");
									incorrectAccNumChoice = scan.nextLine();
									if (!(incorrectAccNumChoice.equals("R")||incorrectAccNumChoice.equals("S"))) {
										System.out.print("Not R or S. ");
									}
									else validUserInput = true;
								}
								if (incorrectAccNumChoice.equals("R")) {
									validNum = false;
								}
								else {
									System.out.println("Please enter your name:");
									String name = scan.nextLine();
									if (getAccountsByName(name)!=null) {
										ArrayList<BankAccount> accountsByName = getAccountsByName(name);
										for (int i = 0; i<accountsByName.size(); i++) {
											if (accounts.get(i) instanceof CheckingAccount)
												System.out.println("Checkings Account|Account Number:"+(accountsByName.get(i).toString()));
											else
												System.out.println("Savings Account  |Account Number:"+(accountsByName.get(i).toString()));
										}
										System.out.println("Which account would you like (ENTER ACCOUNT NUMBER)");
										while(!scan.hasNextInt()) {
											scan.nextLine();
											System.out.println("Invalid. Re enter.");
										}
										accNum = scan.nextInt();
										while (getAccountByNumber(accNum)==null) {
											System.out.println("Not a valid account number. Re enter.");
											if (scan.hasNextInt())
												accNum = scan.nextInt();

										}
										accOne = getAccountByNumber(accNum);
										validNum = true;
									}
									else {
										System.out.println("\nNO ACCOUNTS UNDER THAT NAME\n");
										validNum = true;
										foundName = false;
										validOption = true;
									}
								}

							}
						}
						if (foundName) {
							validNum = false;
							foundName = true;
							while (!validNum) {
								System.out.println("Please enter account number to transfer money TO:");
								while(!scan.hasNextInt()) {
									scan.nextLine();
									System.out.println("Account numbers must be integers. Try again, and if you have the incorrect account number, we can help you recover it.");
								}
								int accNum = scan.nextInt();
								scan.nextLine();
								if (getAccountByNumber (accNum) != null) {
									accTwo = getAccountByNumber(accNum);
									validNum = true;
								}
								else {
									boolean validUserInput = false;
									String incorrectAccNumChoice = "";
									while (!validUserInput) {
										System.out.println("Invalid. Would you like to (R)e-enter, or (S)earch by name?");
										incorrectAccNumChoice = scan.nextLine();
										if (!(incorrectAccNumChoice.equals("R")||incorrectAccNumChoice.equals("S"))) {
											System.out.print("Not R or S. ");
										}
										else validUserInput = true;
									}
									if (incorrectAccNumChoice.equals("R")) {
										validNum = false;
									}
									else {
										System.out.println("Please enter your name:");
										String name = scan.nextLine();
										if (getAccountsByName(name)!=null) {
											ArrayList<BankAccount> accountsByName = getAccountsByName(name);
											for (int i = 0; i<accountsByName.size(); i++) {
												if (accounts.get(i) instanceof CheckingAccount)
													System.out.println("Checkings Account|Account Number:"+(accountsByName.get(i).toString()));
												else
													System.out.println("Savings Account  |Account Number:"+(accountsByName.get(i).toString()));
											}
											System.out.println("Which account would you like (ENTER ACCOUNT NUMBER)");
											while(!scan.hasNextInt()) {
												scan.nextLine();
												System.out.println("Invalid. Re enter.");
											}
											accNum = scan.nextInt();
											while (getAccountByNumber(accNum)==null) {
												System.out.println("Not a valid account number. Re enter.");
												if (scan.hasNextInt())
													accNum = scan.nextInt();

											}
											accTwo = getAccountByNumber(accNum);
											validNum = true;
										}
										else {
											System.out.println("\nNO ACCOUNTS UNDER THAT NAME\n");
											validNum = true;
											foundName = false;
											validOption = true;
										}
									}

								}
							}
						}
						if (foundName) {
							System.out.println("How much would you like to transfer?");
							while(!scan.hasNextDouble()) {
								scan.nextLine();
								System.out.println("How much would you like to transfer?");
							}
							double amt = scan.nextDouble();
							scan.nextLine();
							try {
								accOne.transfer(accTwo, amt);
								validOption = true;
							}
							catch(IllegalArgumentException e) {
								System.out.println("Transaction Not Authorized");
								validOption = false;
							}
						}
						break;
					case "G":
						boolean validName = false;
						while (!validName) {
							System.out.println("Please enter your name:");
							String name = scan.nextLine();
							if (getAccountsByName(name)!=null) {
								ArrayList<BankAccount> accountsByName = getAccountsByName(name);
								for (int i = 0; i<accountsByName.size(); i++) {
									if (accounts.get(i) instanceof CheckingAccount)
										System.out.println("Checkings Account|Account Number:"+(accountsByName.get(i).toString()));
									else
										System.out.println("Savings Account  |Account Number:"+(accountsByName.get(i).toString()));
								}
								System.out.println("These are all accounts associated with your name.\n");
								validName = true;
							}
							else {
								System.out.println("No accounts under that name.");
								validName = true;
							}
						}
						validOption = true;
						break;
					default:
						System.out.println("Not a valid input.");
						validOption = false;
					}
				}
			}

		}


	}

	private static BankAccount getAccountByNumber (int number) {
		for (int i = 0; i<accounts.size(); i++) {
			if (accounts.get(i).getAccountNum() == number) {
				return accounts.get(i);
			}
		}
		return null;
	}

	private static ArrayList<BankAccount> getAccountsByName (String name){
		ArrayList<BankAccount> accountList = new ArrayList<BankAccount>();
		boolean isAccountFound = false;
		for (int i = 0; i<accounts.size(); i++) {
			if (accounts.get(i).getName().equals(name)) {
				accountList.add(accounts.get(i));
				isAccountFound = true;
			}
		}
		if (isAccountFound) {
			return accountList;
		}
		return null;
	}
}
