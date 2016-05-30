package com.yevgeniyzamkovenko.taro.task;

import android.os.AsyncTask;

import com.yevgeniyzamkovenko.taro.manager.ProfileManager;
import com.yevgeniyzamkovenko.taro.utils.IDefines;
import com.yevgeniyzamkovenko.taro.utils.MiscUtils;
import com.yevgeniyzamkovenko.taro.utils.NetworkUtil;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Author: yevgeniy.zamkovenko
 * Date: 17.10.2015
 */
public class GetTokenTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... code) {

        MiscUtils.DEBUG(getClass().getSimpleName(), "GetToken Request");
        GetTokenInstance();

        return null;
    }

    public void GetTokenInstance() {
        HashMap<String, String> params = new HashMap<>();

        params.put("code", JSONObject.quote(ProfileManager.GetInstance().GetCode()));
        params.put("client_id", IDefines.CLIENT_ID);
        params.put("client_secret", IDefines.CLIENT_SECRET);

        MiscUtils.DEBUG(getClass().getSimpleName(), "Params: " + params);

        String result = NetworkUtil.PerformPostCall(IDefines.URL_GET_TOKEN, params);

        MiscUtils.DEBUG(getClass().getSimpleName(), "result: " + result);
        ProfileManager.GetInstance().SetToken(result);

        MiscUtils.DEBUG(getClass().getSimpleName(), "HttpPost execute");

    }
}
