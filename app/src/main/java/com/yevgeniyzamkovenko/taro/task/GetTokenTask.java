package com.yevgeniyzamkovenko.taro.task;

import android.os.AsyncTask;

import com.yevgeniyzamkovenko.taro.manager.ProfileManager;
import com.yevgeniyzamkovenko.taro.utils.IDefines;
import com.yevgeniyzamkovenko.taro.utils.MiscUtils;

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

        String result = PerformPostCall(IDefines.URL_GET_TOKEN, params);

        MiscUtils.DEBUG(getClass().getSimpleName(), "result: " + result);
        ProfileManager.GetInstance().SetToken(result);

        MiscUtils.DEBUG(getClass().getSimpleName(), "HttpPost execute");

    }

    public String PerformPostCall(String requestURL,
                                  HashMap<String, String> postDataParams) {

        MiscUtils.DEBUG(getClass().getSimpleName(), "URL: " + requestURL);

        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            String charset = "UTF-8";

            conn.setDoOutput(true);
            conn.setRequestProperty("Accept-Charset", charset);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);

            OutputStream writer = conn.getOutputStream();

            String postDataString = getPostDataString(postDataParams);

            MiscUtils.DEBUG(getClass().getSimpleName(), "postDataString: " + postDataString);

            writer.write(postDataString.getBytes(charset));

            writer.flush();
            writer.close();
            int responseCode = conn.getResponseCode();

            MiscUtils.DEBUG(getClass().getSimpleName(), "responseCode: " + responseCode);

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first){
                first = false;
            }
            else
            {
                result.append("&");
            }

            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
        }

        return result.toString();
    }
}
