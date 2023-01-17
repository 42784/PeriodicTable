package com.me;

/**
 * @author 秃头老狗
 * @version 1.0
 */
public class Atomic {
    private int atomicNumber;//原子序数
    private String symbol;//元素符号
    private double weight;//相对原子质量
    private double melt;//熔点 K
    private double boil;//沸点 K
    private int discover;//发现年份

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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getMelt() {
        return melt;
    }

    public void setMelt(double melt) {
        this.melt = melt;
    }

    public double getBoil() {
        return boil;
    }

    public void setBoil(double boil) {
        this.boil = boil;
    }

    public int getDiscover() {
        return discover;
    }

    public void setDiscover(int discover) {
        this.discover = discover;
    }

    public Atomic(int atomicNumber, String symbol, double weight, double melt, double boil, int discover) {
        this.atomicNumber = atomicNumber;
        this.symbol = symbol;
        this.weight = weight;
        this.melt = melt;
        this.boil = boil;
        this.discover = discover;
    }

    @Override
    public String toString() {
        return "Atomic{" +
                "atomicNumber=" + atomicNumber +
                ", symbol='" + symbol + '\'' +
                ", weight=" + weight +
                ", melt=" + melt +
                ", boil=" + boil +
                ", discover=" + discover +
                '}';
    }
}
