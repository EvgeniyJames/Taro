package com.yevgeniyzamkovenko.taro.utils;

/**
 * Author: yevgeniy.zamkovenko
 * Date: 14.10.2015
 */
public interface IDefines {

    boolean JAVASCRIPT_ENABLE = true;

    String CLIENT_ID = "1";

    String URL_MAIN = "http://mrtest71.somee.com";
    String URL_LOGIN = URL_MAIN + "/en/home/authorize";
    String URL_REDIRECT = URL_MAIN + "/blank.htm";

    String URL_API = URL_MAIN + "/api";

    String URL_GET_TOKEN = URL_API + "/user/token";
    String URL_GET_LOGIN = URL_API + "/user/login";
}
