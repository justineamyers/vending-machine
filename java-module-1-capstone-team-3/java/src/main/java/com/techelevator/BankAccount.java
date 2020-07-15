package com.techelevator;

public class BankAccount {

	private double balance;
	double change;
	private int quarter = 0;
	private int dime = 0;
	private int nickel = 0;
	

	public BankAccount() {
		balance = 0.0;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getQuarter() {
		return quarter;
	}

	public int getDime() {
		return dime;
	}

	public int getNickel() {
		return nickel;
	}

	public double feedMoney(int moneyAdded) {
		balance += moneyAdded;
		return balance;
	}

	public String returnChange() {
		change = balance;

		change *= 100;

			if (change >= 25 && !(change < 11)) {	// Do we really need the second part? If it's greater than 25 then it will also be greater than 11
				quarter = (int) (change / 25);
				change -= quarter * 25;
			}

			if (change >= 10 && !(change < 6)) {	// Same as above
				dime = (int) change / 10;
				change -= dime * 10;
			}

			if (change >= 5) {
				nickel = (int) change / 5;
				change -= nickel * 5;
				
			}
			
			balance = 0;
			return ("Change return to you is: \nQuarters: " + quarter + ", dimes: " + dime + ", nickels: " + nickel);
			

		

	}

}
