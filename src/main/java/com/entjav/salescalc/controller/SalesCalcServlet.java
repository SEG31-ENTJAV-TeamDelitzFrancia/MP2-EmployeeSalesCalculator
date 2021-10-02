package com.entjav.salescalc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entjav.salescalc.model.EmployeeBean;
import com.entjav.salescalc.util.PrintHumanError;


/** 
 * Servlet that only handles sales calulation and data gathering
 * */
public class SalesCalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// Data Extraction		
		String employeeID = req.getParameter("employeeID");
		String employeeName = req.getParameter("employeeName");
		String salesCode = req.getParameter("salesCode");
		double salesAmount  = Double.parseDouble(req.getParameter("salesAmount"));
			
		// Create Bean
		EmployeeBean employee = new EmployeeBean(getServletContext());
		
		employee.setEmployeeID(employeeID);
		employee.setEmployeeName(employeeName);
		employee.setSalesCode(salesCode);
		employee.setSalesAmount(salesAmount);
	
		// error validation
		
		if(salesAmount < 1) {
			PrintHumanError.printCustomResponse(res, "Invalid Sales Amount!, should be at lest PHP 1.00");
		}
		else if(!employee.computeTakeHomePay()) {
			PrintHumanError.printCustomResponse(res, "Invalid Sales Code! Should only be the ones specified\n"
					+ "(A, B, or C)");
		}
		else {
			// if all checks passed, set employee EmployeeBean and forward to 
			// Display servlet
			req.setAttribute("employeeData", employee);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/payroll");
			dispatcher.forward(req, res);
		}
	}

	
	
}
