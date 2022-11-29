package com.efimchick.ifmo.io.filetree.validator;

public interface ValidatablePath<E> {
	
	boolean isValid(E e);
}
