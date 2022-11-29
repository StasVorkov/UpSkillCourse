package com.ws.calc.servlets;

import com.ws.calc.service.ValidatorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/calc/expression"})
public class ExpressionServlet extends HttpServlet {
	
	private final ValidatorService validator = new ValidatorService();
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		String expression = request.getReader().readLine();
		
		if (validator.validationExpression(expression)) {
			if (session.getAttribute("expression") == null && !expression.isEmpty()) {
				response.setStatus(HttpServletResponse.SC_CREATED);
			} else {
				response.setStatus(HttpServletResponse.SC_OK);
			}
			session.setAttribute("expression", expression);
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Wrong Format");
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute("expression", null);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
}
