package org.pw.rafalj.crm.sql.config;

import org.pw.rafalj.crm.sql.enums.Table;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Created by rjozwiak on 2016-04-25.
 */
public class ConfigContainer {
    Path directory;
    Map<Table, GeneratorConfig> config;

    public Path getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = Paths.get(directory);
    }

    public Map<Table, GeneratorConfig> getConfig() {
        return config;
    }

    public void setConfig(Map<Table, GeneratorConfig> config) {
        this.config = config;
    }
}
