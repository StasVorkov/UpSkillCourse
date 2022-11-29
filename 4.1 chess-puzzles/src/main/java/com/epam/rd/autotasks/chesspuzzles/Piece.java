package com.epam.rd.autotasks.chesspuzzles;

public class Piece implements ChessPiece {
	
	private Cell cell;
	private Character symbol;
	
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	public void setCell(Cell cell) {
		this.cell = cell;
	}
	
	@Override
	public Cell getCell() {
		return cell;
	}
	
	@Override
	public char toChar() {
		return symbol;
	}
	
	@Override
	public String toString() {
		return "Piece{" +
				"cell=" + cell +
				", symbol=" + symbol +
				'}';
	}
}
