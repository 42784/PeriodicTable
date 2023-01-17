package com.me.tools;

public class Timings {
    private static long startTime;
    private static long stopTime;
    public static void startTimings(){
        startTime = System.currentTimeMillis();
    }
    public static void stopTimings(){
        stopTime = System.currentTimeMillis();
    }
    public static long getTime(){
        return (stopTime-startTime);
    }
}
