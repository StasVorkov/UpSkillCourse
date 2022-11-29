package com.vvs.suffixing.reader;

import com.vvs.suffixing.validator.path.ValidatablePathConfigurations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.vvs.suffixing.manager.RenameFileManager.*;

public class PropertiesReader implements ReadableConfigurations{

    private ValidatablePathConfigurations pathConfigurationValidator;

    public PropertiesReader(ValidatablePathConfigurations pathConfigurationValidator) {
        this.pathConfigurationValidator = pathConfigurationValidator;
    }

    public ValidatablePathConfigurations getPathConfigurationValidator() {
        return pathConfigurationValidator;
    }

    public void setPathConfigurationValidator(ValidatablePathConfigurations pathConfigurationValidator) {
        this.pathConfigurationValidator = pathConfigurationValidator;
    }

    @Override
    public Map<String, String> readConfigs(String pathConfigs) throws IOException {

        Properties propertiesApp = new Properties();
        Map <String, String> mapProperties = new HashMap<>();


        if (pathConfigurationValidator.isPathValid(pathConfigs)) {
            try (FileInputStream in = new FileInputStream (pathConfigs)) {
                propertiesApp.load(in);
                mapProperties.put(MODE, propertiesApp.getProperty(MODE));
                mapProperties.put(SUFFIX, propertiesApp.getProperty(SUFFIX));
                mapProperties.put(FILES, propertiesApp.getProperty(FILES));
            }
        } else throw new FileNotFoundException();

        return mapProperties;
    }
}
