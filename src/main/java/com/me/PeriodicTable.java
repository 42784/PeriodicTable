package com.me;

import com.me.tools.YamlTools;
import org.simpleyaml.configuration.file.YamlFile;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 秃头老狗
 * @version 1.0
 */
public class PeriodicTable {
    public static void main(String[] args) {
        YamlTools.initYAML();
        YamlFile yamlFile = YamlTools.getYamlFile();

        List<Map<?, ?>> mapList = (yamlFile.getMapList("main"));

        for (Map<?, ?> map : mapList) {
            Set<?> keySet = map.keySet();
            for (Object key : keySet) {
                Object value = map.get(key);
                System.out.printf("%s: %s\n",key,value);

            }
            System.out.println("==============================================================================");
        }


        System.out.println("Hello World");
    }
}
