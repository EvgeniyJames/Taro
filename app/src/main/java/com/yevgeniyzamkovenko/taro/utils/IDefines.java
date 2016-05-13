package com.yevgeniyzamkovenko.taro.utils;

/**
 * Author: yevgeniy.zamkovenko
 * Date: 14.10.2015
 */
public interface IDefines {
    boolean JAVASCRIPT_ENABLE = true;

    String CLIENT_ID = "1";
    String CLIENT_SECRET = "1";

    String URL_MAIN = "http://faydaentest.somee.com";
    String URL_LOGIN = URL_MAIN + "/en/home/login";
    String URL_REDIRECT = URL_MAIN + "/blunk.htm";

    String URL_API = URL_MAIN + "/api";

    String URL_GET_TOKEN = URL_API + "/user/token";

    String EXTRA_CODE = "code";
}
