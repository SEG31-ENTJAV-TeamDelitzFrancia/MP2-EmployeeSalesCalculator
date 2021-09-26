package com.entjav.salescalc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entjav.salescalc.model.EmployeeBean;
import com.entjav.salescalc.view.Display;

public class SalesCalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// Init MIME Type
		res.setContentType("text/html");
		
		// Data Extraction
		
		 String employeeID = req.getParameter("employeeID");
		 String employeeName = req.getParameter("employeeName");
		 String salesCode = req.getParameter("salesCode");
		 double salesAmount  = Double.parseDouble(req.getParameter("salesAmount"));
		 

		
		// Create Bean Singleton
		EmployeeBean employee = new EmployeeBean(getServletConfig());
		
		employee.setEmployeeID(employeeID);
		employee.setEmployeeName(employeeName);
		employee.setSalesCode(salesCode);
		employee.setSalesAmount(salesAmount);
		 
		employee.computeTakeHomePay();
		
		Display.printPayrollReceipt(res, employee);
			
	}

}
