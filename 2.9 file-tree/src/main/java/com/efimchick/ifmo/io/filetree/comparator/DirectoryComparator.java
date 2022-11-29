package com.efimchick.ifmo.io.filetree.comparator;

import com.efimchick.ifmo.io.filetree.entity.TreeDirectory;

import java.util.Comparator;

public class DirectoryComparator implements Comparator<TreeDirectory> {
	
	@Override
	public int compare(TreeDirectory o1, TreeDirectory o2) {
		return o1.getName().compareToIgnoreCase(o2.getName());
	}
}
