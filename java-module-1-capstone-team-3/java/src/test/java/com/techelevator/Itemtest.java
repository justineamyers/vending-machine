package com.techelevator;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import junit.framework.Assert;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Itemtest {

	Item testObject = new Item("Matthias", 3.50, "Chip");
	
	@Test
	public void ctor_should_return_message_based_on_type() {
	//Cant figure out how to test for return message
		String returnMessage = testObject.getReturnMessage();
		String testAgainst = "Crunch Crunch, Yum!";
		assertEquals(testAgainst, returnMessage);
	}
	
	@Test
	public void ctor_should_return_name() {
		
		Assert.assertEquals("Matthias", testObject.getName());
	}
	
	@Test
	public void ctor_should_return_price() {
		
		Assert.assertEquals(3.50, testObject.getPrice());
	}
	
	@Test
	public void ctor_should_return_type() {
		
		Assert.assertEquals("Chip", testObject.getType());
	}
}
