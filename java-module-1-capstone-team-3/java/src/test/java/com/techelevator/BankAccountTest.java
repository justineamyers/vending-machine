package com.techelevator;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankAccountTest {
	
	BankAccount testClass = new BankAccount();
	

	@Test
	public void feed_money_increases_balance() {
		int moneyAdded = 10;
		double balance = testClass.feedMoney(moneyAdded);
		assertEquals(10, balance, 0.0);
	}
	

	@Test
	public void feed_money_0_balance() {
		int moneyAdded = 0;
		double balance = testClass.feedMoney(moneyAdded);
		assertEquals(0, balance, 0.0);
	}
	
	
	@Test 
	public void return__cents_gives_correct_coins_from_10_dollar_balance() {
		int moneyAdded = 10;
		testClass.feedMoney(moneyAdded);
		testClass.returnChange();
		int quartersExpected = 40;
		assertEquals(quartersExpected, testClass.getQuarter());
		assertEquals(0, testClass.getDime());
		assertEquals(0, testClass.getNickel());
	}
	
	@Test 
	public void return__cents_gives_correct_coins_from_non_whole_dollar_balance() {
		testClass.setBalance(3.65);
		testClass.returnChange();
		assertEquals(14, testClass.getQuarter());
		assertEquals(1, testClass.getDime());
		assertEquals(1, testClass.getNickel());
	}
	
	@Test 
	public void return__cents_gives_correct_coins_from_zero_balance() {
		testClass.setBalance(0);
		testClass.returnChange();
		assertEquals(0, testClass.getQuarter());
		assertEquals(0, testClass.getDime());
		assertEquals(0, testClass.getNickel());
	}
	
	@Test 
	public void return__cents_gives_correct_coins_from_negative_balance() {
		testClass.setBalance(-2);
		testClass.returnChange();
		assertEquals(0, testClass.getQuarter());
		assertEquals(0, testClass.getDime());
		assertEquals(0, testClass.getNickel());
	}
	
	
}
