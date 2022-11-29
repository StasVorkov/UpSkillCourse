package com.efimchick.ifmo.io.filetree.printer;

import com.efimchick.ifmo.io.filetree.entity.TreeDirectory;
import com.efimchick.ifmo.io.filetree.entity.TreeFile;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class PseudoGraphicTreePrinter implements PrintableTree<TreeDirectory, String> {
	
	private static final String INTERMEDIATE_SYMBOL = "├─ ";
	private static final String LAST_SYMBOL = "└─ ";
	private static final String WHITESPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final String EMPTY_STRING = "";
	private static final int MIN_OFFSET = 3;
	private static final char LINE = '│';
	private static final char INTERMEDIATE_CHAR = '├';
	private static final char LAST_CHAR = '└';
	private static final char WHITESPACE_CHAR = ' ';
	private TreeDirectory root;
	
	@Override
	public String printTree(TreeDirectory rootDirectory) {
		
		initializeRoot(rootDirectory);
		String treeWithoutLines = printDirectoryWithoutLines(rootDirectory);
		List<char[]> allCharList = convertFromStringToCharArray(treeWithoutLines);
		Set<Integer> linePositionSet = addLinePositionsToSet(allCharList);
		String fileTreeResult = insertLines(linePositionSet, allCharList);
		
		return fileTreeResult;
	}
	
	private void initializeRoot(TreeDirectory rootDirectory) {
		this.root = rootDirectory;
	}
	
	
	private String printDirectoryWithoutLines(TreeDirectory directory) {
		
		StringBuilder sbPrint = new StringBuilder();
		
		sbPrint.append(WHITESPACE.repeat(determineNumberOfWhitespaces(directory.getPath())))
				.append(addPseudoGraphicSymbolToDirectoryName(directory))
				.append(directory);
		
		if (hasDirectorySubdirectories(directory)) {
			
			for (TreeDirectory subDirectory : directory.getSubDirectoryList()) {
				sbPrint.append(printDirectoryWithoutLines(subDirectory));
			}
			sbPrint.append(printNestedFiles(directory));
			
		} else {
			sbPrint.append(printNestedFiles(directory));
		}
		return sbPrint.toString();
	}
	
	private String addPseudoGraphicSymbolToDirectoryName(TreeDirectory directory) {
		
		String symbol = EMPTY_STRING;
		if (!isDirectoryRoot(directory.getPath())) {
			if (directory.isDirectoryLastInSubdirectoryList()) {
				symbol = LAST_SYMBOL;
			} else {
				symbol = INTERMEDIATE_SYMBOL;
			}
		}
		return symbol;
	}
	
	private boolean isDirectoryRoot(Path path) {
		return path.equals(root.getPath());
	}
	
	private boolean hasDirectorySubdirectories(TreeDirectory directory) {
		return !directory.getSubDirectoryList().isEmpty();
	}
	
	private String printNestedFiles(TreeDirectory directory) {
		
		StringBuilder sbFiles = new StringBuilder();
		
		List<TreeFile> filesList = directory.getNestedFileList();
		for (int index = 0; index < filesList.size(); index++) {
			TreeFile file = filesList.get(index);
			
			if (isLastIndex(filesList, index)) {
				sbFiles.append(addPseudoGraphicSymbolsToFileName(file, LAST_SYMBOL));
			} else {
				sbFiles.append(addPseudoGraphicSymbolsToFileName(file, INTERMEDIATE_SYMBOL));
			}
		}
		return sbFiles.toString();
	}
	
	private boolean isLastIndex(List<TreeFile> filesList, int index) {
		return index == filesList.size() - 1;
	}
	
	private String addPseudoGraphicSymbolsToFileName(TreeFile file, String symbol) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(WHITESPACE.repeat(determineNumberOfWhitespaces(file.getPath())))
				.append(symbol)
				.append(file)
				.append(NEW_LINE);
		
		return sb.toString();
	}
	
	private List<char[]> convertFromStringToCharArray(String treeWithoutLines) {
		
		String[] arrayDirectories = treeWithoutLines.split(NEW_LINE);
		return Arrays.stream(arrayDirectories)
				.map(String::toCharArray)
				.collect(Collectors.toList());
	}
	
	private Set<Integer> addLinePositionsToSet(List<char[]> charList) {
		
		Set<Integer> linePositionSet = new HashSet<>();
		for (char[] array : charList) {
			for (int i = 0; i < array.length; i++) {
				char c = array[i];
				if (c == INTERMEDIATE_CHAR || c == LAST_CHAR) {
					linePositionSet.add(i);
				}
			}
		}
		return linePositionSet;
	}
	
	private String insertLines(Set<Integer> linePositionSet, List<char[]> charList) {
		
		for (char[] array : charList) {
			for (int i = 0; i < array.length; i++) {
				if (isPositionNeedsToBeInsertedLine(linePositionSet, array, i)) {
					array[i] = LINE;
				}
				refreshSetWithLinePositions(linePositionSet, array, i);
			}
		}
		return convertFromCharsToString(charList);
	}
	
	private void refreshSetWithLinePositions(Set<Integer> linePositionSet, char[] array, int i) {
		
		if (linePositionSet.contains(i) && array[i] == LAST_CHAR) {
			linePositionSet.remove(i);
		}
		if (array[i] == INTERMEDIATE_CHAR) {
			linePositionSet.add(i);
		}
	}
	
	private String convertFromCharsToString(List<char[]> charList) {
		
		StringBuilder sb = new StringBuilder();
		for (char[] array : charList) {
			for (char c : array) {
				sb.append(c);
			}
			sb.append(NEW_LINE);
		}
		return sb.toString();
	}
	
	private boolean isPositionNeedsToBeInsertedLine(Set<Integer> linePositionSet, char[] array, int i) {
		return linePositionSet.contains(i) && array[i] == WHITESPACE_CHAR && array[i + 1] == WHITESPACE_CHAR;
	}
	
	private int determineNumberOfWhitespaces(Path path) {
		
		if (isDirectoryRoot(path) || isDirectoryRoot(path.getParent())) {
			return 0;
		} else return MIN_OFFSET + determineNumberOfWhitespaces(path.getParent());
	}
}
