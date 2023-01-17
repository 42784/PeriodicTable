package com.me;

import com.me.tools.YamlTools;
import org.apache.log4j.Logger;
import org.simpleyaml.configuration.file.YamlFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 秃头老狗
 * @version 1.0
 */
public class PeriodicTable {
    public static final Logger logger = Logger.getLogger(PeriodicTable.class);
    public static final String softwareAddress = "C:\\Users\\Administrator\\Desktop\\PeriodicTable";//软件地址
    public static final List<Atomic> atomicList = new ArrayList<>();

    public static void main(String[] args) {
        initAtomicList();

        logger.debug(atomicList);
        logger.info("Hello World");
    }

    /**
     * 读取YAML获取原子信息 构建list
     */
    public static void initAtomicList() {
        YamlFile yamlFile = new YamlTools("C:\\Users\\Administrator\\Desktop\\PeriodicTable\\test.yaml").getYamlFile();

        List<Map<?, ?>> main = yamlFile.getMapList("main");
        for (Map<?, ?> map : main) {
            try {
                int atomicID = Integer.parseInt(map.get("atomic").toString());
                String symbol = map.get("symbol").toString();
                double weight = Double.parseDouble(map.get("weight").toString());
                double melt = Double.parseDouble(map.get("melt").toString());
                double boil = Double.parseDouble(map.get("boil").toString());
                int discover = Integer.parseInt(map.get("discover").toString());
                Atomic atomic = new Atomic(atomicID, symbol, weight, melt, boil, discover);
                atomicList.add(atomic);
            } catch (NullPointerException ignored) {
            }//读取到空的原子 则跳过

        }
    }
}
