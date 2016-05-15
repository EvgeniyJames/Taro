package com.yevgeniyzamkovenko.taro;

/**
 * Author: yevgeniy.zamkovenko
 * Date: 15.05.2016
 */
public class Profile {
    private String m_name;

    public Profile() {

    }

    public void SetName(String name) {
        m_name = name;
    }

    public String GetName() {
        return m_name;
    }
}
