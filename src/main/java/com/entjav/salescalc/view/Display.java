package com.entjav.salescalc.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.entjav.salescalc.model.EmployeeBean;

public class Display {
	
	private static PrintWriter htmlOut;

	public static void printPayrollReceipt(HttpServletResponse res, EmployeeBean employee) throws IOException {
		

		htmlOut = res.getWriter();
			
		// add custom css
		htmlOut.print("<link rel=\"stylesheet\" href=\"./assets/css/style.css\" />");
			
		htmlOut.print(""
				+ "<div class='container'>"
				+ "<fieldset>"
				+ "<legend>"+ employee.getEmployeeName() +"'s Payroll Details</legend>"
				+ "<hr>"
				+ "<div>"
				+ "<div class='employee-identification'>"
				+ "<h2><strong>ID</strong>: " + employee.getEmployeeID() + "</h2>"
				+ "<h2><strong>Name</strong>: " + employee.getEmployeeName() + "</h2>"
				+ "</div>"
				+ "<hr>"
				+ "<div class='employee-payroll-details'>"
				+ "<table>"
				+ "<thead>"
				+ "<tr>"
				+ "<th colspan='2'>Employee Payroll Details</th>"
				+ "</tr>"
				+ "</thead>"
				+ "<tbody>"
				+ "<tr>"
				+ "<td>"+ employee.getEmployeeName() +"'s Sales</td>"
				+ "<td class='money'>PHP " + String.format("%12.2f", employee.getSalesAmount()) + "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>Sales Code</td>"
				+ "<td>" + employee.getSalesCode().toUpperCase() + "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td class='table-spacer' colspan='2'><hr></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>Gross Earned Amount</td>"
				+ "<td class='money'>PHP "+ String.format("%12.2f", employee.getGrossEarnedAmount()) +"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>Sales Commission</td>"
				+ "<td class='money'>PHP "+ String.format("%12.2f", employee.getSalesCommission()) +"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td class='table-spacer' colspan='2'><hr></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>Take Home Pay</td>"
				+ "<td class='money'>PHP "+ String.format("%12.2f", employee.getTakeHomePay()) +"</td>"
				+ "</tr>"
				+ "</tbody>"
				+ "</table>"
				+ "</div>"
				+ "</div>"
				+ "</fieldset>"
				+ "</div>"
				+ "<button><a href=\"userinput.html\" class='button-text'>Go Back</a></button>"
				+ "");
			
	}
	
	public static void printHumanError(HttpServletResponse res, String msgPrompt) throws IOException {
		
		htmlOut = res.getWriter();

		// add custom css
		htmlOut.print("<link rel=\"stylesheet\" href=\"./assets/css/style.css\" />");

		res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		htmlOut.print(""
				+ "<div class='box-error'>"
				+ "<h1 class='text-error-primary'> 400 Bad Request!</h1>"
				+ "<h3 class='text-error-secondary'>" + msgPrompt + "</h3>"
				+ "</div>"
				+ "<button><a href=\"userinput.html\" class='button-text'>Go Back</a></button>"
				+ "");
		
	}
	
}
