package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.Board;
import com.epam.rd.autotasks.chesspuzzles.ChessPiece;
import com.epam.rd.autotasks.chesspuzzles.Piece;
import com.epam.rd.autotasks.chesspuzzles.PieceFactoryPostProcessor;
import com.epam.rd.autotasks.chesspuzzles.service.BoardReader;
import com.epam.rd.autotasks.chesspuzzles.service.CellParser;
import com.epam.rd.autotasks.chesspuzzles.service.PieceParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@ComponentScan("com.epam.rd.autotasks.chesspuzzles")
@PropertySource("classpath:app.properties")
public class Puzzle01 {
	
	@Value("${puzzle01}")
	String pathToInput;
	
	@Bean
	public BoardReader boardReader() {
		return new BoardReader("src/main/resources/boards/Puzzle01.txt");
	}
	
	@Bean
	public CellParser cellParser() {
		return new CellParser();
	}
	
	@Bean
	public Board board() {
		return new Board(getCollection());
	}
	
	@Bean
	public Collection<ChessPiece> getCollection() {
		return new ArrayList<>();
	}
	
	@Bean
	public PieceParser pieceParser(CellParser cellParser) {
		return new PieceParser(cellParser);
	}
	
	@Bean
	public Piece piece() {
		return new Piece();
	}
	
	@Bean
	public PieceFactoryPostProcessor pieceFactoryPostProcessor(PieceParser pieceParser, BoardReader boardReader) {
		return new PieceFactoryPostProcessor(pieceParser,boardReader);
	}
}
