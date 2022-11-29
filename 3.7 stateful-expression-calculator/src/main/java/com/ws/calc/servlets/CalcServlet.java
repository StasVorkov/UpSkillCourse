package com.ws.calc.servlets;

import com.ws.calc.service.CalculateResultService;
import com.ws.calc.service.CalculatorService;
import com.ws.calc.service.ProcessingRequestService;
import com.ws.calc.service.ValidatorService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "CalcServlet", urlPatterns = "/calc/result")
public class CalcServlet extends HttpServlet {
	
	private final CalculateResultService calculateResultService = new CalculateResultService(
			new CalculatorService(),
			new ProcessingRequestService(),
			new ValidatorService());
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		try {
			String result = calculateResultService.getResult(session, response);
			writer.print(result);
			response.setStatus(HttpServletResponse.SC_OK);
			writer.close();
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_CONFLICT, "Lack of data");
		}
	}
}
