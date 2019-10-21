package edu.gcu.bootcamp.cst135.milestone.FullStackBanking;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

import edu.gcu.bootcamp.cst135.milestone.controller.Bank;

public class FullStackBanking {

	public static void main(String[] args) {
		final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		try {
			try {
				MyLogger.setup();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			Bank bank = new Bank();
			bank.viewCustomerMenu();
		} catch (Exception ex) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			logger.severe(sw.toString());
		}
	}
}
