package com.epam.rd.autocode.filetree.entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileEntity {
    private final Path filePath;
    private final String name;
    private long size;

    public FileEntity(Path path) {
        this.filePath = path;
        this.name = path.getFileName().toString();
        try {
            this.size = Files.size(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    @Override
    public String toString() {
      return name + " " + size + " bytes";
    }

    public Path getFilePath() {
        return filePath;
    }
}
