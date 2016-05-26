package com.yevgeniyzamkovenko.taro.utils;

import android.util.Log;

import java.util.Random;

/**
 * Author: yevgeniy.zamkovenko
 * Date: 17.10.2015
 */
public class MiscUtils {

    private static final String NAME = "TARO";
    private static final String PREFIX = NAME + " ### : ";

    public static void DEBUG(String tag, String log) {
        Log.i(PREFIX + tag, log);
    }

    public static void DEBUG(String log) {
        Log.i(PREFIX, log);
    }

    public static int Rand(int a, int b) {
        Random r = new Random();

        int max = Math.max(a, b);
        int min = Math.min(a, b);

        return r.nextInt(max - min) + min;
    }
}
