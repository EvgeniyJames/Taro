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
    @Override
    protected Void doInBackground(Void... params) {
        GetLoginInstance();
        return null;
    }

    public void GetLoginInstance() {
        HashMap<String, String> params = new HashMap<>();

        params.put("token", ProfileManager.GetInstance().GetToken());

        String result = NetworkUtil.PerformPostCall(IDefines.URL_GET_LOGIN, params);

        Profile profile = new Profile();
        profile.SetName(result);
        ProfileManager.GetInstance().SetProfile(profile);
    }
}
