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
            YamlFile yamlFile = new YamlTools("C:\\Users\\Administrator\\Desktop\\PeriodicTable\\本地原子数据.yaml").getYamlFile();
            List<Map<?, ?>> main = yamlFile.getMapList("main");
            for (Map<?, ?> map : main) {
                try {
                    int atomicID = (int) map.get("number");
                    String symbol = map.get("symbol").toString();
                    String name = map.get("name").toString();
                    String weight = map.get("atomic_mass") == null ? "N/A" : map.get("atomic_mass").toString();
                    String melt = map.get("melt") == null ? "N/A" : map.get("melt").toString();
                    String boil = map.get("boil") == null ? "N/A" : map.get("boil").toString();
                    String phase = map.get("phase") == null ? "N/A" : map.get("phase").toString();
                    Atomic atomic = new Atomic(name, atomicID, symbol, weight, melt, boil, phase);
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
    }
}
