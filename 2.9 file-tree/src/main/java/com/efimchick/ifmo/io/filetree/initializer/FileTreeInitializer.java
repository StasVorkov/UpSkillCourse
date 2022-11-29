package com.efimchick.ifmo.io.filetree.initializer;

import com.efimchick.ifmo.io.filetree.entity.TreeDirectory;
import com.efimchick.ifmo.io.filetree.entity.TreeFile;
import com.efimchick.ifmo.io.filetree.validator.PathValidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class FileTreeInitializer implements AbleToInitializeFileTree<Path, TreeDirectory> {
	
	private static final String INITIALIZE_ERROR = "Initialized stage error. Invalid path: ";
	private static final String INVALID_DIRECTORY_PATH_MESSAGE = "Invalid directory path. Directory size hasn't calculated. Path: ";
	private static final String INVALID_FILE_PATH_MESSAGE = "Invalid file path. File size hasn't calculated. Path: ";
	private static final Logger logger = Logger.getLogger(FileTreeInitializer.class.getName());
	private final List<TreeDirectory> directoryList = new ArrayList<>();
	private PathValidator pathValidator;
	private TreeDirectory root;
	
	public FileTreeInitializer() {
		this.pathValidator = new PathValidator();
	}
	
	@Override
	public List<TreeDirectory> initializeTree(Path path) {
		
		initializeRootDirectory(path);
		initializeDirectoryWithNestedFiles(root);
		markLastDirectoryInSubdirectoriesList(directoryList);
		
		return directoryList;
	}
	
	private void initializeRootDirectory(Path path) {
		
		root = new TreeDirectory(path, path.getFileName().toString(), calculateDirectorySize(path));
	}
	
	private void initializeDirectoryWithNestedFiles(TreeDirectory directory) {
		
		directoryList.add(directory);
		try (DirectoryStream<Path> directories = Files.newDirectoryStream(directory.getPath())) {
			for (Path path : directories) {
				if (Files.isRegularFile(path)) {
					directory.getNestedFileList().add(initializeSingleFile(path));
				} else if (Files.isDirectory(path)) {
					TreeDirectory tempDirectory = initializeSingleDirectory(path);
					directory.getSubDirectoryList().add(tempDirectory);
					initializeDirectoryWithNestedFiles(tempDirectory);
				}
			}
		} catch (IOException e) {
			logger.severe(INITIALIZE_ERROR + directory.getPath().toString());
			System.exit(-1);
		}
	}
	
	private TreeDirectory initializeSingleDirectory(Path path) {
		return new TreeDirectory(path, path.getFileName().toString(), calculateDirectorySize(path));
	}
	
	public TreeFile initializeSingleFile(Path path) {
		return new TreeFile(path, path.getFileName().toString(), getFileSize(path));
	}
	
	private long getFileSize(Path path) {
		
		long size = 0;
		
		if (pathValidator.isValid(path)) {
			File file = new File(path.toString());
			size = file.length();
		} else {
			logger.severe(INVALID_FILE_PATH_MESSAGE + path.toString());
			System.exit(-1);
		}
		return size;
	}
	
	private long calculateDirectorySize(Path path) {
		
		long directorySize = 0;
		try (Stream<Path> walk = Files.walk(path)) {
			directorySize = walk
					.filter(Files::isRegularFile)
					.mapToLong(this::getFileSize)
					.sum();
		} catch (IOException e) {
			logger.severe(INVALID_DIRECTORY_PATH_MESSAGE + path.toString());
			System.exit(-1);
		}
		return directorySize;
	}
	
	private void markLastDirectoryInSubdirectoriesList(List<TreeDirectory> directoryList) {
		
		for (TreeDirectory directory : directoryList) {
			if (hasDirectorySubdirectories(directory) && !hasDirectoryNestedFiles(directory)) {
				
				List<TreeDirectory> subDirectoryList = directory.getSubDirectoryList();
				for (int i = 0; i < subDirectoryList.size(); i++) {
					subDirectoryList.get(subDirectoryList.size() - 1)
							.setDirectoryLastInSubdirectoryList(true);
				}
			}
		}
	}
	
	private boolean hasDirectoryNestedFiles(TreeDirectory dir) {
		return !dir.getNestedFileList().isEmpty();
	}
	
	private boolean hasDirectorySubdirectories(TreeDirectory directory) {
		return !directory.getSubDirectoryList().isEmpty();
	}
	
	public void setPathValidator(PathValidator pathValidator) {
		this.pathValidator = pathValidator;
	}
	
	public PathValidator getPathValidator() {
		return pathValidator;
	}
}
