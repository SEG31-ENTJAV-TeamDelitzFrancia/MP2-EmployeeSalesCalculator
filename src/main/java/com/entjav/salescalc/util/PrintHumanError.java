package com.entjav.salescalc.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;


public class PrintHumanError {

	private static PrintWriter htmlOut;
	
	/** 
	 * Prints a custom bad request if ever user does invalid input on any 
	 * query parameter.
	 * @param res - HttpServletResponse
	 * @param msgPrompt - Custom message prompt
	 * */
	public static void printCustomResponse(HttpServletResponse res, String msgPrompt) throws IOException {
		
		//set the MIME type
		res.setContentType("text/html;charset=UTF-8");
		
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
