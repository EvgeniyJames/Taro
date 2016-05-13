package com.yevgeniyzamkovenko.taro.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.yevgeniyzamkovenko.taro.R;
import com.yevgeniyzamkovenko.taro.manager.TokenManager;
import com.yevgeniyzamkovenko.taro.task.GetTokenTask;
import com.yevgeniyzamkovenko.taro.utils.IDefines;
import com.yevgeniyzamkovenko.taro.utils.MiscUtils;
import com.yevgeniyzamkovenko.taro.utils.NetworkUtil;

public class LoginFragment extends Fragment {

    private Button m_buttonLogin;
    private WebView m_webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        m_buttonLogin = (Button) view.findViewById(R.id.btn_login);
        m_buttonLogin.setOnClickListener(new LoginButtonListener());

        return view;
    }

    class LoginButtonListener implements View.OnClickListener {

        Dialog authDialog;

        @Override
        public void onClick(View v) {

            final Activity context = getActivity();
            if (!NetworkUtil.IsNetworkConnected(context)) {
//                Toast.makeText(getContext(), getString(R.string.txtNoInternetConnection), Toast.LENGTH_SHORT).show();
                return;
            }

            authDialog = new Dialog(context);
            authDialog.setContentView(R.layout.auth_dialog);

            m_webView = (WebView) authDialog.findViewById(R.id.webview);
            m_webView.getSettings().setJavaScriptEnabled(IDefines.JAVASCRIPT_ENABLE);
            m_webView.loadUrl(IDefines.URL_LOGIN + "?redirectUrl=" + IDefines.URL_REDIRECT);
            m_webView.setWebViewClient(new WebViewClient(){

                Intent m_resultIntent = new Intent();
                boolean m_isAuthComplete = false;
                String m_authCode;

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageFinished(view, url);

                    MiscUtils.TRACE(getClass().getSimpleName(), "Page started");
                    MiscUtils.TRACE(getClass().getSimpleName(), "URL: " + url);

                    if(url.contains("?code=") && !m_isAuthComplete) {

                        Uri uri = Uri.parse(url);
                        m_authCode = uri.getQueryParameter("code");
                        MiscUtils.TRACE(getClass().getSimpleName(), "Code: " + m_authCode);

                        m_isAuthComplete = true;

                        TokenManager.GetInstance().SetCode(m_authCode);

                        new GetTokenTask().execute();

                        context.setResult(Activity.RESULT_OK, m_resultIntent);

                        MiscUtils.TRACE(getClass().getSimpleName(), "dismiss auth dialog");

//                        Toast.makeText(context, getString(R.string.txtLoggedIn), Toast.LENGTH_SHORT).show();

                        authDialog.dismiss();
                    }
                }
            });

            authDialog.show();
            authDialog.setTitle("title");
            authDialog.setCancelable(true);
        }
    }
}
