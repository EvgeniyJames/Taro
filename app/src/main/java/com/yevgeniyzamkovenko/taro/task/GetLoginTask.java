package com.yevgeniyzamkovenko.taro.task;

import android.os.AsyncTask;

import com.yevgeniyzamkovenko.taro.Profile;
import com.yevgeniyzamkovenko.taro.manager.ProfileManager;
import com.yevgeniyzamkovenko.taro.utils.IDefines;
import com.yevgeniyzamkovenko.taro.utils.NetworkUtil;

import java.util.HashMap;

/**
 * Author: yevgeniy.zamkovenko
 * Date: 30.05.2016
 */
public class GetLoginTask extends AsyncTask<Void, Void, Void> {

    private String m_login;

    @Override
    protected Void doInBackground(Void... params) {
        GetLoginInstance();
        return null;
    }

    public void GetLoginInstance() {
        HashMap<String, String> params = new HashMap<>();

        params.put("token", ProfileManager.GetInstance().GetToken());

        m_login = NetworkUtil.PerformPostCall(IDefines.URL_GET_LOGIN, params);
       m_login =  m_login.substring(1, m_login.length() - 1);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Profile profile = new Profile();
        profile.SetName(m_login);
        ProfileManager.GetInstance().SetProfile(profile);
    }
}
