package com.vvs.suffixing.validator.configs;

import com.vvs.suffixing.manager.RenameFileManager;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.vvs.suffixing.manager.RenameFileManager.*;
import static java.util.Objects.isNull;

public class PropertyConfigurationsValidator implements ValidatableConfigurations {

    private static final Logger logger = Logger.getLogger(RenameFileManager.class.getName());

    @Override
    public boolean isConfigurationsValid(Map<String, String> mapProperties) {

        if ((isModeRecognizable(mapProperties) &&
                isSuffixRecognizable(mapProperties) &&
                isFilesExist(mapProperties))) {
            return true;
        } else return false;
    }

    private boolean isModeRecognizable(Map<String, String> mapProperties) {

        if (!mapProperties.get(MODE).equalsIgnoreCase(MODEMOVE) &&
                !mapProperties.get(MODE).equalsIgnoreCase(MODEMOVE)) {
            logger.log(Level.SEVERE, "Mode is not recognized: " + mapProperties.get(MODE));
            return false;
        }
        return true;
    }

    private boolean isSuffixRecognizable(Map<String, String> mapProperties) {

        if (isNull(mapProperties.get(SUFFIX)) || (mapProperties.get(SUFFIX).isEmpty())) {
            logger.log(Level.SEVERE, "No suffix is configured");
            return false;
        }
        return true;
    }

    private boolean isFilesExist(Map<String, String> mapProperties) {

        if (isNull(mapProperties.get(FILES)) || (mapProperties.get(FILES).isEmpty())) {
            logger.log(Level.WARNING, "No files are configured to be copied/moved");
            return false;
        }
        return true;
    }
}

