package com.efimchick.ifmo.io.filetree.validator;

import java.nio.file.Files;
import java.nio.file.Path;

public class PathValidator implements ValidatablePath<Path> {
	
	@Override
	public boolean isValid(Path path) {
		return (path != null) && (!Files.notExists(path));
	}
}
