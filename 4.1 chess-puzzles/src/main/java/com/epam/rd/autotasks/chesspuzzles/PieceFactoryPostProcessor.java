package com.epam.rd.autotasks.chesspuzzles;

import com.epam.rd.autotasks.chesspuzzles.service.BoardReader;
import com.epam.rd.autotasks.chesspuzzles.service.PieceParser;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import java.util.Map;

public class PieceFactoryPostProcessor implements BeanFactoryPostProcessor {
	
	PieceParser pieceParser;
	BoardReader boardReader;
	
	public PieceFactoryPostProcessor(PieceParser pieceParser, BoardReader boardReader) {
		this.pieceParser = pieceParser;
		this.boardReader = boardReader;
	}
	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		
		Map<Cell, Character> pieces = pieceParser.getPiecesMap(boardReader.getString());
		
		for (Map.Entry<Cell,Character> entry : pieces.entrySet()) {
			if (entry.getKey() != null) {
				GenericBeanDefinition bd = new GenericBeanDefinition();
				bd.setBeanClass(Piece.class);
				bd.getPropertyValues().add("symbol", entry.getValue());
				bd.getPropertyValues().add("cell", entry.getKey());
				((DefaultListableBeanFactory) beanFactory)
						.registerBeanDefinition("MyPiece"+entry.getKey().toString(), bd);
			}
		}
	}
}
	

