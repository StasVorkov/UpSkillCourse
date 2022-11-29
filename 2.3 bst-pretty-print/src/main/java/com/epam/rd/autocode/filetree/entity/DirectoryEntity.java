package com.epam.rd.autocode.filetree.entity;

import com.efimchick.ifmo.io.filetree.printer.AbleToPrintDirectory;
import com.efimchick.ifmo.io.filetree.printer.PrinterDirectoryImpl;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class DirectoryEntity {

    private final Path directoryPath;
    private final String name;
    private final long size;
    private final List<FileEntity> filesList = new LinkedList<>();
    private final List<DirectoryEntity> subDirectoriesList = new LinkedList<>();
    private final AbleToPrintDirectory printer;
    private subDir subDirectory;


    public DirectoryEntity(Path path) {
        this.directoryPath = path;
        this.name = path.getFileName().toString();               //I have some doubts:
        fillSubDirectoryAndFileLists(path);                     //is it good realization of constructor?
        this.size = calculateDirectorySize(path);
        this.printer = new PrinterDirectoryImpl();
    }

    private long calculateDirectorySize(Path path) {
        long sizeDirectory = 0;
        try (Stream<Path> walk = Files.walk(path)) {
            sizeDirectory = walk
                    .filter(Files::isRegularFile)
                    .mapToLong(path1 -> {
                        try {
                            return Files.size(path1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return 0L;
                    })
                    .sum();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sizeDirectory;
    }

    private void fillSubDirectoryAndFileLists(Path path) {
        try (DirectoryStream<Path> files = Files.newDirectoryStream(path)) {
            for (Path p : files) {
                if (Files.isRegularFile(p)) {
                    this.filesList.add(new FileEntity(p));
                } else if (Files.isDirectory(p)) {
                    this.subDirectoriesList.add(new DirectoryEntity(p));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getName() {
        return this.name;
    }

    public long getSize() {
        return this.size;
    }

    public List<FileEntity> getFilesList() {
        return this.filesList;
    }

    public subDir getSubDirectory() {
        return this.subDirectory;
    }

    public List<DirectoryEntity> getSubDirectoriesList() {
        return this.subDirectoriesList;
    }

    @Override
    public String toString() {
        return printer.print(this);
    }

    public Path getDirectoryPath() {
        return directoryPath;
    }

    enum subDir {
        NONE,
        ONE,
        MORE_THAN_ONE
    }

}
