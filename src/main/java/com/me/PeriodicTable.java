package com.me;

import com.me.tools.PeriodicTableSystem;
import com.me.tools.Timings_Object;
import com.me.tools.YamlTools;
import org.apache.log4j.Logger;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.*;
import java.util.*;

/**
 * @author 秃头老狗
 * @version 1.0
 */
public class PeriodicTable {
    public static final Logger logger = Logger.getLogger(PeriodicTable.class);
    public static String softwareAddress = System.getProperty("user.dir");//软件地址
    public static final HashMap<String, Atomic> atomicHashMap_symbol = new HashMap<>();
    public static final HashMap<Integer, Atomic> atomicHashMap_atomicID = new HashMap<>();
    public static final List<Atomic> atomicList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        logger.debug("softwareAddress = " + softwareAddress);
        Timings_Object timings = new Timings_Object();
        timings.startTimings();
        new PeriodicTable().initAtomicMap();//初始化
        timings.stopTimings();
        logger.info(String.format("初始化据用时: %dms", timings.getTime()));

        PeriodicTableSystem.joinSystem();//进入系统

        logger.info("Hello World");
    }

    /**
     * 读取YAML获取原子信息 构建键值对映射
     */
    public void initAtomicMap() throws IOException {
//        构建Atomic
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("本地原子数据.yaml");
        assert resourceAsStream != null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
        File tempFile = File.createTempFile("PeriodicTableAtomicData", ".yaml");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }

        bufferedWriter.flush();
        bufferedWriter.close();
        bufferedReader.close();


        YamlFile yamlFile = new YamlTools(
                tempFile.getPath()
        ).getYamlFile();


        List<Map<?, ?>> main = yamlFile.getMapList("main");
        for (Map<?, ?> map : main) {
            try {
                Object temp;
                int atomicID = (int) map.get("atomicNumber");

                String symbol = map.get("symbol").toString();

                String name = map.get("name").toString();

                temp = map.get("atomic_mass");
                String weight = temp == null ? "N/A" : temp.toString();

                temp = map.get("melt");
                String melt = temp == null ? "N/A" : temp.toString();

                temp = map.get("boil");
                String boil = temp == null ? "N/A" : temp.toString();

                temp = map.get("phase");
                String phase = temp == null ? "N/A" : temp.toString();

                temp = map.get("electron");
                String electron = temp == null ? "N/A" : temp.toString();

                Atomic atomic = new Atomic(name, atomicID, symbol, weight, melt, boil, phase, electron);

                atomicHashMap_symbol.put(symbol, atomic);
                atomicHashMap_atomicID.put(atomicID, atomic);
                atomicList.add(atomic);
                logger.debug("Register Atomic: " + atomic);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }//读取到空的原子
        }

//        logger.debug(atomicHashMap_atomicID);
    }

}
