package com.atguigu.mtime.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.base.BaseActivity;
import com.atguigu.mtime.bean.ReviewDetailBean;
import com.atguigu.mtime.utils.UrlConstants;

/**
 * 新闻详情浏览Activity
 * created by hanfeng at 2015/12/13
 */
public class ReviewBrowserActivity extends BaseActivity {

    private View rootView;
    private WebView mBrowser;
    private ImageView img_fail;
    private String newsId;
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
        newsId = intent.getStringExtra("id");
        title = intent.getStringExtra("title");

        if (newsId != null) {
            getDataFromNet();
        }
    }

    /**
     * 联网请求获取网页代码
     */
    private void getDataFromNet() {
        Log.e("URL", UrlConstants.DETAIL_REVIEW + newsId);
        Volley.newRequestQueue(this).add(new StringRequest(UrlConstants.DETAIL_NEWS + newsId, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s != null && !TextUtils.isEmpty(s)) {
//                    parseData(s);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }));
    }

    private void parseData(String s) {

        ReviewDetailBean detailBean = JSON.parseObject(s, ReviewDetailBean.class);
        if (detailBean != null) {
            mBrowser.loadDataWithBaseURL(null, detailBean.getContent(), "text/html", "UTF8", null);
        }
    }

    /**
     * 初始化浏览器参数
     */
    private void initSetting() {
        WebSettings webSet = mBrowser.getSettings();
        webSet.setJavaScriptEnabled(true);
        webSet.setAllowFileAccess(true);
        webSet.setSupportZoom(true);
        webSet.setDefaultTextEncodingName("UTF-8");

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
