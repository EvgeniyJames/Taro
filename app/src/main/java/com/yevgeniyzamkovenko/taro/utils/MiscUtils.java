package com.yevgeniyzamkovenko.taro.utils;

import android.util.Log;

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
}
