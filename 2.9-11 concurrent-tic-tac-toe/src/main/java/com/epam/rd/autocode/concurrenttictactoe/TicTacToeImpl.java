package com.epam.rd.autocode.concurrenttictactoe;

import java.util.Arrays;
import java.util.logging.Logger;

public class TicTacToeImpl implements TicTacToe {
	
	private static final char WHITESPACE = ' ';
	private static final int BOARD_SIZE = 3;
	private static final String OVERWRITING_MARK_ERROR_MESSAGE = "The cell is already filled";
	private static final Logger logger = Logger.getLogger(TicTacToeImpl.class.getSimpleName());
	private final char[][] board;
	private char lastMark;
	
	public TicTacToeImpl() {
		this.lastMark = WHITESPACE;
		this.board = new char[BOARD_SIZE][BOARD_SIZE];
		Arrays.stream(this.board).forEach(a -> Arrays.fill(a, WHITESPACE));
	}
	
	@Override
	public void setMark(int x, int y, char mark) {
		checkForOverwritingMark(x, y);
		board[x][y] = mark;
		lastMark = mark;
	}
	
	private void checkForOverwritingMark(final int x, final int y) {
		if (this.board[x][y] != WHITESPACE) {
			logger.severe(OVERWRITING_MARK_ERROR_MESSAGE);
			throw new IllegalArgumentException(OVERWRITING_MARK_ERROR_MESSAGE);
		}
	}
	
	@Override
	public char[][] table() {
		char[][] copyTable = new char[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < board.length; i++) {
			System.arraycopy(board[i], 0, copyTable[i], 0, board[i].length);
		}
		return copyTable;
	}
	
	@Override
	public char lastMark() {
		return lastMark;
	}
}
