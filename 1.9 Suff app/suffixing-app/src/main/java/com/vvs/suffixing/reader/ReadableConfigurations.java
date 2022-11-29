package com.vvs.suffixing.reader;

import java.io.IOException;
import java.util.Map;

public interface ReadableConfigurations {

    Map<String, String> readConfigs(String pathConfigs) throws IOException;
}
