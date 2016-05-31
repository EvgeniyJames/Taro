package com.yevgeniyzamkovenko.taro.manager;

import com.yevgeniyzamkovenko.taro.Profile;
import com.yevgeniyzamkovenko.taro.listener.OnTokenChangeListener;
import com.yevgeniyzamkovenko.taro.task.GetLoginTask;
import com.yevgeniyzamkovenko.taro.utils.MiscUtils;

import java.util.ArrayList;

/**
 * Author: yevgeniy.zamkovenko
 * Date: 17.10.2015
 */
public class ProfileManager {

    private ArrayList<OnTokenChangeListener> m_onTokenChangeListeners = new ArrayList<>();

    private static ProfileManager s_instance = null;

    private Profile m_profile;

    private String m_token;
    private String m_code;

    public static ProfileManager GetInstance() {
        if (s_instance == null) {
            CreateInstance();
        }
        return s_instance;
    }

    private ProfileManager() {

    }

    public void SetProfile(Profile profile) {
        m_profile = profile;

        MiscUtils.DEBUG("SetName: " + profile.GetName());

        NotifyListeners();
    }

    private static void CreateInstance() {
        s_instance = new ProfileManager();
    }

    public void Init() {
        CreateInstance();
    }

    public String GetToken() {
        return m_token;
    }

    public void SetToken(String token) {
        m_token = token;

        MiscUtils.DEBUG("SetToken: " + token);

        UpdateUserInfo();
    }

    private void UpdateUserInfo() {
        new GetLoginTask().execute();
    }

    public void NotifyListeners() {
        int size = m_onTokenChangeListeners.size();
        for (int i = 0; i < size; i++) {
            m_onTokenChangeListeners.get(i).OnTokenChange(m_profile);
        }
    }

    public void InitFakeProfile() {
        Profile profile = new Profile();
        profile.SetName("FakeName");
        SetProfile(profile);
    }

    public void AddListener(OnTokenChangeListener listener) {
        if (!m_onTokenChangeListeners.contains(listener)) {
            m_onTokenChangeListeners.add(listener);
        }
    }

    public void RemoveListener(OnTokenChangeListener listener) {
        m_onTokenChangeListeners.remove(listener);
    }

    public boolean IsAuthorized() {
        return m_profile != null;
    }
}