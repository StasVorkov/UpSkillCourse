package com.epam.rd.autotasks.chesspuzzles;

import java.util.Collection;

public class Board implements ChessBoard {
	
	private static final String EMPTY_BOARD =   "........" +
												"........" +
												"........" +
												"........" +
												"........" +
												"........" +
												"........" +
												"........";
	public static final int SIZE_BOARD = 8;
	
	private final Collection<ChessPiece> pieces;
	
	public Board(Collection<ChessPiece> pieces) {
		this.pieces = pieces;
	}
	
	@Override
	public String state() {
		StringBuilder stringBuilder = new StringBuilder(EMPTY_BOARD);
		for (ChessPiece p : pieces) {
			if (p.getCell() == null) {
				continue;
			}
			int start = getMultiplier(p.getCell().number) * SIZE_BOARD + getNumberFromLetter(p.getCell().letter) - 1;
			int end =  getMultiplier(p.getCell().number) * SIZE_BOARD + getNumberFromLetter(p.getCell().letter);
			stringBuilder.replace(start, end, String.valueOf(p.toChar()));
		}
		addBreaks(stringBuilder);
		return stringBuilder.toString();
	}
	
	private void addBreaks(StringBuilder stringBuilder) {
		for (int i = SIZE_BOARD*(SIZE_BOARD-1); i > 0; i = i - SIZE_BOARD) {
			stringBuilder.insert(i, "\n");
		}
	}
	
	private int getMultiplier(int number) {
		switch (number) {
			case 1:
				return 7;
			case 2:
				return 6;
			case 3:
				return 5;
			case 4:
				return 4;
			case 5:
				return 3;
			case 6:
				return 2;
			case 7:
				return 1;
			case SIZE_BOARD:
				return 0;
		}
		throw new RuntimeException ("The replacement of the char in the board failed. Invalid number of the Cell");
	}
	
	private int getNumberFromLetter(char letter) {
		switch (letter) {
			case 'A':
				return 1;
			case 'B':
				return 2;
			case 'C':
				return 3;
			case 'D':
				return 4;
			case 'E':
				return 5;
			case 'F':
				return 6;
			case 'G':
				return 7;
			case 'H':
				return 8;
		}
		throw new RuntimeException ("The replacement of the char in the board failed. Invalid number of the Cell");
	}
}
