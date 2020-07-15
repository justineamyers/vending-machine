package com.techelevator;

import java.util.ArrayList;
import java.util.HashMap;

public class Item {

	private String name;
	private Double price;
	private String type;
	private int quantity;
	private String returnMessage;

	public Item(String name, Double price, String type) {
		this.name = name;
		this.price = price;
		this.type = type;
		this.quantity = 5;
		// Set return message of item dependent upon type
		if (type.equals("Chip")) {
			this.returnMessage = "Crunch Crunch, Yum!";
		} else if (type.equals("Candy")) {
			this.returnMessage = "Munch Munch, Yum!";
		} else if (type.equals("Drink")) {
			this.returnMessage = "Glug Glug, Yum!";
		} else if (type.equals("Gum")) {
			this.returnMessage = "Chew Chew, Yum!";
		}
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String toString() {
		return name + " $" + price + " ";

	}

}
