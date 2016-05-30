package com.yevgeniyzamkovenko.taro.utils;

/**
 * Author: yevgeniy.zamkovenko
 * Date: 14.10.2015
 */
public interface IDefines {

    boolean DEBUG_MODE = true;

    boolean JAVASCRIPT_ENABLE = true;

    String CLIENT_ID = "1";
    String CLIENT_SECRET = "1";

    String URL_MAIN = "http://mrtest71.somee.com";
    String URL_LOGIN = URL_MAIN + "/en/home/login";
    String URL_REDIRECT = URL_MAIN + "/blunk.htm";

    String URL_API = URL_MAIN + "/api";

    String URL_GET_TOKEN = URL_API + "/user/token";
    String URL_GET_LOGIN = URL_API + "/user/login";

    String EXTRA_CODE = "code";
}
