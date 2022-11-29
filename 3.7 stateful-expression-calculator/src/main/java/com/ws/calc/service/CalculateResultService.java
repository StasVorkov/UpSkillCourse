package com.ws.calc.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Get expression from ProcessingRequestService and pass it to Calculator.
 * Getting the result from Calculator.
 */

public class CalculateResultService {
	
	private final CalculatorService calculator;
	private final ProcessingRequestService processingRequestService;
	private final ValidatorService validator;
	
	public CalculateResultService(CalculatorService calculator, ProcessingRequestService processingRequestService,
	                              ValidatorService validator)
	{
		this.calculator = calculator;
		this.processingRequestService = processingRequestService;
		this.validator = validator;
	}
	
	public String getResult(HttpSession session, HttpServletResponse response) throws IOException {
		
		String result;
		String mathExpression = processingRequestService.processingRequest(session, response);
		if (validator.validationExpression(mathExpression)) {
			result = calculator.calculateResult(mathExpression);
		} else {
			throw new IllegalArgumentException();
		}
		return result;
	}
}
