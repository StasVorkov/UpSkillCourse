package com.ws.calc.service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
	public String getExpression(HttpServletRequest request) {
		
		Map<String, String[]> parameterMap = request.getParameterMap();
		String expression = request.getParameter("expression").replaceAll(" ", "");
		
		expression = parseExpression(expression, parameterMap);
		
		List<String> expressionParts = transformExpressionWithOrder(expression);
		String output = expressionParts.get(0);
		
		for (int i = expressionParts.size() - 1; i > 0; --i) {
			String regex = "[()]";
			String withoutRegex = expressionParts.get(i);
			String simpleExpression = withoutRegex;
			withoutRegex = withoutRegex.replaceAll(regex, "");
			output = output.replace(simpleExpression, calculateExpression(withoutRegex));
		}
		output = output.replaceAll("[()]", "");
		return output;
	}
	
	private String parseExpression(String expression, Map<String, String[]> parameter) {
		String mathRegex = "[a-z]";
		Pattern var = Pattern.compile(mathRegex);
		Matcher matcher = var.matcher(expression);
		
		while (matcher.find()) {
			for (Map.Entry<String, String[]> e : parameter.entrySet())
				expression = expression.replace(e.getKey(), e.getValue()[0]);
		}
		return expression;
	}
	
	private List<String> transformExpressionWithOrder(String mathExpression) {
		List<String> expressionParts = new ArrayList<>();
		char firstBracket = '(';
		char lastBracket = ')';
		expressionParts.add(mathExpression);
		while (mathExpression.contains(Character.toString(firstBracket))) {
			int num = mathExpression.indexOf(firstBracket);
			int ordinal = 1;
			for (int i = num + 1; i < mathExpression.length(); ++i) {
				if (mathExpression.charAt(i) == firstBracket) {
					++ordinal;
				} else if (mathExpression.charAt(i) == lastBracket) {
					--ordinal;
				}
				if (mathExpression.charAt(i) == lastBracket && ordinal == 0) {
					expressionParts.add(mathExpression.substring(num, ++i));
					break;
				}
			}
			mathExpression = mathExpression.substring(++num);
		}
		System.out.println(expressionParts);
		return expressionParts;
	}
	
	public String calculateExpression(String expression) {
		expression = addSpace(expression);
		String[] arrayExpression = expression.split(" ");
		int length = arrayExpression.length;
		int i = 1;
		while (i < length - 1) {
			if (arrayExpression[i].equals("*") || arrayExpression[i].equals("/")) {
				arrayExpression[i - 1] = calculate(Integer.parseInt(arrayExpression[i - 1]),
						Integer.parseInt(arrayExpression[i + 1]),
						arrayExpression[i]);
				overwriting(arrayExpression, i);
				i = 1;
				length -= 2;
			} else
				i += 2;
		}
		i = 1;
		while (i < length - 1) {
			if (arrayExpression[i].equals("-") || arrayExpression[i].equals("+")) {
				arrayExpression[i - 1] = calculate(Integer.parseInt(arrayExpression[i - 1]),
						Integer.parseInt(arrayExpression[i + 1]),
						arrayExpression[i]);
				overwriting(arrayExpression, i);
				i = 1;
				length -= 2;
			} else
				i += 2;
		}
		return arrayExpression[0];
	}
	
	private String addSpace(String s) {
		StringBuilder output = new StringBuilder();
		String space = " ";
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			if (c == '*' || c == '/' || c == '+') {
				output.append(space).append(c).append(space);
			} else if (c == '-') {
				if (i != 0 && Character.isDigit(s.charAt(i - 1))) {
					output.append(space).append(c).append(space);
				} else {
					output.append(c);
				}
			} else {
				output.append(c);
			}
		}
		return output.toString();
	}
	
	private String calculate(int valueX, int valueY, String operator) {
		int result = 0;
		switch (operator) {
			case "*": {
				result = valueX * valueY;
				break;
			}
			case "/": {
				result = valueX / valueY;
				break;
			}
			case "+": {
				result = valueX + valueY;
				break;
			}
			case "-": {
				result = valueX - valueY;
				break;
			}
		}
		return String.valueOf(result);
	}
	
	private void overwriting(String[] arrStr, int position) {
		if (arrStr.length - (position + 2) >= 0) {
			System.arraycopy(arrStr, position + 2,
					arrStr, position, arrStr.length - (position + 2));
		}
	}
}
