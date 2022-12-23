package nataliia.semenova.examgame.webview;

import android.annotation.SuppressLint;
import android.webkit.CookieSyncManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewManager {
    private WebSettings webSettings;
    private WebView webView;


    public WebViewManager(WebView webView) {
        this.webView = webView;
        setSettings();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setSettings() {
        webSettings  = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(false);
        webSettings.setDisplayZoomControls(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

        webView.setInitialScale(1);
        webView.loadUrl("https://yandex.ru");

        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebViewManager webView, WebResourceRequest request) {
                return false;
            }

            public void onPageFinished(WebViewManager view, String url) {
                CookieSyncManager.getInstance().sync();
            }
        });
    }
}
