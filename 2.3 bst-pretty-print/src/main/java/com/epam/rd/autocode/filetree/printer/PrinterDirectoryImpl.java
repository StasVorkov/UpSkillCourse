package com.epam.rd.autocode.filetree.printer;

import com.efimchick.ifmo.io.filetree.FileTreeImpl;
import com.efimchick.ifmo.io.filetree.entity.DirectoryEntity;
import com.efimchick.ifmo.io.filetree.entity.FileEntity;

import java.nio.file.Path;

public class PrinterDirectoryImpl implements AbleToPrintDirectory {

    private static final int MIN_OFFSET = 3;

    @Override
    public String print(DirectoryEntity directory) {

        StringBuilder sbGeneral = new StringBuilder();

        if (directory.getSubDirectoriesList().size() > 0) {
            sbGeneral.append(directory.getName())
                    .append(" ")
                    .append(directory.getSize())
                    .append(" bytes");
        } else {
            sbGeneral.append(getDirectoryAttributesToString(directory));
            sbGeneral.append(getDirectoryFilesAttributesToString(directory));
        }
        return sbGeneral.toString();
    }


    private String getDirectoryAttributesToString(DirectoryEntity directory) {
        StringBuilder sb = new StringBuilder();
        sb.append(directory.getName())
                .append(" ")
                .append(directory.getSize())
                .append(" bytes")
                .append("\n");
        return sb.toString();
    }

    private String getDirectoryFilesAttributesToString(DirectoryEntity directory) {
        StringBuilder sb = new StringBuilder();
        for (FileEntity f : directory.getFilesList()) {
            sb.append(" ".repeat(determineNumberOfWhitespaces(f.getFilePath().getParent()) + 3))
                    .append(f)
                    .append("\n");
        }
        return deleteLastChar(sb);
    }

    private int determineNumberOfWhitespaces(Path path) {
        if (path.toString().equals(FileTreeImpl.getRootDirectoryPath().toString())) {
            return 0;
        } else return MIN_OFFSET + determineNumberOfWhitespaces(path.getParent());
    }

    //    private boolean hasRootDirectoryAnyFiles() {                                    //Method's name started from has
//        return !FileTreeImpl.getRootDirectory().getFilesList().isEmpty();
//    }
//
//    private String getRootFilesAttributesInTheEnd() {
//        StringBuilder sb = new StringBuilder();
//        for (FileEntity f : FileTreeImpl.getRootDirectory().getFilesList()) {
//            sb.append(" ".repeat(determineNumberOfWhitespaces(f.getFilePath().getParent()) + 3))
//                    .append(f)
//                    .append("\n");
//        }
//        return sb.toString();
//    }


    private String getSubDirectoryFilesAttributes(DirectoryEntity directory) {
        StringBuilder sb = new StringBuilder();
        for (FileEntity f : directory.getFilesList()) {
            sb.append(" ".repeat(determineNumberOfWhitespaces(f.getFilePath().getParent())))
                    .append(f)
                    .append("\n");
        }
        return sb.toString();
    }

    private String deleteLastChar(StringBuilder sb) {
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
