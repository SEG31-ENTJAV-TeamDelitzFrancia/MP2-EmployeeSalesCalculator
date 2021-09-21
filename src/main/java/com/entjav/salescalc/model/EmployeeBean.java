package com.entjav.salescalc.model;

import java.io.Serializable;

import javax.servlet.ServletConfig;

public class EmployeeBean implements Serializable {

	/**
	 * Auto-generated serial version UID for Employee Class (cuz why not bruh)
	 */
	private static final long serialVersionUID = -1224771227435510280L;
	
	// -- init config values -- //
	private ServletConfig conf;
	
	// -- user inputs -- //
	private String employeeID;
	private String employeeName;
	private String salesCode;
	private double salesAmount;
	
	// -- computed outputs -- //
	private double takeHomePay;
	private double grossEarnedAmount;
	private double salesCommission;
	
	// -- constructor -- //
	public EmployeeBean() {
		employeeID = "";
		employeeName = "";
		salesCode = "";
		salesAmount = 0;
		
		takeHomePay = 0;
		grossEarnedAmount = 0;
		salesCommission = 0;
	}
	public EmployeeBean(ServletConfig config) {
		this.conf = config;
	}

	// -- input variables -- //
	
	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getSalesCode() {
		return salesCode;
	}

	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}

	public double getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(double salesAmount) {
		this.salesAmount = salesAmount;
	}
	
	// -- -- //
	
	// -- computed variables -- //
	
	public double getTakeHomePay() {
		computeTakeHomePay();
		return takeHomePay;
	}

	public double getGrossEarnedAmount() {
		computeTakeHomePay();
		return grossEarnedAmount;
	}

	public double getSalesCommission() {
		return salesCommission;
	}
	
	// -- -- //

	// -- computation methods -- //
	public void computeSalesCodeAlpha() {
		grossEarnedAmount = 175 + (0.5 * salesAmount);
	}
	
	public void computeSalesCodeBravo() {
		grossEarnedAmount = 100 + (0.2 * salesAmount);
	}
	
	public void computeSalesCodeCharlie() {
		grossEarnedAmount = 50 + (0.25 * salesAmount);
	}
	
	public void computeSalesAdditionalCommisson() {
		if (salesAmount >= 2500) {
			salesCommission = Double.parseDouble(conf.getInitParameter("ADDITIONAL_SALES_COMMISSION")) * salesAmount;
		}
	}
	
	public void computeTakeHomePay() {
		
		computeSalesAdditionalCommisson();
		
		switch(salesCode) {
			case "A":
			case "a":
				computeSalesCodeAlpha();
			case "B":
			case "b":
				computeSalesCodeBravo();
			case "C":
			case "c":
				computeSalesCodeCharlie();
				
			default:
				System.err.println("Error! This isn't supposed to happen..");
				
		}
		
		takeHomePay = grossEarnedAmount + salesCommission;
		
	}
	
}
