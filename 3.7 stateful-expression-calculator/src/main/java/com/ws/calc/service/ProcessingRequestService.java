package com.ws.calc.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ProcessingRequestService {
	
	/**
	 * Get expression from session
	 * Insert values of variables instead of letters.
	 * (c*(a-b)/b)*a   ->  (45*(24-38)/38)*24
	 */
	
	public String processingRequest(HttpSession session, HttpServletResponse resp) throws IOException {
		
		StringBuilder output = new StringBuilder();
		
		String expression = "";
		Object expressionObject = session.getAttribute("expression");
		if (expressionObject instanceof String) {
			expression = (String) expressionObject;
		}
		
		expression = expression.replaceAll(" ", "");
		for (int i = 0; i < expression.length(); i++) {
			String ch = String.valueOf(expression.charAt(i));
			if (Character.isLetter(expression.charAt(i))) {
				String value = (String) session.getAttribute(ch);
				if (value != null) {
					if (Character.isLetter(value.charAt(0))) {
						value = (String) session.getAttribute(String.valueOf(value.charAt(0)));
					}
				}
				output.append(value);
			} else {
				output.append(ch);
			}
		}
		return output.toString();
	}
}
