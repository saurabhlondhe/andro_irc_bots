package com.example.centos.ircbot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class web extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = (WebView) findViewById(R.id.web1);
        //Intent i = getIntent();
        //String message = i.getStringExtra(hello_bot.EXTRA_MESSAGE);
        getSupportActionBar().hide();
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //--------------------------------------------------------------------
        webView.setWebViewClient(new myCustomWebViewClient());
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        //--------------------------------------------------------
        webView.loadUrl("https://saurabhlondhe.github.io/");
        Boolean canGoBack=webView.canGoBack();

    }
    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            // do your stuff here
            Intent returnBtn = new Intent(getApplicationContext(),
                    MainActivity.class);

            startActivity(returnBtn);
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
    private class myCustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //here load the url in your webview
            webView.loadUrl(url);

            return true;
        }
    }
}
