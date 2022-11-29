package com.efimchick.ifmo.io.filetree.initializer.temp;

import com.efimchick.ifmo.io.filetree.entity.TreeDirectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class FileTreeVisitorInitializer {
	
	private final List<TreeDirectory> listDir = new ArrayList<>();
	
	public List<TreeDirectory> initializeTree(Path path) {
		try {
			Files.walkFileTree(path, new Visitor(listDir));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listDir;
	}
}
