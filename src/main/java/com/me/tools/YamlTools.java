package com.me.tools;


import org.simpleyaml.configuration.ConfigurationSection;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.IOException;

/**
 * @author 秃头老狗
 * @version 1.0
 */
public class YamlTools {
    public static YamlFile yamlFile = new YamlFile("C:\\Users\\Administrator\\Desktop\\参赛相关\\本地数据.yml");

    public static ConfigurationSection mainConfigurationSection = yamlFile.createSection("main");
    public static void initYAML(){

        try {
            yamlFile.createNewFile();
            yamlFile.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public static YamlFile getYamlFile() {
        return yamlFile;
    }

    public static void setYamlFile(YamlFile yamlFile) {
        YamlTools.yamlFile = yamlFile;
    }
}
