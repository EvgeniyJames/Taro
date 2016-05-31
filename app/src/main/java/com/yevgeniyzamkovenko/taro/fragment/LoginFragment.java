package com.yevgeniyzamkovenko.taro.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.yevgeniyzamkovenko.taro.R;
import com.yevgeniyzamkovenko.taro.manager.ProfileManager;
import com.yevgeniyzamkovenko.taro.task.GetTokenTask;
import com.yevgeniyzamkovenko.taro.utils.IDefines;
import com.yevgeniyzamkovenko.taro.utils.MiscUtils;
import com.yevgeniyzamkovenko.taro.utils.NetworkUtil;

public class LoginFragment extends Fragment{

    private Button m_buttonLogin;
    private Button m_fakeButtonLogin;

    private WebView m_webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        m_fakeButtonLogin = (Button) view.findViewById(R.id.btn_fake_login);
        m_fakeButtonLogin.setOnClickListener(new FakeLoginButtonListener());

        m_buttonLogin = (Button) view.findViewById(R.id.btn_login);
        m_buttonLogin.setOnClickListener(new LoginButtonListener());

        return view;
    }

    class LoginButtonListener implements View.OnClickListener {

        private Dialog m_authDialog;

        @Override
        public void onClick(View v) {

            final Activity context = getActivity();
            if (!NetworkUtil.IsNetworkConnected(context)) {
                Toast.makeText(context, R.string.txt_no_internet, Toast.LENGTH_SHORT).show();
                return;
            }

            m_authDialog = new Dialog(context);
            m_authDialog.setContentView(R.layout.auth_dialog);

            m_webView = (WebView) m_authDialog.findViewById(R.id.webview);
            m_webView.getSettings().setJavaScriptEnabled(IDefines.JAVASCRIPT_ENABLE);
            m_webView.loadUrl(IDefines.URL_LOGIN + "?redirectUrl=" + IDefines.URL_REDIRECT);
            m_webView.setWebViewClient(new WebViewClient(){

                Intent m_resultIntent = new Intent();
                boolean m_isAuthComplete = false;
                String token;

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageFinished(view, url);

                    MiscUtils.DEBUG(getClass().getSimpleName(), "Page started");
                    MiscUtils.DEBUG(getClass().getSimpleName(), "URL: " + url);

                    if(url.contains("?token=") && !m_isAuthComplete) {

                        Uri uri = Uri.parse(url);
                        token = uri.getQueryParameter("token");
                        MiscUtils.DEBUG(getClass().getSimpleName(), "token: " + token);

                        m_isAuthComplete = true;

                        ProfileManager.GetInstance().SetToken(token);

                        new GetTokenTask().execute();

                        context.setResult(Activity.RESULT_OK, m_resultIntent);

                        MiscUtils.DEBUG(getClass().getSimpleName(), "dismiss auth dialog");

//                        Toast.makeText(context, getString(R.string.txtLoggedIn), Toast.LENGTH_SHORT).show();

                        m_authDialog.dismiss();
                    }
                }
            });

            m_authDialog.show();
            m_authDialog.setTitle(R.string.txt_login);
            m_authDialog.setCancelable(true);
        }
    }

    class FakeLoginButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ProfileManager.GetInstance().InitFakeProfile();
        }
    }

}
