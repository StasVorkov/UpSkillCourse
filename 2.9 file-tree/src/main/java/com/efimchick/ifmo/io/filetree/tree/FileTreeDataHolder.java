package com.efimchick.ifmo.io.filetree.tree;

import com.efimchick.ifmo.io.filetree.entity.TreeDirectory;
import com.efimchick.ifmo.io.filetree.initializer.AbleToInitializeFileTree;
import com.efimchick.ifmo.io.filetree.initializer.FileTreeInitializer;
import com.efimchick.ifmo.io.filetree.printer.PrintableTree;
import com.efimchick.ifmo.io.filetree.printer.PseudoGraphicTreePrinter;
import com.efimchick.ifmo.io.filetree.sorter.TreeSortable;
import com.efimchick.ifmo.io.filetree.sorter.TreeSorter;
import com.efimchick.ifmo.io.filetree.validator.PathValidator;
import com.efimchick.ifmo.io.filetree.validator.ValidatablePath;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class FileTreeDataHolder implements FileTree {
	
	private PrintableTree<TreeDirectory, String> printer;
	private ValidatablePath<Path> pathValidator;
	private AbleToInitializeFileTree<Path, TreeDirectory> treeInitializer;
	private TreeSortable<TreeDirectory> sorter;
	private List<TreeDirectory> directoryList;
	
	public FileTreeDataHolder() {
		
		this.printer = new PseudoGraphicTreePrinter();
		this.pathValidator = new PathValidator();
		this.treeInitializer = new FileTreeInitializer();
		this.sorter = new TreeSorter();
	}
	
	@Override
	public Optional<String> printFileTree(Path path) {
		
		if (pathValidator.isValid(path)) {
			
			if (isDirectory(path)) {
				this.directoryList = treeInitializer.initializeTree(path);
				sorter.sortTree(directoryList);
				String treeFileAsString = printer.printTree(directoryList.get(0));
				return Optional.of(treeFileAsString);
				
			} else if (isFile(path)) {
				return Optional.of(treeInitializer.initializeSingleFile(path).toString());
			}
		}
		return Optional.empty();
	}
	
	private boolean isDirectory(Path path) {
		return Files.isDirectory(path);
	}
	
	private boolean isFile(Path path) {
		return Files.isRegularFile(path);
	}
	
	public PrintableTree<TreeDirectory, String> getPrinter() {
		return printer;
	}
	
	public void setPrinter(PrintableTree<TreeDirectory, String> printer) {
		this.printer = printer;
	}
	
	public ValidatablePath<Path> getPathValidator() {
		return pathValidator;
	}
	
	public void setPathValidator(ValidatablePath<Path> pathValidator) {
		this.pathValidator = pathValidator;
	}
	
	public AbleToInitializeFileTree<Path, TreeDirectory> getTreeInitializer() {
		return treeInitializer;
	}
	
	public void setTreeInitializer(AbleToInitializeFileTree<Path, TreeDirectory> treeInitializer) {
		this.treeInitializer = treeInitializer;
	}
	
	public TreeSortable<TreeDirectory> getSorter() {
		return sorter;
	}
	
	public void setSorter(TreeSortable<TreeDirectory> sorter) {
		this.sorter = sorter;
	}
	
	public List<TreeDirectory> getDirectoryList() {
		return directoryList;
	}
	
}

