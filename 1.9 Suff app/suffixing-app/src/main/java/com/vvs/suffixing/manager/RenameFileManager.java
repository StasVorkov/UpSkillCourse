package com.vvs.suffixing.manager;

import com.vvs.suffixing.reader.ReadableConfigurations;
import com.vvs.suffixing.validator.configs.ValidatableConfigurations;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RenameFileManager {

    private static final Logger logger = Logger.getLogger(RenameFileManager.class.getName());

    public static final String MODE = "mode";
    public static final String SUFFIX = "suffix";
    public static final String FILES = "files";

    public static final String MODECOPY = "copy";
    public static final String MODEMOVE = "move";

    private Map<String, String> mapProperties;
    private String mode;
    private String suffix;
    private String files;
    private ReadableConfigurations propertiesReader;
    private ValidatableConfigurations propertyConfigurationsValidator;

    public RenameFileManager(ReadableConfigurations propertiesReader, ValidatableConfigurations propertyConfigurationsValidator) {
        this.propertiesReader = propertiesReader;
        this.propertyConfigurationsValidator = propertyConfigurationsValidator;
    }

    public ReadableConfigurations getPropertiesReader() {
        return propertiesReader;
    }

    public void setPropertiesReader(ReadableConfigurations propertiesReader) {
        this.propertiesReader = propertiesReader;
    }

    public ValidatableConfigurations getPropertyConfigurationsValidator() {
        return propertyConfigurationsValidator;
    }

    public void setPropertyConfigurationsValidator(ValidatableConfigurations propertyConfigurationsValidator) {
        this.propertyConfigurationsValidator = propertyConfigurationsValidator;
    }

    public void renameFilesWithConfiguration(String pathProperties) {
    
        readProperties(pathProperties);
    
        if (!propertyConfigurationsValidator.isConfigurationsValid(mapProperties)) {
            return;
        }

        String[] arrayOfPaths = files.split(":");

        for (String sourcePath : arrayOfPaths) {
            File filePath = new File(sourcePath);

            if (!filePath.exists()) {
                logger.log(Level.SEVERE, "No such file: " + sourcePath);
                continue;
            }

            String destinationPath = null;
            try {
                String[] subPath = sourcePath.split("\\.");
                destinationPath = subPath[0] + suffix + "." + subPath[1];
            } catch (IndexOutOfBoundsException e) {
                logger.log(Level.SEVERE, "IndexOutOfBoundsException. Split string incorrect");
            }

            try {
                if (mode.equalsIgnoreCase(MODEMOVE)) {
                    Files.move(Path.of(sourcePath), Path.of(Objects.requireNonNull(destinationPath)));
                    logger.info(sourcePath + " => " + destinationPath);
                } else if (mode.equalsIgnoreCase(MODECOPY)) {
                    Files.copy(Path.of(sourcePath), Path.of(Objects.requireNonNull(destinationPath)));
                    logger.info(sourcePath + " -> " + destinationPath);
                }
            } catch (IOException e) {
                logger.log(Level.SEVERE, "IO Exception. File didn't move/copy");
            }
        }
    }
    
    private void readProperties(String pathProperties) {
        try {
            mapProperties = propertiesReader.readConfigs(pathProperties);
            mode = mapProperties.get(MODE);
            suffix = mapProperties.get(SUFFIX);
            files = mapProperties.get(FILES);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO Exception. File properties didn't load");
        }
    }
}
