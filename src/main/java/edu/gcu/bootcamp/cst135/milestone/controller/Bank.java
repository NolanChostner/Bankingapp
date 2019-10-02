package edu.gcu.bootcamp.cst135.milestone.controller;

import java.util.Scanner;

import edu.gcu.bootcamp.cst135.milestone.model.Checking;
import edu.gcu.bootcamp.cst135.milestone.model.Saving;

public class Bank {
	Scanner scanner = new Scanner(System.in);
	public Saving saving = new Saving("-SAV12345",5000,200,25,.06);
	public Checking checking = new Checking("-CHK23456",5000,25);
	
	private void viewExitScreen() {
		System.out.println("Goodbye, Have a good day!");
	}

	public void viewCustomerMenu() {
			int option;
			do {
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
				option = getUserMenuChoice();
				processCustomerMenu(option);
			} while (option != 9);
		
		}

	
	private int getUserMenuChoice() {
			int y = 1;
			
			int x = 0;
					while (x == 0) {
						String z = scanner.nextLine();
						
						try {
							y = Integer.parseInt(z);
							x =1;
						}
						catch (Exception e) {
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
						x =1;
					}
					catch (Exception e) {
						System.out.println("Expecting a cash value. Please try again");
					}
				}
				return y;
	}
	

	private void processCustomerMenu(int parseInt) {

		switch(parseInt) {
		case 1: viewDepositChecking();viewBalances();
		break;
		case 2: viewDepositSavings();viewBalances();
		break;
		case 3: viewWithdrawalChecking();viewBalances();
		break;
		case 4: viewWithdrawalSavings();viewBalances();
		break;
		case 5: viewBalances();
		break;
		case 6: viewEndOfMonth();viewBalances();
		break;  
		case 9: viewExitScreen();
		break;
		default: viewCustomerMenu();
		}
	}

	private void viewEndOfMonth() {

		System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("               END OF MONTH");
		System.out.println("                 GCU BANK");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");

		if(saving.getAccountBalance() < saving.getMinBalance()) {
			System.out.printf("A $%.2f service fee is being assessed for below minimum balance in savings", saving.getServiceFee());
			saving.setAccountBalance(saving.getAccountBalance() - saving.getServiceFee());
		}
		if (saving.getAccountBalance() > 0){
			saving.setAccountBalance(saving.getAccountBalance() + (saving.getInterest() * saving.getAccountBalance()));
		}	
	}		

	private void viewWithdrawalChecking() {

		
			System.out.println("How much would you like to withdraw: ");
			double input = getUserAmount();
			processWithdrawalChecking(input);
		}
		
	

	private void processWithdrawalChecking(double input) {

		if(checking.getAccountBalance() < input) {
			System.out.println("A $" + checking.getOverdraft() + " overdraft fee will be assessed if you continue. Continue Y or N?");
			if(scanner.nextLine().toLowerCase().equals("y")) {
				checking.setAccountBalance(checking.getAccountBalance() - input - checking.getOverdraft());
			}else
				viewWithdrawalChecking();
		}else
			checking.setAccountBalance(checking.getAccountBalance() - input);
	}

	private void viewDepositSavings() {

		
			System.out.println("How much would you like to deposit: ");
			double input = getUserAmount();
			processDepositSavings(input);
		
	}

	private void processDepositSavings(double input) {

		saving.setAccountBalance(saving.getAccountBalance() + input);
	}

	private void viewDepositChecking() {

		
			System.out.println("How much would you like to deposit: ");
			double input = getUserAmount();
			processDepositChecking(input);
		}
	

	private void processDepositChecking(double input) {

		checking.setAccountBalance(checking.getAccountBalance() + input);
	}

	private void viewWithdrawalSavings() {

		
			System.out.println("How much would you like to withdraw: ");
			double input = getUserAmount();
			processWithdrawalSavings(input);
		}
	

	private void processWithdrawalSavings(double input) {

		saving.setAccountBalance(saving.getAccountBalance() - input);	
	}

	private void viewBalances() {

		System.out.println("\n------------------------");	
		System.out.println(saving.toString());
		System.out.println(checking.toString());
		System.out.println("------------------------");
	}
}