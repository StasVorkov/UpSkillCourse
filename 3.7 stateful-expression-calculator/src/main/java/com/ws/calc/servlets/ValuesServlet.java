package com.ws.calc.servlets;

import com.ws.calc.service.ValidatorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "ValuesServlet", urlPatterns = "/calc/*")
public class ValuesServlet extends HttpServlet {
	
	private final ValidatorService validator = new ValidatorService();
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String uri = request.getRequestURI();
		String variable = uri.substring(uri.length() - 1);
		String value = request.getReader().readLine();
		
		if (validator.validationVariable(value)) {
			if (session.getAttribute(variable) == null && value != null) {
				response.setStatus(HttpServletResponse.SC_CREATED);
			} else {
				response.setStatus(HttpServletResponse.SC_OK);
			}
			session.setAttribute(variable, value);
		} else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String uri = request.getRequestURI();
		String variable = uri.substring(uri.length() - 1);
		
		session.setAttribute(variable, null);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
}
