package com.yevgeniyzamkovenko.taro.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;

import com.yevgeniyzamkovenko.taro.Profile;
import com.yevgeniyzamkovenko.taro.R;
import com.yevgeniyzamkovenko.taro.listener.OnTokenChangeListener;
import com.yevgeniyzamkovenko.taro.manager.ProfileManager;

public class MainActivity extends FragmentActivity implements OnTokenChangeListener {

    private DrawerLayout m_drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProfileManager.GetInstance().AddListener(this);

        ConfigMenu();
    }

    private void ConfigMenu() {
        boolean isAuthorized = ProfileManager.GetInstance().IsAuthorized();
        m_drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        m_drawerLayout.setDrawerLockMode(isAuthorized ? DrawerLayout.LOCK_MODE_UNLOCKED : DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        if (isAuthorized) {
            LoadMainScreen();
        } else {
            LeadLoginScreen();
        }
    }

    private void LeadLoginScreen() {
        getFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new LoginFragment())
                .commit();
    }

    private void LoadMainScreen() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new MainScreenFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (HasBackStack()) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    private boolean HasBackStack() {
        return getFragmentManager().getBackStackEntryCount() > 0;
    }

    @Override
    public void OnTokenChange(Profile profile) {
        boolean activated = profile != null;
        m_drawerLayout.setDrawerLockMode(activated ? DrawerLayout.LOCK_MODE_UNLOCKED :DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        if (activated) {
            LoadMainScreen();
        }
    }
}
