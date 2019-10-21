package edu.gcu.bootcamp.cst135.milestone.model;
import java.util.logging.Logger;
public class Checking extends Account {
	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private double overdraft;

	public double getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}

	public Checking(String accountNumber, double accountBalance, double overdraft) {
		super(accountNumber, accountBalance);
		this.overdraft = overdraft;
	}
}