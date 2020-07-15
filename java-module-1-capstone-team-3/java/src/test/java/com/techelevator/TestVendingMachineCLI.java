package com.techelevator;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import com.techelevator.view.Menu;

public class TestVendingMachineCLI {
	
	VendingMachine VMTestObject = new VendingMachine();
	BankAccount bankAccountTest = new BankAccount();
	Menu menu = new Menu(System.in, System.out);
	
	
	
	
	@Before
	public void setup() throws FileNotFoundException {
		VMTestObject.createInventory();
//		public TestVendingMachineCLI() throws FileNotFoundException {
//			VendingMachineCLI menuTest = new VendingMachineCLI(menu);
//		//}
//			menuTest.run();
	}
	

	@Test
	public void sales_report_processing_creates_TreeMap_correctly() throws FileNotFoundException {
		VendingMachineCLI menuTest = new VendingMachineCLI(menu);
		//TreeMap<String, Integer> salesReportMap = new TreeMap<>();
		TreeMap<String, Integer> salesReportTest = new TreeMap<>();
		ArrayList<String> listOfSales = new ArrayList<>();
//		bankAccountTest.feedMoney(10);
//		VMTestObject.purchase(bankAccountTest, "B3");
		listOfSales = VMTestObject.addToListOfSales(VMTestObject.inventory.get("B3").getName());
		//salesReportMap.put("B3", 1);
		salesReportTest = menuTest.salesReportProcessing(listOfSales);
		boolean test = salesReportTest.containsKey("Wonka Bar");
		assertTrue(test);
//		boolean test = (menuTest.salesReportProcessing().containsKey("Wonka Bar"));
//		assertTrue(test);
	}
	
	
//	@Test
//	public void () throws FileNotFoundException {
//		VendingMachineCLI menuTest = new VendingMachineCLI(menu);
//		TreeMap<String, Integer> salesReport = new TreeMap<>();
//		ArrayList<String> listOfSales = new ArrayList<>();
//		bankAccountTest.feedMoney(10);
//		VMTestObject.purchase(bankAccountTest, "B3");
//		listOfSales.add("Wonka Bar");
//		salesReport.put("B3", 1);
//		menuTest.salesReportProcessing();
//		boolean test = (salesReport.isFile());
//		assertTrue(test);
//		
//	}
	
	@Test
	public void sales_report_file_is_created() throws FileNotFoundException{
		VendingMachineCLI menuTest = new VendingMachineCLI(menu);
		boolean salesReportExists = menuTest.salesReport.isFile();
		assertTrue(salesReportExists);
	}
	
	@Test
	public void audit_file_is_created() throws FileNotFoundException{
		VendingMachineCLI menuTest = new VendingMachineCLI(menu);
		boolean logAuditFileExists = menuTest.logAuditFile.isFile();
		assertTrue(logAuditFileExists);
	}

}
