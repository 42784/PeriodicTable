package com.me.tools;

import com.me.Atomic;
import com.me.PeriodicTable;
import org.apache.log4j.Logger;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author 秃头老狗
 * @version 1.0
 */
public class PeriodicTableSystem {
    public static final Logger logger = Logger.getLogger(PeriodicTableSystem.class);
    private static final String PeriodicTableFormat = """ 
            *                              *
            **                        ******
            **                        ******
            **              ****************
            **              ****************
            ********************************
            ********************************
            """;

    public static void joinSystem() {
        System.out.println();
        logger.info("===============================元素周期表系统===============================");
        logger.info("0.退出系统");
        logger.info("1.通过原子序数查询 该原子信息");
        logger.info("2.通过元素符号查询 该原子信息");
        logger.info("3.通过原子序数查询 由该原子的组成的化学式");
        logger.info("4.通过元素符号查询 由该原子的组成的化学式");
        logger.info("5.打印所有原子的信息");
        logger.info("6.打印元素周期表");
        Integer nextInt = Utilitys.userNextInt("你的选择");
        switch (nextInt) {
            case 0 -> System.exit(0);
            case 1 -> queryAtomicByID();
            case 2 -> queryAtomicBySymbol();

            case 3 -> getChemicalFormulaByID();
            case 4 -> getChemicalFormulaBySymbol();

            case 5 -> printAllAtomic();
            case 6 -> printPeriodicTable();

        }
        joinSystem();
    }

    public static void getChemicalFormulaByID() {
        Timings_Object timingsObject = new Timings_Object();
        Integer atomicID = Utilitys.userNextInt("原子序数");
        timingsObject.startTimings();
        try {
            Atomic atomic = PeriodicTable.atomicHashMap_atomicID.get(atomicID);
            String symbol = atomic.getSymbol();
            File file = URLTool.requestServer(//请求网络获取化学式
                    "https://ptable.com/JSON/compounds/formula=" + symbol, "ChemicalFormula.yaml");
            timingsObject.stopTimings();
            logger.info(String.format("联网获取化学式成功，耗时: %dms", timingsObject.getTime()));
            printChemicalFormula(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getChemicalFormulaBySymbol() {
        Timings_Object timingsObject = new Timings_Object();
        String symbol = Utilitys.userNextLine("元素符号(如He)");
        timingsObject.startTimings();
        File file = URLTool.requestServer(//请求网络获取化学式
                "https://ptable.com/JSON/compounds/formula=" + symbol, "ChemicalFormula.yaml");
        timingsObject.stopTimings();
        logger.info(String.format("联网获取化学式成功，耗时: %dms", timingsObject.getTime()));
        printChemicalFormula(file);

    }

    public static void printChemicalFormula(File file) {
        YamlFile yamlFile = new YamlTools(file).getYamlFile();
        List<Map<?, ?>> mapList = yamlFile.getMapList("matches");
        for (Map<?, ?> map : mapList) {
            Object molecularformula = map.get("molecularformula");//Str
            List<String> list = (List<String>) map.get("allnames");

            logger.info("化学式: " + molecularformula);
            StringBuilder stringBuilder = new StringBuilder("[");
            for (String s : list) {
                stringBuilder.append('\"').append(s).append('\"').append(',');
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1).append(']');

            logger.info("化学式全称: " + stringBuilder.toString());

        }

    }

    public static void queryAtomicByID() {
        Integer atomicID = Utilitys.userNextInt("原子序数");
        try {
            Atomic atomic = PeriodicTable.atomicHashMap_atomicID.get(atomicID);
            Atomic.printAtomicAttribute(atomic);
        } catch (Exception e) {
            logger.warn("未知的原子序数");
        }
    }

    public static void queryAtomicBySymbol() {
        try {
            String symbol = Utilitys.userNextLine("元素符号(如He)");
            Atomic atomic = PeriodicTable.atomicHashMap_symbol.get(symbol);
            Atomic.printAtomicAttribute(atomic);
        } catch (Exception e) {
            logger.warn("未知的原子符号");
        }
    }

    public static void printAllAtomic() {
        List<Atomic> atomicList = PeriodicTable.atomicList;
        for (Atomic atomic : atomicList) {
            logger.info(atomic);
            Atomic.printAtomicAttribute(atomic);
            System.out.println();
        }
    }

    public static void printPeriodicTable() {
        int AtomicId = 1;
        char[] chars = PeriodicTableFormat.toCharArray();
        StringBuilder stringBuilder = new StringBuilder("元素周期表:\n");
        for (char aChar : chars) {
            switch (aChar) {
                case '*' -> {
                    Atomic atomic = PeriodicTable.atomicHashMap_atomicID.get(AtomicId++);
                    stringBuilder.append(String.format("%-2s ", atomic.getSymbol()));
                }
                case ' ' -> {
                    stringBuilder.append("   ");
                }
                case '\n' -> {
                    stringBuilder.append('\n');
                }
            }
        }
        logger.info(stringBuilder.toString());
    }
}
