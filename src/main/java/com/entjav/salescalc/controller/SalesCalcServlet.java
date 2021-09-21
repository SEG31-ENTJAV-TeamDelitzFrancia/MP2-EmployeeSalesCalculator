package com.entjav.salescalc.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entjav.salescalc.model.EmployeeBean;


@WebServlet("/calcsales.action")
public class SalesCalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// Init MIME Type
		res.setContentType("text/html");
		
		// Data Extraction
		/*
		 * String employeeID;
		 * String employeeName;
		 * String salesCode;
		 * double salesAmount;
		 * */
		// TODO: awaiting for HTML
		
		// Create Bean Singleton
		EmployeeBean employee = new EmployeeBean(getServletConfig());
		/*
		 * employeeID = employee.setEmployeeID();
		 * employeeName = employee.setEmployeeName();
		 * salesCode = employee.setSalesCode();
		 * salesAmount = Double.parseDouble(employee.setSalesAmount());
		 * */
		
		// employee.computeTakeHomePay();
		
		// TODO: Print Take Home Pay
		
	}

}
