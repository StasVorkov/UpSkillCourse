package com.vvs.suffixing.main;

import com.vvs.suffixing.manager.RenameFileManager;
import com.vvs.suffixing.reader.PropertiesReader;
import com.vvs.suffixing.validator.configs.AlternativePropertyConfigurationsValidator;
import com.vvs.suffixing.validator.configs.PropertyConfigurationsValidator;
import com.vvs.suffixing.validator.path.PathPropertiesValidator;

public class Main {

    public static void main(String[] args) {

        RenameFileManager renameFileManager;
        renameFileManager = new RenameFileManager(new PropertiesReader(new PathPropertiesValidator()),
                new PropertyConfigurationsValidator());
        renameFileManager.renameFilesWithConfiguration(args[0]);

        renameFileManager.setPropertyConfigurationsValidator(new AlternativePropertyConfigurationsValidator());
        renameFileManager.renameFilesWithConfiguration(args[1]);
    }
}
