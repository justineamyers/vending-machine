package com.techelevator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import junit.framework.Assert;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VendingMachinetest {

	VendingMachine VMObj = new VendingMachine();
	BankAccount accountTest = new BankAccount();
	
	
	@Before
	public void setup() {
		VMObj.createInventory();
	}

	@Test
	public void test_quantity_method_should_equal_5_at_initialization() {
		int testQuantity = VMObj.getQuantity("A1");
		assertEquals(5, testQuantity);
	}
	
	@Test
	public void test_quantity_method_should_equal_4_after_removing_1() {
		VMObj.removeItem("A1");
		int testQuantity = VMObj.getQuantity("A1");
		assertEquals(4, testQuantity);
	}
	
	@Test
	public void set_quantity_method_should_set_quantity_to_passed_in_value() {
		int testQuantity = VMObj.SetQuantity(0);
		assertEquals(0, testQuantity);
	}
	
	
	@Test
	public void remove_item_method_should_remove_multiples_above_zero() {
		VMObj.removeItem("A1");
		VMObj.removeItem("A1");
		VMObj.removeItem("A1");
		int testQuantity = VMObj.getQuantity("A1");
		assertEquals(2, testQuantity);
	}
	@Test
	public void remove_item_method_should_not_remove_below_zero() {
		VMObj.removeItem("A1");
		VMObj.removeItem("A1");
		VMObj.removeItem("A1");
		VMObj.removeItem("A1");
		VMObj.removeItem("A1");
		VMObj.removeItem("A1");
		int testQuantity = VMObj.getQuantity("A1");
		assertEquals(0, testQuantity);
	}
	
	@Test
	public void slot_location_method_returns_true_if_found() {
		assertTrue(VMObj.verifySlotLocation("A1"));
	}
	
	@Test
	public void slot_location_method_returns_false_if_not_found() {
		assertFalse(VMObj.verifySlotLocation("E6"));
	}
	
	@Test
	public void purchase_should_return_string() {
		assertNotNull(VMObj.purchase(accountTest, "A1"));
	}
	
	@Test
	public void purchase_should_remove_an_item_from_quantity() {
		VMObj.purchase(accountTest, "A1");
		int testQuantity = VMObj.getQuantity("A1");
		assertEquals(4, testQuantity);
	}
	
	@Test
	public void purchase_should_remove_an_item_from_quantity_if_run_more_than_once() {
		VMObj.purchase(accountTest, "A1");
		VMObj.purchase(accountTest, "A1");
		VMObj.purchase(accountTest, "A1");
		int testQuantity = VMObj.getQuantity("A1");
		assertEquals(2, testQuantity);
	}
	
	@Test
	public void purchase_should_remove_an_item_from_quantity_until_it_hits_0() {
		VMObj.purchase(accountTest, "A1");
		VMObj.purchase(accountTest, "A1");
		VMObj.purchase(accountTest, "A1");
		VMObj.purchase(accountTest, "A1");
		VMObj.purchase(accountTest, "A1");
		int testQuantity = VMObj.getQuantity("A1");
		assertEquals(0, testQuantity);
	}
	
	@Test
	public void purchase_should_remove_an_item_from_quantity_until_it_hits_0_then_stop_removing_items_but_still_run() {
		VMObj.purchase(accountTest, "A1");
		VMObj.purchase(accountTest, "A1");
		VMObj.purchase(accountTest, "A1");
		VMObj.purchase(accountTest, "A1");
		VMObj.purchase(accountTest, "A1");
		VMObj.purchase(accountTest, "A1");
		VMObj.purchase(accountTest, "A1");
		int testQuantity = VMObj.getQuantity("A1");
		assertEquals(0, testQuantity);
	}
	
	@Test
	public void find_price_method_correctly_identifies_price() {
		double testPrice = VMObj.findPrice("A1");
		assertEquals(3.05, testPrice, 0.00);
	}
	
	
}
