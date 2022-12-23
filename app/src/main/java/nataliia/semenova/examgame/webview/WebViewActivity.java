package nataliia.semenova.examgame.webview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.CookieSyncManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import nataliia.semenova.examgame.R;

public class WebViewActivity extends AppCompatActivity {
    private WebView webView;
    private WebSettings webSettings;
    private static final String LOAD_URL = "https://kitstore.partners/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_webview);
        webView = findViewById(R.id.wv_page);
        webSettings = webView.getSettings();
        setSettings();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setSettings() {
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(false);
        webSettings.setDisplayZoomControls(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webView.setInitialScale(1);
        webView.loadUrl(LOAD_URL);

        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request) {
                return false;
            }

            public void onPageFinished(WebView view, String url) {
                CookieSyncManager.getInstance().sync();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
