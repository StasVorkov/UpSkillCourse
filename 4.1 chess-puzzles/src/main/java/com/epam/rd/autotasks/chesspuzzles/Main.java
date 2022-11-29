package com.epam.rd.autotasks.chesspuzzles;
import com.epam.rd.autotasks.chesspuzzles.config.Default;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Default.class);
		System.out.println("values from context " + (context.getBeansOfType(ChessPiece.class).values()));
	}
}
