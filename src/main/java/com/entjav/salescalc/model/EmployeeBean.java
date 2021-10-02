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
	public void computeSalesCodeAlpha() {
		grossEarnedAmount = Double.parseDouble(conf.getInitParameter("SALES_CODE_ALPHA_ADDITIONAL"))
				+ (Double.parseDouble(conf.getInitParameter("SALES_CODE_ALPHA_PERCENT")) * salesAmount);
	}
	
	public void computeSalesCodeBravo() {
		grossEarnedAmount = Double.parseDouble(conf.getInitParameter("SALES_CODE_BRAVO_ADDITIONAL")) 
				+ (Double.parseDouble(conf.getInitParameter("SALES_CODE_BRAVO_PERCENT")) * salesAmount);
	}
	
	public void computeSalesCodeCharlie() {
		grossEarnedAmount = Double.parseDouble(conf.getInitParameter("SALES_CODE_CHARLIE_ADDITIONAL")) 
				+ (Double.parseDouble(conf.getInitParameter("SALES_CODE_CHARLIE_PERCENT")) * salesAmount);
	}
	
	public void computeSalesAdditionalCommisson() {
		if (salesAmount > Double.parseDouble(conf.getInitParameter("MINIMUM_COMMISSION"))) {
			salesCommission = Double.parseDouble(conf.getInitParameter("ADDITIONAL_SALES_COMMISSION")) * salesAmount;
		}
	}
	
	public boolean computeTakeHomePay() {
		
		computeSalesAdditionalCommisson();
		
		if(salesCode.equalsIgnoreCase(conf.getInitParameter("SALES_CODE_ALPHA_ALIAS"))) 
				computeSalesCodeAlpha();
		else if(salesCode.equalsIgnoreCase(conf.getInitParameter("SALES_CODE_BRAVO_ALIAS")))
				computeSalesCodeBravo();
		else if(salesCode.equalsIgnoreCase(conf.getInitParameter("SALES_CODE_CHARLIE_ALIAS")))
				computeSalesCodeCharlie();
		else
			return false;
		
		takeHomePay = grossEarnedAmount + salesCommission;
		return true;
		
	}
}
