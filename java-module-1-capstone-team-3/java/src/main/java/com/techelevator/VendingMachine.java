package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class VendingMachine {

	private String slotLocation = "";
	String line = "";
	String name;
	Double price;
	String type;
	int quantity;

	NumberFormat formatter = NumberFormat.getCurrencyInstance();
	ArrayList<Item> items = new ArrayList<>();
	TreeMap<String, Item> inventory = new TreeMap<>();
	Set<String> keys = inventory.keySet();

	public TreeMap<String, Item> createInventory() {
		File itemFile = new File("vendingmachine.csv");
		Scanner fileReader;
		try {
			fileReader = new Scanner(itemFile);

			while (fileReader.hasNext()) {
				line = fileReader.nextLine();

				String[] itemLines = line.split("\\|");
				for (int i = 0; i < itemLines.length; i++) {
					slotLocation = itemLines[0];
					name = itemLines[1];
					price = Double.parseDouble(itemLines[2]);
					type = itemLines[3];

					Item thisItem = new Item(name, price, type);

					inventory.put(slotLocation, thisItem);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return inventory;
	}

	public int getQuantity(String slotLocation) {

		return quantity = inventory.get(slotLocation).getQuantity();
	}

	public int SetQuantity(int quantity) {

		this.quantity = quantity;

		return this.quantity;
	}

	public TreeMap<String, Item> removeItem(String slotLocation) {

		if (getQuantity(slotLocation) > 0) {
			inventory.get(slotLocation).setQuantity((getQuantity(slotLocation) - 1));
		} else {
			SetQuantity(0);
		}

		return inventory;
	}

	public String toString() {
		String printOut = "";
		Set<String> keys = inventory.keySet();
		for (String k : keys) {
			Item value = inventory.get(k);
			if (inventory.get(k).getQuantity() > 0) {
				System.out.println(k + ": " + value + "Quantity: " + getQuantity(k));
			} else {
				System.out.println(k + ": " + value + "SOLD OUT");
			}

		}
		return printOut;
	}

	public double findPrice(String slotLocation) {

		double price = 0.0;

		price = inventory.get(slotLocation).getPrice();

		return price;
	}

	public boolean verifySlotLocation(String slotLocation) {
		return (inventory.containsKey(slotLocation));
	}

	public String purchase(BankAccount account, String slotLocation) {
		String returnMessage = "";

		Item setOfItems = inventory.get(slotLocation);
		if (setOfItems.getQuantity() > 0) {
			returnMessage = setOfItems.getName() + " price: " + formatter.format(findPrice(slotLocation))
					+ " Money Remaining: " + formatter.format((account.getBalance() - findPrice(slotLocation))) + "\n"
					+ setOfItems.getReturnMessage();

		} else {
			returnMessage = "SOLD OUT";
		}

		removeItem(slotLocation);

		return returnMessage;
	}

	public int moneyValidation(String moneyAddedString) {
		int moneyAdded = 0;

		if (!moneyAddedString.isEmpty()) {
			try {
				moneyAdded = Integer.parseInt(moneyAddedString); // Add money added to user account
			} catch (NumberFormatException exception) {
				System.out.println("You entered an invalid option, please enter a whole number");
			}
		} else {
			System.out.println("Invalid, please enter a valid whole dollar amount");
		}

		return moneyAdded;
	}
	
	public ArrayList<String> addToListOfSales(String name) {
		ArrayList<String> listOfSales = new ArrayList<>();
		listOfSales.add(name);
		return listOfSales;
	}

}
