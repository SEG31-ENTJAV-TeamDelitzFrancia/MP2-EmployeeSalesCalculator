package com.entjav.salescalc.model;

import java.io.Serializable;

import javax.servlet.ServletContext;

public class EmployeeBean implements Serializable {

	private static final long serialVersionUID = -1224771227435510280L;
	
	// -- init config values -- //
	private ServletContext conf;
	
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
	public EmployeeBean(ServletContext config) {
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
	/* TODO: Set all hardcoded values to serverContext Values 
	 * available context values:
	 * SALES_CODE_ALPHA_PERCENT -> replace 0.5 with this
	 * SALES_CODE_ALPHA_ADDITIONAL -> replace 175 with this
	 * SALES_CODE_BRAVO_PERCENT -> replace 0.50 with this
	 * SALES_CODE_BRAVO_ADDITIONAL -> replace 100 with this
	 * SALES_CODE_CHARLIE_PERCENT -> replace 0.25 with this
	 * SALE_CODE_CHARLIE_ADDITIONAL -> replace 50 with this
	 * MINIMUM_COMMISSION -> replace 2500 with this
	 * 
	 * */
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
		if (salesAmount > 2500) {
			salesCommission = Double.parseDouble(conf.getInitParameter("ADDITIONAL_SALES_COMMISSION")) * salesAmount;
		}
	}
	
	public boolean computeTakeHomePay() {
		
		computeSalesAdditionalCommisson();
		
		if(salesCode.equalsIgnoreCase("a")) 
				computeSalesCodeAlpha();
		else if(salesCode.equalsIgnoreCase("b"))
				computeSalesCodeBravo();
		else if(salesCode.equalsIgnoreCase("c"))
				computeSalesCodeCharlie();
		else
			return false;
		
		takeHomePay = grossEarnedAmount + salesCommission;
		return true;
		
	}
}
//this is a temporary comment created for testing purposes