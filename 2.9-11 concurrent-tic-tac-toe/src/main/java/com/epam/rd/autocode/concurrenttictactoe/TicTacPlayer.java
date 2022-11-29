package com.epam.rd.autocode.concurrenttictactoe;

public class TicTacPlayer implements Player {
	
	private static final char WHITESPACE = ' ';
	private final TicTacToe ticTacToe;
	private final char mark;
	private final PlayerStrategy strategy;
	
	public TicTacPlayer(final TicTacToe ticTacToe, final char mark, final PlayerStrategy strategy) {
		this.ticTacToe = ticTacToe;
		this.mark = mark;
		this.strategy = strategy;
	}
	
	@Override
	public void run() {
		
		while (!isGameOver()) {
			synchronized (ticTacToe) {
				
				if (isTheFirstMove()) {
					if (mark == 'X') {
						addMarkToTable();
					}
				} else if (isAbleToMove()) {
					addMarkToTable();
				}
			}
		}
	}
	
	private void addMarkToTable() {
		Move move = strategy.computeMove(mark, ticTacToe);
		ticTacToe.setMark(move.row, move.column, mark);
	}
	
	private boolean isTheFirstMove() {
		return ticTacToe.lastMark() == WHITESPACE;
	}
	
	private boolean isAbleToMove() {
		return !isGameOver() && ticTacToe.lastMark() != mark;
	}
	
	private boolean isGameOver() {
		return !hasTableEmptyCells() || isPlayerWin();
	}
	
	private boolean hasTableEmptyCells() {
		
		for (char[] charArray : ticTacToe.table()) {
			for (char c : charArray) {
				if (c == WHITESPACE) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isPlayerWin() {
		
		for (int i = 0; i < ticTacToe.table().length; i++) {
			if (hasHorizontalBingo(i) || hasVerticalBingo(i)) {
				return true;
			}
		}
		return hasDiagonalBingo();
	}
	
	private boolean hasHorizontalBingo(int row) {
		
		char tableCell = ticTacToe.table()[row][0];
		for (int i = 0; i < ticTacToe.table()[row].length; i++) {
			if (ticTacToe.table()[row][i] != tableCell) {
				return false;
			}
			tableCell = ticTacToe.table()[row][i];
		}
		return tableCell != WHITESPACE;
	}
	
	private boolean hasVerticalBingo(int column) {
		
		char tableCell = ticTacToe.table()[0][column];
		for (int i = 0; i < ticTacToe.table().length; i++) {
			if (ticTacToe.table()[i][column] != tableCell) {
				return false;
			}
			tableCell = ticTacToe.table()[i][column];
		}
		return tableCell != WHITESPACE;
	}
	
	private boolean hasDiagonalBingo() {
		return hasTheFirstDiagonalBingo() || hasTheSecondDiagonalBingo();
	}
	
	private boolean hasTheFirstDiagonalBingo() {
		
		char tableCell = ticTacToe.table()[0][0];
		for (int i = 0; i < ticTacToe.table().length; i++) {
			if (ticTacToe.table()[i][i] != tableCell) {
				return false;
			}
			tableCell = ticTacToe.table()[i][i];
		}
		return tableCell != WHITESPACE;
	}
	
	private boolean hasTheSecondDiagonalBingo() {
		
		int lastRow = ticTacToe.table().length - 1;
		char tableCell = ticTacToe.table()[lastRow][0];
		for (int i = 0; i < ticTacToe.table().length; i++) {
			if (ticTacToe.table()[lastRow - i][i] != tableCell) {
				return false;
			}
			tableCell = ticTacToe.table()[lastRow - i][i];
		}
		return tableCell != WHITESPACE;
	}
}
