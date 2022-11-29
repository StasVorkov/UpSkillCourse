package com.ws.calc.service;

import java.util.ArrayList;
import java.util.List;

public class CalculatorService {
	
	public static final int AMOUNT_OF_OPERANDS_IN_SIMPLE_CALCULATION = 2;
	
	/**
	 * 1. Method processingExpression() create a list of expressions in a calculated order:
	 * (45*(24-38)/38)*24   ->  [(45*(24-38)/38)*24, (45*(24-38)/38), (24-38)]
	 *
	 * 2. Method createExpressionWithoutParentheses() simplifies inputted expression to expression without parentheses:
	 * [(45*(24-38)/38)*24, (45*(24-38)/38), (24-38)] -> 45*-14/38*24
	 *
	 * 3. Methods calculateTheSimplestExpression() and overwritingExpression() give final result.
	 * 45*-14/38*24 -> 24
	 */
	
	public String calculateResult(String mathExpression) {
		List<String> list = processingExpression(mathExpression);
		String expressionWithoutParentheses = createExpressionWithoutParentheses(list);
		return calculateExpressionWithoutParentheses(expressionWithoutParentheses);
	}
	
	private List<String> processingExpression(String mathExpression) {
		List<String> stringParts = new ArrayList<>();
		char firstBracket = '(';
		char lastBracket = ')';
		stringParts.add(mathExpression);
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
					stringParts.add(mathExpression.substring(num, ++i));
					break;
				}
			}
			mathExpression = mathExpression.substring(++num);
		}
		return stringParts;
	}
	
	private String createExpressionWithoutParentheses(List<String> list) {
		String result;
		result = list.get(0);
		
		for (int i = list.size() - 1; i > 0; i--) {
			String s1 = list.get(i);
			String s2 = s1;
			s1 = s1.replaceAll("[()]", "");
			try {
				result = result.replace(s2, calculateExpressionWithoutParentheses(s1));
			} catch (Exception e) {
				break;
			}
		}
		result = result.replaceAll("[()]", "");
		return result;
	}
	
	private String calculateExpressionWithoutParentheses(String expression) {
		System.out.println("exp "+ expression);
		expression = addSpaceToExpression(expression);
		String[] arrExpr = expression.split(" ");
		int length = arrExpr.length;
		int i = 1;
		while (i < length - 1) {
			if (arrExpr[i].equals("*") || arrExpr[i].equals("/")) {
				int leftOperand = Integer.parseInt(arrExpr[i - 1]);
				int rightOperand = Integer.parseInt(arrExpr[i + 1]);
				arrExpr[i - 1] = calculateTheSimplestExpression(leftOperand,rightOperand,arrExpr[i]);
				overwritingExpression(arrExpr, i);
				i = 1;
				length -= AMOUNT_OF_OPERANDS_IN_SIMPLE_CALCULATION;
			} else
				i += AMOUNT_OF_OPERANDS_IN_SIMPLE_CALCULATION;
		}
		i = 1;
		while (i < length - 1) {
			if (arrExpr[i].equals("-") || arrExpr[i].equals("+")) {
				int leftOperand = Integer.parseInt(arrExpr[i - 1]);
				int rightOperand = Integer.parseInt(arrExpr[i + 1]);
				arrExpr[i - 1] = calculateTheSimplestExpression(leftOperand,rightOperand,arrExpr[i]);
				overwritingExpression(arrExpr, i);
				i = 1;
				length -= AMOUNT_OF_OPERANDS_IN_SIMPLE_CALCULATION;
			} else
				i += AMOUNT_OF_OPERANDS_IN_SIMPLE_CALCULATION;
		}
		return arrExpr[0];
	}
	
	private String addSpaceToExpression(String s) {
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
	
	private String calculateTheSimplestExpression(int valueX, int valueY, String operator) {
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
	
	private void overwritingExpression(String[] arrStr, int position) {
		if (arrStr.length - (position + AMOUNT_OF_OPERANDS_IN_SIMPLE_CALCULATION) >= 0) {
			System.arraycopy(arrStr, position + AMOUNT_OF_OPERANDS_IN_SIMPLE_CALCULATION,
					arrStr, position, arrStr.length - (position + AMOUNT_OF_OPERANDS_IN_SIMPLE_CALCULATION));
		}
	}
}
