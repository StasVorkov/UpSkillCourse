package com.efimchick.ifmo.io.filetree.entity;

import java.nio.file.Path;

public class TreeFile {
	
	private static final String WHITESPACE = " ";
	private static final String BYTES = " bytes";
	private final Path path;
	private final String name;
	private final long fileSize;
	
	public TreeFile(Path path, String name, long size) {
		this.path = path;
		this.name = name;
		this.fileSize = size;
	}
	
	public Path getPath() {
		return path;
	}
	
	public String getName() {
		return name;
	}
	
	public long getSize() {
		return fileSize;
	}
	
	@Override
	public String toString() {
		return name + WHITESPACE + fileSize + BYTES;
	}
}
