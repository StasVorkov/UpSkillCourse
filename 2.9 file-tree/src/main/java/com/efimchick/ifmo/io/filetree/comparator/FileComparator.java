package com.efimchick.ifmo.io.filetree.comparator;

import com.efimchick.ifmo.io.filetree.entity.TreeFile;

import java.util.Comparator;

public class FileComparator implements Comparator<TreeFile> {
	
	@Override
	public int compare(TreeFile o1, TreeFile o2) {
		return o1.getName().compareToIgnoreCase(o2.getName());
	}
}
