package edu.gcu.bootcamp.cst135.milestone.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import edu.gcu.bootcamp.cst135.milestone.model.Checking;
import edu.gcu.bootcamp.cst135.milestone.model.Customer;
import edu.gcu.bootcamp.cst135.milestone.model.Saving;
import edu.gcu.bootcamp.cst135.milestone.model.Transaction;

import java.util.logging.Logger;

public class Bank {
	private Customer customer;

	Boolean loggedIn = false;

	public Bank() throws ParseException {
		super();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		Date yourDate = df.parse("2018-05-30");
		customer = new Customer("Nolan", "Chostner", "Nolanchostner24", "password1", yourDate);
	}
	// This the login information

	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

	Scanner scanner = new Scanner(System.in);
	public Saving saving = new Saving("-SAV12345", 2500, 200, 25, .06);
	public Checking checking = new Checking("-CHK23456", 500, 25);

	private void viewExitScreen() {
		System.out.println("Goodbye, Have a good day!");
	}
	// When Exiting
	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public void viewCustomerMenu() {
		int option;
		do { 
			if (loggedIn) {
		
			System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			System.out.println("                MAIN MENU");
			System.out.println("                GCU BANK");
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			System.out.println("Pick an option: ");
			System.out.println("-----------------------");
			System.out.println(" 1: Deposit to Checking");
			System.out.println(" 2: Deposit to Savings");
			System.out.println(" 3: Withdraw from Checking");
			System.out.println(" 4: Withdraw from Savings");
			System.out.println(" 5: Get balance");
			System.out.println(" 6: Get monthly statement");
			System.out.println("------------------------");
			System.out.println(" 9: : Logout");
		} else {
			System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			System.out.println("Main menu");
			System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			System.out.println("1: log-in");
			System.out.println("9: Exit Bank"); 
		}
			// Menu after login
		option = getUserMenuChoice();
		if (loggedIn) {
			processCustomerMenu(option);
		} else {
			if (option == 1) {
				Login();}
			else if (option == 9) {
				viewExitScreen();
			}
		}
		} while (option != 9);
			}

	private void Login() {
		System.out.printf("Enter Username:");
		String username = scanner.nextLine();
		System.out.println("Enter Password:");
		String password = scanner.nextLine();

		if (customer.getUserName().equals(username) && customer.getPassword().equals(password)) {
			System.out.println("Welcome " + customer );
			System.out.println("You are logged in");
			loggedIn = true;
		} else {
			System.out.println("Incrrect username/password combination");
		}
	}
 // Login menu
	private int getUserMenuChoice() {
		int y = 1;

		int x = 0;
		while (x == 0) {
			String z = scanner.nextLine();

			try {
				y = Integer.parseInt(z);
				x = 1;
			} catch (Exception e) {
				System.out.println("We’re sorry an error occurred.  Our engineers are working on the problem");
				x = 0;
			}
		}
		return y;
	}

	private double getUserAmount() {
		double y = 1;

		int x = 0;
		while (x == 0) {
			String z = scanner.nextLine();

			try {
				y = Double.parseDouble(z);
				x = 1;
			} catch (Exception e) {
				System.out.println("Expecting a cash value. Please try again");
			}
		}
		return y;
	}

	private void processCustomerMenu(int parseInt) {

		switch (parseInt) {
		case 1:
			viewDepositChecking();
			viewBalances();
			break;
		case 2:
			viewDepositSavings();
			viewBalances();
			break;
		case 3:
			viewWithdrawalChecking();
			viewBalances();
			break;
		case 4:
			viewWithdrawalSavings();
			viewBalances();
			break;
		case 5:
			viewBalances();
			break;
		case 6:
			viewEndOfMonth();
			viewBalances();
			break;
		case 9:
			viewExitScreen();
			break;
		default:
			viewCustomerMenu();
		}
	}
 // All the options in menu
	private void viewEndOfMonth() {
// End of month statement
		System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("               END OF MONTH");
		System.out.println("                 GCU BANK");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");

		if (customer.getSaving().getAccountBalance() < customer.getSaving().getMinBalance()) {
			System.out.printf("A $%.2f service fee is being assessed for below minimum balance in savings",
					customer.getSaving().getServiceFee());
			customer.getSaving().setAccountBalance(customer.getSaving().getAccountBalance() - customer.getSaving().getServiceFee());
		}
		if (customer.getSaving().getAccountBalance() > 0) {
			customer.getSaving().setAccountBalance(customer.getSaving().getAccountBalance() + (customer.getSaving().getInterest() * customer.getSaving().getAccountBalance()));
			if (customer.getLoan().getLateFee() > 0) {
	            logger.info("Late fee assessed for loan");
	            System.out.printf("A late fee is being assessed for your loan: \n$");
	            System.out.println(customer.getLoan().getLateFee());
	            customer.getLoan()
	                    .setAccountBalance(customer.getLoan().getAccountBalance() - customer.getLoan().getLateFee());
	        }
	        System.out.printf("\nYour loan as increased due to interest by: \n$");
	        System.out.println(customer.getLoan().doEndofMonthInterest());
	        customer.getLoan()
	                .setAccountBalance(customer.getLoan().getAccountBalance() + customer.getLoan().doEndofMonthInterest());
			System.out.println("TRANSACTIONS:");
			System.out.println(transactions.size());
			for (int i = 0; i < transactions.size(); i++) {
				System.out.println(transactions.get(i));
			}
		}
	}
	// This is checking
	private void viewWithdrawalChecking() {

		System.out.println("How much would you like to withdraw: ");
		double input = getUserAmount();
		processWithdrawalChecking(input);
	}

	private void processWithdrawalChecking(double input) {

		if (checking.getAccountBalance() < input) {
			System.out.println("A $" + checking.getOverdraft()
					+ " overdraft fee will be assessed if you continue. Continue Y or N?");
			if (scanner.nextLine().toLowerCase().equals("y")) {
				customer.getChecking().setAccountBalance(customer.getChecking().getAccountBalance() - input - checking.getOverdraft());
				transactions.add(new Transaction(customer.getChecking().getAccountNumber(), "Widthdrawl from checking", input));
			} else
				viewWithdrawalChecking();
		} else {
			customer.getChecking().setAccountBalance(customer.getChecking().getAccountBalance() - input);
			transactions.add(new Transaction(customer.getChecking().getAccountNumber(), "Widthdrawl from checking", input));
		}
	}
	// This is Savings
	private void viewDepositSavings() {

		System.out.println("How much would you like to deposit: ");
		double input = getUserAmount();
		processDepositSavings(input);

	}

	private void processDepositSavings(double input) {

		customer.getSaving().setAccountBalance(saving.getAccountBalance() + input);
		transactions.add(new Transaction(customer.getSaving().getAccountNumber(), "Deposit to Savings", input));
	}
 //Checking again
	private void viewDepositChecking() {

		System.out.println("How much would you like to deposit: ");
		double input = getUserAmount();
		processDepositChecking(input);
	}

	private void processDepositChecking(double input) {

		customer.getChecking().setAccountBalance(customer.getChecking().getAccountBalance() + input);
		transactions.add(new Transaction(customer.getChecking().getAccountNumber(), "Deposit to checking", input));
	}
 // Savings again
	private void viewWithdrawalSavings() {

		System.out.println("How much would you like to withdraw: ");
		double input = getUserAmount();
		processWithdrawalSavings(input);
	}

	private void processWithdrawalSavings(double input) {

		customer.getSaving().setAccountBalance(customer.getSaving().getAccountBalance() - input);
		transactions.add(new Transaction(customer.getSaving().getAccountNumber(), "Widthdrawl from Savings", input));
	}

	private void viewBalances() {

		System.out.println("\n------------------------");
		System.out.println(customer.getSaving().toString());
		System.out.println(customer.getChecking().toString());
		System.out.println(customer.getLoan().toString());
		System.out.println("------------------------");
	}

}