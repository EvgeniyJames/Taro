package com.yevgeniyzamkovenko.taro.utils;

import android.util.Log;

/**
 * Author: yevgeniy.zamkovenko
 * Date: 17.10.2015
 */
public class MiscUtils {

    private static final String PREFIX = "RECIPES";

    public static void TRACE(String tag, String log) {
        Log.i(PREFIX + " ### : " + tag, log);
    }

    }
