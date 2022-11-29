package com.ws.calc.service;

public class ValidatorService {
	
	private static final int MIN_RANGE = -10000;
	private static final int MAX_RANGE = 10000;
	
	public boolean validationExpression(String s) {
		if (s == null || s.isEmpty() || s.contains("null")) {
			return false;
		}
		for (int i = 0; i < s.length() - 1; i++) {
			char s1 = s.charAt(i);
			char s2 = s.charAt(i + 1);
			if (Character.isLetter(s1) && Character.isLetter(s2))
				return false;
		}
		return true;
	}
	
	public boolean validationVariable(String s) {
		if (s == null || s.isEmpty()) {
			return false;
		}
		if (s.matches("[a-z]") && s.length() == 1) {
			return true;
		}
		int value = Integer.parseInt(s);
		return value >= MIN_RANGE && value <= MAX_RANGE;
	}
}


