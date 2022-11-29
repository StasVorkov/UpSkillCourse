package com.efimchick.ifmo.io.filetree.initializer;

import com.efimchick.ifmo.io.filetree.entity.TreeFile;

import java.io.IOException;
import java.util.List;

public interface AbleToInitializeFileTree<T, E> {
	
	List<E> initializeTree(T t);
	
	TreeFile initializeSingleFile(T t);
}