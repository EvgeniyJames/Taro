package com.yevgeniyzamkovenko.taro.fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.yevgeniyzamkovenko.taro.Profile;
import com.yevgeniyzamkovenko.taro.R;
import com.yevgeniyzamkovenko.taro.listener.OnTokenChangeListener;
import com.yevgeniyzamkovenko.taro.manager.CardManager;
import com.yevgeniyzamkovenko.taro.manager.ProfileManager;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements OnTokenChangeListener {

    private DrawerLayout m_drawerLayout;

    private ArrayAdapter<String> m_adapter;
    ArrayList<String> m_items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProfileManager.GetInstance().AddListener(this);
        CardManager.GetInstance().Init(this);

        ConfigMenu();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void ConfigMenu() {
        boolean isAuthorized = ProfileManager.GetInstance().IsAuthorized();
        m_drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        m_drawerLayout.setDrawerLockMode(isAuthorized ? DrawerLayout.LOCK_MODE_UNLOCKED : DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        if (isAuthorized) {
            LoadMainScreen();
        } else {
            LoadLoginScreen();
        }
    }

    private void LoadLoginScreen() {
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
        m_drawerLayout.setDrawerLockMode(activated ? DrawerLayout.LOCK_MODE_UNLOCKED : DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        if (activated) {
            ListView listView = (ListView) m_drawerLayout.findViewById(R.id.sliding_menu);

            m_items.clear();
            m_items.add(profile.GetName());

            m_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, m_items);

//            listView.setAdapter(m_adapter);
        }

        if (activated) {
            LoadMainScreen();
        }
    }
}
