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
}
