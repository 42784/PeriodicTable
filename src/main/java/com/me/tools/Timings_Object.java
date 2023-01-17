package com.me.tools;

public class Timings_Object {
    private long startTime;
    private long stopTime;

    public void startTimings() {
        startTime = System.currentTimeMillis();
    }

    public void stopTimings() {
        stopTime = System.currentTimeMillis();
    }

    public long getTime() {
        return (stopTime - startTime);
    }
}
