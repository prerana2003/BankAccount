package BankAccountAssignment;

public class NegativeBalanceException extends Exception {
	
	public NegativeBalanceException(String msg) {
		super(msg);
	}
	
}
