package com.me;

import com.me.tools.PeriodicTableSystem;
import com.me.tools.YamlTools;
import org.apache.log4j.Logger;
import org.simpleyaml.configuration.ConfigurationSection;
import org.simpleyaml.configuration.file.YamlFile;

import java.util.*;

/**
 * @author 秃头老狗
 * @version 1.0
 */
public class PeriodicTable {
    public static final Logger logger = Logger.getLogger(PeriodicTable.class);
    public static final String softwareAddress = "C:\\Users\\Administrator\\Desktop\\PeriodicTable";//软件地址
    public static final HashMap<String, Atomic> atomicHashMap_symbol = new HashMap<>();
    public static final HashMap<Integer, Atomic> atomicHashMap_atomicID = new HashMap<>();
    public static final List<Atomic> atomicList = new ArrayList<>();

    public static void main(String[] args) {
        initAtomicMap();
        PeriodicTableSystem.joinSystem();

        logger.info("Hello World");
    }

    /**
     * 读取YAML获取原子信息 构建键值对映射
     */
    public static void initAtomicMap() {
        {//构建Atomic
            YamlFile yamlFile = new YamlTools("C:\\Users\\Administrator\\Desktop\\PeriodicTable\\原子数据.yml").getYamlFile();
            List<Map<?, ?>> main = yamlFile.getMapList("main");
            for (Map<?, ?> map : main) {
                try {
                    int atomicID = Integer.parseInt(map.get("atomic").toString());
                    String symbol = map.get("symbol").toString();
                    String weight = map.get("weight") == null ? "N/A" : map.get("weight").toString();
                    String melt = map.get("melt") == null ? "N/A" : map.get("melt").toString();
                    String boil = map.get("boil") == null ? "N/A" : map.get("boil").toString();
                    int discover = Integer.parseInt(map.get("discover").toString());
                    Atomic atomic = new Atomic(null, atomicID, symbol, weight, melt, boil, discover);
                    atomicHashMap_symbol.put(symbol, atomic);
                    atomicHashMap_atomicID.put(atomicID, atomic);
                    atomicList.add(atomic);
                    logger.debug("注册原子: " + atomic);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }//读取到空的原子 则跳过
            }
        }
        logger.debug(atomicHashMap_atomicID);
        {//补充添加Atomic的name
            YamlFile yamlFile = new YamlTools("C:\\Users\\Administrator\\Desktop\\PeriodicTable\\元素英文全名.yml").getYamlFile();
            ConfigurationSection configurationSection = yamlFile.getConfigurationSection("main");
            for (int i = 1; i <= 118; i++) {
                Atomic atomic = atomicHashMap_atomicID.get(i);
                atomic.setName(configurationSection.getString("Atomic"+i));
            }

        }
    }
}
