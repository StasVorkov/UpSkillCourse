package com.efimchick.ifmo.io.filetree.tree;

import java.nio.file.Path;
import java.util.Optional;

public interface FileTree {
	Optional<String> printFileTree(Path path);
}
