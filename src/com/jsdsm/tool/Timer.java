package com.jsdsm.tool;

public class Timer {
    private static long start;
    private static long end;
    private static String msg = "”√ ±£∫";

    public static void start() {
        start = System.currentTimeMillis();
    }

    public static String end() {
         end =  System.currentTimeMillis();
         return msg+(end-start)+" s";
    }
}
