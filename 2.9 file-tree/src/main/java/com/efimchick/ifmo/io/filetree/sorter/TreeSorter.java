package com.efimchick.ifmo.io.filetree.sorter;

import com.efimchick.ifmo.io.filetree.comparator.DirectoryComparator;
import com.efimchick.ifmo.io.filetree.comparator.FileComparator;
import com.efimchick.ifmo.io.filetree.entity.TreeDirectory;

import java.util.Collection;
import java.util.List;

public class TreeSorter implements TreeSortable<TreeDirectory> {
	
	@Override
	public void sortTree(List<TreeDirectory> sourceList) {
		
		sortDirectories(sourceList);
		sortFiles(sourceList);
	}
	
	private void sortDirectories(List<TreeDirectory> sourceList) {
		
		sourceList.stream()
				.map(TreeDirectory::getSubDirectoryList)
				.forEach(directories -> directories.sort(new DirectoryComparator()));
	}
	
	private void sortFiles(List<TreeDirectory> sourceList) {
		
		sourceList.stream()
				.map(TreeDirectory::getSubDirectoryList)
				.flatMap(Collection::stream)
				.map(TreeDirectory::getNestedFileList)
				.forEach(files -> files.sort(new FileComparator()));
	}
}
