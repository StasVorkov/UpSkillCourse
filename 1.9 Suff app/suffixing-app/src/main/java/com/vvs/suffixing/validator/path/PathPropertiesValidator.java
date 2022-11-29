package com.vvs.suffixing.validator.path;

import java.io.File;

public class PathPropertiesValidator implements ValidatablePathConfigurations {

    @Override
    public boolean isPathValid(String path) {
        File f = new File(path);
        return f.exists() && !f.isDirectory();
    }
}
