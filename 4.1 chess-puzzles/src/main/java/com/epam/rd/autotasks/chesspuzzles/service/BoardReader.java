package com.epam.rd.autotasks.chesspuzzles.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class BoardReader {
	
	String tableViewPath;
	
	public BoardReader(String tableViewPath) {
		this.tableViewPath = tableViewPath;
	}
	
	public String getString() {
		
		if (tableViewPath == null || tableViewPath.isEmpty()) {
			throw new IllegalArgumentException("Path to file is invalid");
		}
		
		String startTable;
		try (Stream<String> stream = Files.lines(Paths.get(tableViewPath))) {
			startTable = stream.collect(Collectors.joining("\n"));
		} catch (IOException e) {
			throw new IllegalArgumentException("Path to file is invalid", e);
		}
		return startTable;
	}
}
