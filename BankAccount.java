package BankAccountAssignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BankAccount {
	 private int account_number;
	 private String name;
	 private String account_type;
	 private double balance;
	 
	 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 
	 public BankAccount(int account_number, String name, String account_type, double balance) throws LowBalanceException, NegativeBalanceException,InsufficientBalanceException{
			super();
			this.account_number = account_number;
			this.name = name;
			this.account_type = account_type;
			this.balance = balance;
	 }
	 
	 public int getAccount_number() {
		return account_number;
	 }
	
	 public String getName() {
		return name;
	 }
	
	 public String getAccount_type() {
		 return account_type;
	 }
	
	 public double getBalance() throws LowBalanceException, NegativeBalanceException{
		 	if(balance < 0) {
				try {
					throw new NegativeBalanceException("Initial balance cannot be Negative");
				} 
				catch (NegativeBalanceException e) {
					System.out.println(e.getMessage());
				}
			}
			if(account_type.equalsIgnoreCase("Saving") && balance < 1000) {
				try {
					throw new LowBalanceException("Balance is less than minimum balance");
				} catch (LowBalanceException e) {
					System.out.println(e.getMessage());
				}
			}
			else if(account_type.equalsIgnoreCase("Current") && balance < 5000) {
				try {
					throw new LowBalanceException("Balance is less than minimum balance");
				} catch (LowBalanceException e) {
					System.out.println(e.getMessage());
				}
			}
		 
			return balance;
	 }
	
	 public void deposit(float amt) throws NegativeBalanceException, LowBalanceException, NumberFormatException, IOException{
		
		if(amt<0){
                try {
					throw new NegativeBalanceException("Amount to be Deposited Cannot be Negative");
				} catch (NegativeBalanceException e) {
					System.out.println(e.getMessage());
				}
         }
		else {
			if(account_type.equals("saving")) {
				balance=getBalance()+amt;
			}
			else {
				balance=getBalance()+amt;
			}
		}
	 }

	 public void withdraw(double amt) throws NegativeBalanceException,InsufficientBalanceException,LowBalanceException, NumberFormatException, IOException{
		 
		 if(amt<0){
             try {
					throw new NegativeBalanceException("Amount to be withdraw Cannot be Negative");
				} catch (NegativeBalanceException e) {
					System.out.println(e.getMessage());
				}
		 }
		 else {
			 if(account_type.equalsIgnoreCase("Saving") && amt < 1000) {
	        	 try {
					throw new InsufficientBalanceException("We can't withdraw amount, Insufficient balance");
				} catch (InsufficientBalanceException e) {
					System.out.println(e.getMessage());
				}
	         }
	         else if(account_type.equalsIgnoreCase("Current") && amt < 5000) {
	        	 try {
					throw new InsufficientBalanceException("We can't withdraw amount, Insufficient balance");
				} catch (InsufficientBalanceException e) {
					System.out.println(e.getMessage());
				}
	         }
	         else{
	             balance=getBalance()-amt; 
	         
	         }
		 }
	 }
//	 
//	 public void displayAcc(double amt) throws LowBalanceException, NegativeBalanceException {
//		 if(account_type.equalsIgnoreCase("Saving") && amt < 1000) {
//        	 try {
//				throw new InsufficientBalanceException("Sorry we can't create account. Balance should be greater than 1000");
//			} catch (InsufficientBalanceException e) {
//				System.out.println(e.getMessage());
//			}
//         }
//         else if(account_type.equalsIgnoreCase("Current") && amt < 5000) {
//        	 try {
//				throw new InsufficientBalanceException("Sorry we can't create account. Balance should be greater than 5000");
//			} catch (InsufficientBalanceException e) {
//				System.out.println(e.getMessage());
//			}
//         }
//         else{
//			 System.out.println("Account Details :");
//			 System.out.println("Account_number : "+getAccount_number());
//			 System.out.println("Name : "+getName());
//			 System.out.println("Account_type : "+getAccount_type());
//			 System.out.println("Balance : "+getBalance());
//         }
//	 }
	 
}
