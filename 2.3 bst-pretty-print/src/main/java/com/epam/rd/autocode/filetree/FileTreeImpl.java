package com.epam.rd.autocode.filetree;

import com.efimchick.ifmo.io.filetree.entity.DirectoryEntity;
import com.efimchick.ifmo.io.filetree.entity.FileEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FileTreeImpl implements FileTree {

    private static final int MIN_OFFSET = 3;
    private final List<DirectoryEntity> directoriesList = new LinkedList<>();
    private static Path rootDirectoryPath;                                        //static Path & Directory is very usefully,
    private static DirectoryEntity rootDirectory;                                 // but mb it's not a good practice, cause there are
                                                                                  // variables of object, but not a class variables.
    @Override
    public Optional<String> tree(Path path) {

        if (pathIsValid(path)) {
            if (isDirectory(path)) {
                initializeRootPath(path);
                fillDirectoriesList(path);
                initializeRootDirectory(path);
                String result = printTree(this.directoriesList);
                System.out.println(result);
                return Optional.of(result);
            } else if (isFile(path)) {
                return Optional.of((new FileEntity(path)).toString());
            }
        }
        return Optional.empty();
    }

    private void initializeRootPath(Path path) {
        rootDirectoryPath = path;
    }

    private boolean isFile(Path path) {
        return Files.isRegularFile(path);
    }

    private boolean isDirectory(Path path) {
        return Files.isDirectory(path);
    }

    private boolean pathIsValid(Path path) {
        return (path!=null) && (!Files.notExists(path));
    }

    private void fillDirectoriesList(Path path) {
        try (Stream<Path> pathStream = Files.walk(path)) {
            pathStream
                    .filter(Files::isDirectory)
                    .forEach(p -> this.directoriesList.add(new DirectoryEntity(p)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeRootDirectory(Path path) {
        if (directoriesList.size() > 0) {
            rootDirectory=directoriesList.get(0);
        }
    }

    public static Path getRootDirectoryPath() {
        return rootDirectoryPath;
    }

    public static DirectoryEntity getRootDirectory() {
        return rootDirectory;
    }


    private String printTree(List<DirectoryEntity> directoriesList) {
        StringBuilder sb = new StringBuilder();
        sb.append(directoriesList.get(0));
        for (int i = 1; i <directoriesList.size() ; i++) {
            sb.append("\n")
                    .append(" ".repeat(Math.max(0, determineNumberOfWhitespaces(directoriesList.get(i).getDirectoryPath()))))
                    .append(directoriesList.get(i));
        }
        sb.append("\n")
                .append(getDirectoryFilesAttributesToString(getRootDirectory()));

        return sb.toString();
    }

    private int determineNumberOfWhitespaces(Path path) {
        if (path.toString().equals(FileTreeImpl.getRootDirectoryPath().toString())) {
            return 0;
        } else return MIN_OFFSET + determineNumberOfWhitespaces(path.getParent());
    }
    private String getDirectoryFilesAttributesToString(DirectoryEntity directory) {
        StringBuilder sb = new StringBuilder();
        for (FileEntity f : directory.getFilesList()) {
            sb.append(" ")
                    .append(" ".repeat(determineNumberOfWhitespaces(f.getFilePath().getParent()) + 3))
                    .append(f)
                    .append("\n");
        }
        return sb.toString();
    }
}
