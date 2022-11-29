package com.epam.rd.autotasks.chesspuzzles.service;

import com.epam.rd.autotasks.chesspuzzles.Cell;

import java.util.LinkedHashMap;
import java.util.Map;


public class PieceParser {
	
	CellParser cellParser;
	
	public PieceParser(CellParser cellParser) {
		this.cellParser = cellParser;
	}
	
	public Map<Cell, Character> getPiecesMap(String table) {
		String tableWithoutBreaks = table.replace("\n", "");
		Map<Cell, Character> pieces = new LinkedHashMap<>();
		char[] chars = tableWithoutBreaks.toCharArray();
		
		for (int i = 0; i < chars.length; i++) {
			{
				char c = chars[i];
				if (c != '.') {
					pieces.put(Cell.cell(cellParser.getLetter(i), cellParser.getNumber(i)), c);
				}
			}
		}
		return pieces;
	}
	
	
}
