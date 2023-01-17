package com.me.tools;


import com.me.PeriodicTable;
import org.apache.log4j.Logger;
import org.simpleyaml.configuration.ConfigurationSection;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.IOException;

/**
 * @author 秃头老狗
 * @version 1.0
 */
public class YamlTools {
    public YamlFile yamlFile;

    public YamlTools(String filename) {
        this.yamlFile = new YamlFile(filename);
        try {
            yamlFile.createNewFile();
            yamlFile.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public YamlFile getYamlFile() {
        return yamlFile;
    }

    public void setYamlFile(YamlFile yamlFile) {
        yamlFile = yamlFile;
    }
}
