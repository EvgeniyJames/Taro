package com.yevgeniyzamkovenko.taro.manager;

/**
 * Author: yevgeniy.zamkovenko
 * Date: 17.10.2015
 */
public class TokenManager {

    private static TokenManager s_instance = null;

    private String m_token;
    private String m_code;

    public static TokenManager GetInstance() {
        if (s_instance == null) {
            CreateInstance();
        }
        return s_instance;
    }

    private TokenManager() {

    }

    private static void CreateInstance() {
        s_instance = new TokenManager();
    }

    public void Init() {
        CreateInstance();
    }

    public String GetToken() {
        return m_token;
    }

    public void SetToken(String token) {
        m_token = token;
    }

    public String GetCode() {
        return m_code;
    }

    public void SetCode(String code) {
        m_code = code;
    }

}
