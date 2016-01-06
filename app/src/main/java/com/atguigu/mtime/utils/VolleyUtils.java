package com.atguigu.mtime.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by HanFeng on 2015/12/11.
 */
public class VolleyUtils {

    private Context context;
    private static VolleyUtils volleyUtils;
    private RequestQueue requestQueue;
    private String result;

    private VolleyUtils(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    public static VolleyUtils getInstance(Context context) {
        if (volleyUtils == null) {
            volleyUtils = new VolleyUtils(context);
        }
        return volleyUtils;
    }

    /**
     * 联网请求文本数据
     * @param method
     * @param url
     * @return
     */
    public String requestString(int method, String url) {
        StringRequest request = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                result = s;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                result = null;
            }
        });
        requestQueue.add(request);
        return result;
    }

}
