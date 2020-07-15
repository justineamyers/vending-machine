package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
//import java.util.Formatter;  Looks like we don't need this
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**************************************************************************************************************************
*  This is your Vending Machine Command Line Interface (CLI) class
*
*  It is the main process for the Vending Machine
*
*  THIS is where most, if not all, of your Vending Machine interactions should be coded
*  
*  It is instantiated and invoked from the VendingMachineApp (main() application)
*  
*  Your code should be placed in here
***************************************************************************************************************************/
import com.techelevator.view.Menu; // Gain access to Menu class provided for the Capstone

public class VendingMachineCLI {


	NumberFormat formatter = NumberFormat.getCurrencyInstance();	//$0.00 format for printing money to console and files
	VendingMachine vendingMachine = new VendingMachine();
	BankAccount account = new BankAccount();
	ArrayList<String> listOfSales = new ArrayList<>();				
	double totalSales = 0.0;

	File logAuditFile = new File("Log.txt");						
	File salesReport = new File("salesReport" + LocalDateTime.now() + ".txt");	

	PrintWriter writeAudit;
	PrintWriter writeSales;
	Scanner keyboard;

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";	
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_SALES_REPORT = "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT, MAIN_MENU_SALES_REPORT };
	private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT,
			PURCHASE_MENU_FINISH_TRANSACTION,  };

	private Menu vendingMenu; // Menu object to be used by an instance of this class

	public VendingMachineCLI(Menu menu) throws FileNotFoundException { // Constructor - user will pas a menu for this
																		// class to use
		this.vendingMenu = menu; // Make the Menu the user object passed, our Menu
		this.writeAudit = new PrintWriter(logAuditFile);		// to print to Audit File
		this.writeSales = new PrintWriter(salesReport);			// to print to Sales Report
		this.keyboard = new Scanner(System.in);

		try {

			if (!salesReport.isFile()) {			// If sales report does not already exist, create it
				salesReport.createNewFile();		
			}
			if (!logAuditFile.isFile()) {			// If audit file does not already exist, create it
				logAuditFile.createNewFile();
			}
		} catch (IOException exception) {
		}

	}

	/**************************************************************************************************************************
	 * VendingMachineCLI main processing loop
	 * 
	 * Display the main menu and process option chosen
	 *
	 * It is invoked from the VendingMachineApp program
	 *
	 * THIS is where most, if not all, of your Vending Machine objects and
	 * interactions should be coded
	 *
	 * Methods should be defined following run() method and invoked from it
	 *
	 ***************************************************************************************************************************/

	boolean shouldProcess = true;

	public void run() {

		vendingMachine.createInventory();		// Stock 5 of each item

		while (shouldProcess) { // Loop until user indicates they want to exit

			String choice = (String) vendingMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS); // Display menu and get choice

			switch (choice) { // Process based on user menu choice

			case MAIN_MENU_OPTION_DISPLAY_ITEMS:
				displayItems(); // invoke method to display items in Vending Machine
				break; // Exit switch statement

			case MAIN_MENU_OPTION_PURCHASE:
				purchaseItems(); // invoke method to purchase items from Vending Machine
				break; // Exit switch statement

			case MAIN_MENU_OPTION_EXIT:
				endMethodProcessing(); // Invoke method to perform end of method processing
				shouldProcess = false; // Set variable to end loop
				break;

			case MAIN_MENU_SALES_REPORT:
				salesReportProcessing(listOfSales);
				break; // Exit switch statement
			}
		}
		return; // End method and return to caller
	}

	/********************************************************************************************************
	 * Methods used to perform processing
	 ********************************************************************************************************/

	public void displayItems() { // static attribute used as method is not associated with specific object
									// instance
		vendingMachine.toString();

	}

	public void purchaseItems() { // static attribute used as method is not associated with specific object
		boolean shouldLoop = true;
		while (shouldLoop) {
			System.out.println("\nCurrent money provided: " + formatter.format(account.getBalance()));	//Print current money in user account
			String choice = (String) vendingMenu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

			switch (choice) { // Process based on user menu choice

			case PURCHASE_MENU_FEED_MONEY:
				System.out.println("\nCurrent Money Provided: " + formatter.format(account.getBalance()));
				System.out.println(
						"How much money are you feeding? Only takes whole dollar amounts! \n 1 for $1 \n 2 for $2 \n 5 for $5 \n 10 for $10");	
				System.out.println("Enter 0 to stop adding money");		// User can continue to add money until they press 0. All money will add to total in user account
				int moneyAdded = 0;
				String moneyAddedString = "";
				do {
					moneyAddedString = keyboard.nextLine(); // Takes in the value as a string so we can check for null or non int values
					moneyAdded = vendingMachine.moneyValidation(moneyAddedString); // Validates the input and either prints to the screen or saves value to variable

					switch (moneyAdded) {
					case 1:
					case 2:
					case 5:
					case 10:
						account.feedMoney(moneyAdded);
						System.out.println("\nCurrent Money Provided: " + formatter.format(account.getBalance()));	// print user account balance
						writeAudit.println(LocalDateTime.now() + " FEED MONEY: " + formatter.format((moneyAdded)) + " "		// print date/time and money added to the audit file
								+ formatter.format((account.getBalance())));
						writeAudit.flush();		// clear buffer 
						break;
					case 0:
						break;
					default:
						System.out.println("Invalid money selection! I only take $1, $2, $5, or $10.");	// will print if user provides other than 1, 2, 5, 10, or 0
						System.out.println("\nCurrent Money Provided: " + account.getBalance());		// print balance
						break;
					}
					
				} while (moneyAdded != 0);
				break; // Exit switch statement

			case PURCHASE_MENU_SELECT_PRODUCT:
				String name = "";
				vendingMachine.toString(); // Display the CURRENT offerings
				System.out.println("What item would you like to purchase? Enter the code >>> ");
				String slotLocation = keyboard.nextLine().toUpperCase(); // Takes in the slot location to purchase, sends it to purchase method
				if (!vendingMachine.verifySlotLocation(slotLocation) || slotLocation.isEmpty() ) {		// If slot location does not exist, or is empty prints statement and sends user back to purchase menu
					System.out.println("Invalid Selection, please choose again");
					break;
				} else if (vendingMachine.getQuantity(slotLocation) <= 0) {		// If slot location exists, but no items remain
					System.out.println("SOLD OUT");
				} else {
					if (account.getBalance() >= vendingMachine.findPrice(slotLocation)) {	// If user balance is more than cost of item selected
						System.out.println(vendingMachine.purchase(account, slotLocation));	// 	print purchase info
						account.setBalance(account.getBalance() - vendingMachine.findPrice(slotLocation));	// subtract cost of item from user balance
						//name = vendingMachine.inventory.get(slotLocation).get(0).getName();		// name of item purchased
						name = vendingMachine.inventory.get(slotLocation).getName();
						writeAudit.println(LocalDateTime.now() + " " + name + " " + slotLocation + " "	//Print date/time, name of item, slot location, 
								+ formatter.format((vendingMachine.findPrice(slotLocation))) + " "			// price of item,
								+ formatter.format((account.getBalance())));								//  and balance to audit file
						writeAudit.flush();															// clear buffer
						totalSales += vendingMachine.findPrice(slotLocation);		// add price of item to total sales $
						listOfSales = vendingMachine.addToListOfSales(name);
						//listOfSales.add(name);										// add name of item to list of sold items
					} else {
						System.out.println("You must add more money to purchase that item!");	// if user account does not have enough money to purchase
					}
				}
				break; // Exit switch statement

			case PURCHASE_MENU_FINISH_TRANSACTION:
				writeAudit.println(LocalDateTime.now() + " GIVE CHANGE: " + formatter.format((account.getBalance())) + " $0.00");	// print date/time, amount of money returned to user, and updated balance of $0.00 to audit file 
				writeAudit.flush();			// clear buffer
				System.out.println(account.returnChange()); // Invoke method to perform end of method processing
				shouldProcess = true; // Set variable to end loop
				shouldLoop = false;
				break; // Exit switch statement
			}
		}
		return; // End method and return to caller
	}

	public TreeMap<String, Integer> salesReportProcessing(ArrayList<String> listOfSales) {
		TreeMap<String, Integer> salesReportMap = new TreeMap<>();
		for (String name : listOfSales) {
			if (!salesReportMap.containsKey(name)) {
				salesReportMap.put(name, 1);
			} else if (salesReportMap.containsKey(name)) {

				salesReportMap.put(name, (salesReportMap.get(name) + 1));
			}
		}

		Set<String> keys = salesReportMap.keySet();
		for (String key : keys) {
			writeSales.println(key + "|" + salesReportMap.get(key));
		}
		writeSales.println("\n Total sales: " + formatter.format(totalSales));
		return salesReportMap;
	}
	
	

	public void endMethodProcessing() { // static attribute used as method is not associated with specific object
		System.out.println("******************************************************************");
		System.out.println("Thank you for visiting Justine and Matthias's Vending Machine! :) ");
		System.out.println("Always remember: This is America, code however you want! ");
		System.out.println("******************************************************************");
		writeAudit.close();
		writeSales.close();
		keyboard.close();
		// Graceful exit and closes all open scanners. 
	}
}
