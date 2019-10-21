package edu.gcu.bootcamp.cst135.milestone.model;
import java.util.logging.Logger;
public abstract class Account {
	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private String accountNumber;
	private double accountBalance;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String toString() {
		return accountNumber + " $" + accountBalance;
	}

	public Account(String accountNumber, double accountBalance) {

		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		
	}
}
