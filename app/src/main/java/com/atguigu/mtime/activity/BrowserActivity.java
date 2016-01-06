package com.atguigu.mtime.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.base.BaseActivity;

/**
 * 内嵌浏览器
 * 1.通过intent将URL传入进来，key : url
 * 2.如果需要设置标题, key : title
 * created by 赵迅艺 at 2015/12/7
 */
public class BrowserActivity extends BaseActivity {

    private View rootView;
    private WebView mBrowser;
    private ImageView img_fail;
    private String http_url;
    private String title;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initSetting();
        initData();

    }

    private void initView() {
        mBrowser = (WebView) rootView.findViewById(R.id.browser);
        img_fail = (ImageView) rootView.findViewById(R.id.img_err_browser);
    }

    private void initData() {
        Intent intent = getIntent();
        http_url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");

        if (http_url != null) {
            if (http_url.startsWith("http://")) {
                mBrowser.loadUrl(http_url);
            } else {
                http_url = "http://" + http_url;
                mBrowser.loadUrl(http_url);
            }
        }

        setFavoriteVisiable(View.GONE);
    }

    /**
     * 初始化浏览器参数
     */
    private void initSetting() {
        WebSettings webSet = mBrowser.getSettings();
        webSet.setJavaScriptEnabled(true);
        webSet.setAllowFileAccess(true);
        webSet.setBuiltInZoomControls(true);
        webSet.setSupportZoom(true);

        MyWebViewClient myWebViewClient = new MyWebViewClient();
        mBrowser.setWebViewClient(myWebViewClient);
        MyWebChromeClient myWebChromeClient = new MyWebChromeClient();
        mBrowser.setWebChromeClient(myWebChromeClient);
        mBrowser.setDownloadListener(new MyDownload());
    }

    @Override
    protected View getView() {
        rootView = View.inflate(this, R.layout.activity_browser, null);
        return rootView;
    }

    @Override
    protected void clickBack() {
        finish();
    }

    @Override
    protected void clickFavorite() {

    }

    @Override
    protected void clickShare() {

    }

    class MyDownload implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent,
                                    String contentDisposition, String mimetype, long contentLength) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            img_fail.setVisibility(View.VISIBLE);
            mBrowser.setVisibility(View.GONE);
            view.loadUrl("javascript:document.body.innerHTML=\"\"");

        }

    }

    class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mBrowser.canGoBack()) {
            mBrowser.goBack();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK && !mBrowser.canGoBack()) {
            finish();
            return true;
        }
        return false;
    }
}


