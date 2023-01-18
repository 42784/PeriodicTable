package com.me;

import org.apache.log4j.Logger;

/**
 * @author 秃头老狗
 * @version 1.0
 */
public class Atomic {
    private String name;//元素名称
    private int atomicNumber;//原子序数
    private String symbol;//元素符号
    private String weight;//相对原子质量
    private String melt;//熔点 K
    private String boil;//沸点 K
    private String phase;//常温的物态
    public static final Logger logger = Logger.getLogger(Atomic.class);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }

    public void setAtomicNumber(int atomicNumber) {
        this.atomicNumber = atomicNumber;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getMelt() {
        return melt;
    }

    public void setMelt(String melt) {
        this.melt = melt;
    }

    public String getBoil() {
        return boil;
    }

    public void setBoil(String boil) {
        this.boil = boil;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public Atomic(String name, int atomicNumber, String symbol, String weight, String melt, String boil, String phase) {
        this.name = name;
        this.atomicNumber = atomicNumber;
        this.symbol = symbol;
        this.weight = weight;
        this.melt = melt;
        this.boil = boil;
        this.phase = phase;
    }

    @Override
    public String toString() {
        return "Atomic{" +
                "name='" + name + '\'' +
                ", atomicNumber=" + atomicNumber +
                ", symbol='" + symbol + '\'' +
                ", weight='" + weight + '\'' +
                ", melt='" + melt + '\'' +
                ", boil='" + boil + '\'' +
                ", phase='" + phase + '\'' +
                '}';
    }

    public static void printAtomicAttribute(Atomic atomic) {
        logger.info(String.format("""
                        Atomic:
                        \t元素名称= %s
                        \t原子序数= %d
                        \t原子符号= %s
                        \t相对原子质量= %s
                        \t熔点= %s K
                        \t沸点= %s K
                        \t物态= %s""",
                atomic.name, atomic.atomicNumber, atomic.symbol, atomic.weight, atomic.melt, atomic.boil,atomic.phase));
    }
}
