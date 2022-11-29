package com.efimchick.ifmo.io.filetree.initializer.temp;

import com.efimchick.ifmo.io.filetree.entity.TreeDirectory;
import com.efimchick.ifmo.io.filetree.entity.TreeFile;
import com.efimchick.ifmo.io.filetree.initializer.FileTreeInitializer;
import com.efimchick.ifmo.io.filetree.validator.PathValidator;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Visitor implements FileVisitor<Path> {
	
	private static final String INVALID_FILE_PATH_MESSAGE = "Invalid file path. File size hasn't calculated";
	private static final String INVALID_DIRECTORY_PATH_MESSAGE = "Invalid directory path. Directory size hasn't calculated";
	private static final Logger logger = Logger.getLogger(FileTreeInitializer.class.getName());
	private final List<TreeDirectory> visitDirList;
	private final PathValidator pathValidator;
	
	
	public Visitor(List<TreeDirectory> visitDirList) {
		this.visitDirList = visitDirList;
		this.pathValidator = new PathValidator();
	}
	
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		
		TreeDirectory directory = new TreeDirectory(dir, dir.getFileName().toString(), calculateDirectorySize(dir));
		this.visitDirList.add(directory);
		
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
		TreeFile treeFile = new TreeFile(file, file.getFileName().toString(), getFileSize(file));
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) {
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
		return FileVisitResult.CONTINUE;
	}
	
	private long getFileSize(Path path) {
		
		long size;
		
		try {
			size = Files.size(path);
		} catch (IOException e) {
			logger.severe(INVALID_FILE_PATH_MESSAGE);
			throw new RuntimeException(INVALID_FILE_PATH_MESSAGE, e);
		}
		
		return size;
	}
	
	private long calculateDirectorySize(Path path) throws IOException {
		
		long directorySize;
		
		if (pathValidator.isValid(path)) {
			
			try (Stream<Path> walk = Files.walk(path)) {
				directorySize = walk
						.filter(Files::isRegularFile)
						.mapToLong(this::getFileSize)
						.sum();
			}
			
		} else {
			logger.severe(INVALID_DIRECTORY_PATH_MESSAGE);
			throw new RuntimeException(INVALID_DIRECTORY_PATH_MESSAGE);
		}
		return directorySize;
	}
}
