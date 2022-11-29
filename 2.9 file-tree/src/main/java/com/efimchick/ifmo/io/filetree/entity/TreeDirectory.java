package com.efimchick.ifmo.io.filetree.entity;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TreeDirectory {
	
	private static final String WHITESPACE = " ";
	private static final String BYTES = " bytes";
	private static final String NEW_LINE = "\n";
	private final Path path;
	private final String name;
	private final long size;
	private final List<TreeFile> nestedFileList = new ArrayList<>();
	private final List<TreeDirectory> subDirectoryList = new ArrayList<>();
	private boolean isDirectoryLastInSubdirectoryList;
	
	public TreeDirectory(Path path, String name, long size) {
		
		this.path = path;
		this.name = name;
		this.size = size;
		this.isDirectoryLastInSubdirectoryList = false;
	}
	
	public Path getPath() {
		return path;
	}
	
	public String getName() {
		return this.name;
	}
	
	public long getSize() {
		return this.size;
	}
	
	public List<TreeFile> getNestedFileList() {
		return this.nestedFileList;
	}
	
	public List<TreeDirectory> getSubDirectoryList() {
		return this.subDirectoryList;
	}
	
	public boolean isDirectoryLastInSubdirectoryList() {
		return isDirectoryLastInSubdirectoryList;
	}
	
	public void setDirectoryLastInSubdirectoryList(boolean lastOrNot) {
		isDirectoryLastInSubdirectoryList = lastOrNot;
	}
	
	@Override
	public String toString() {
		return this.getName() + WHITESPACE + this.getSize() + BYTES + NEW_LINE;
	}
}
