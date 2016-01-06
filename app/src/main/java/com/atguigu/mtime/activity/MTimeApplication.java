package com.atguigu.mtime.activity;

import android.app.ActivityManager;
import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.atguigu.mtime.R;
import com.atguigu.mtime.bean.ChartsTopListBean;
import com.atguigu.mtime.bean.DiscoverTopBean;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by HanFeng on 2015/12/14.
 */
public class MTimeApplication extends Application {

    private static RequestQueue requestQueue;
    private static DiscoverTopBean discoverTopBean;
    private static ChartsTopListBean chartsTopListBean;

    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    /**
     * 返回Volley请求队列
     * @return
     */
    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }

    /**
     * 设置发现页面四个头布局数据
     * @param discoverTopBean
     */
    public static void setDiscoverTopBean(DiscoverTopBean discoverTopBean) {
        MTimeApplication.discoverTopBean = discoverTopBean;
    }

    /**
     * 返回发现页面四个头布局数据
     * @return
     */
    public static DiscoverTopBean getDiscoverTopBean() {
        return MTimeApplication.discoverTopBean;
    }

    /**
     * 设置排行榜数据
     * @param chartsTopListBean
     */
    public static void setChartsTopListBean(ChartsTopListBean chartsTopListBean) {
        MTimeApplication.chartsTopListBean = chartsTopListBean;
    }

    /**
     * 获取排行榜的数据
     * @return
     */
    public static ChartsTopListBean getChartsTopListBean() {
        return chartsTopListBean;
    }

}
