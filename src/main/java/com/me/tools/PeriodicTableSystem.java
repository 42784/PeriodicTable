package com.me.tools;

import com.me.Atomic;
import com.me.PeriodicTable;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author 秃头老狗
 * @version 1.0
 */
public class PeriodicTableSystem {
    public static final Logger logger = Logger.getLogger(PeriodicTableSystem.class);
    public static void joinSystem() {
        System.out.println();
        logger.info("===============================元素周期表系统===============================");
        logger.info("0.退出系统");
        logger.info("1.通过原子序数查询原子信息");
        logger.info("2.通过元素符号查询");
        logger.info("3.打印所有原子的信息");
        Integer nextInt = Utilitys.userNextInt("你的选择");
        switch (nextInt) {
            case 0 -> System.exit(0);
            case 1 -> queryAtomicByID();
            case 2 -> queryAtomicBySymbol();
            case 3 -> printAllAtomic();

        }
        joinSystem();
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
    public static void printAllAtomic(){
        List<Atomic> atomicList = PeriodicTable.atomicList;
        for (Atomic atomic : atomicList) {
            logger.info(atomic);
            Atomic.printAtomicAttribute(atomic);
            System.out.println();
        }

    }
}
