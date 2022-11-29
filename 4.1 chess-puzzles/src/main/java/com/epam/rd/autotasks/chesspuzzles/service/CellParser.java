package com.epam.rd.autotasks.chesspuzzles.service;

public class CellParser {
	
	public char getLetter(int index) {
		
		if (index % 8 == 0) {
			return 'A';
		} else if (index % 8 == 1) {
			return 'B';
		} else if (index % 8 == 2) {
			return 'C';
		} else if (index % 8 == 3) {
			return 'D';
		} else if (index % 8 == 4) {
			return 'E';
		} else if (index % 8 == 5) {
			return 'F';
		} else if (index % 8 == 6) {
			return 'G';
		} else if (index % 8 == 7) {
			return 'H';
		} else throw new IllegalArgumentException("index is negative");
	}
	
	public int getNumber(int index) {
		
		if (index < 8) {
			return 8;
		} else if (index < 16) {
			return 7;
		} else if (index < 24) {
			return 6;
		} else if (index < 32) {
			return 5;
		} else if (index < 40) {
			return 4;
		} else if (index < 48) {
			return 3;
		} else if (index < 56) {
			return 2;
		} else if (index < 64) {
			return 1;
		} else throw new IllegalArgumentException("index is negative");
	}
}
