package com.atguigu.mtime.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.base.BaseActivity;
import com.atguigu.mtime.adapter.NewsPageAdapter;
import com.atguigu.mtime.bean.NewsBean;
import com.atguigu.mtime.bean.NewsListBean;
import com.atguigu.mtime.utils.ShareUtils;
import com.atguigu.mtime.utils.UrlConstants;

import java.util.List;

public class NewsActivity extends BaseActivity {

    private ListView listView;

    private List<NewsListBean.NewsList> data;
    private String id;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initTitleBar();
        getData();
        initData();

    }

    private void initTitleBar() {
        setFavoriteVisiable(View.GONE);
        setShareVisiable(View.GONE);
    }

    private void getData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        title = intent.getStringExtra("title");
        super.setTitle(title);

        getDataFromNet();
    }

    private void initData() {

    }

    @Override
    protected View getView() {
        View view = View.inflate(this, R.layout.activity_news, null);
        listView = (ListView) view.findViewById(R.id.lv_news);
        return view;
    }

    private void getDataFromNet() {
//        if (id == null) return;

        Volley.newRequestQueue(this).add(new StringRequest(UrlConstants.DETAIL_MOVIE_NEWS, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                parseData(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }));
    }

    private void parseData(String result) {
        NewsBean newsBean = JSON.parseObject(result, NewsBean.class);

        listView.setAdapter(new NewsPageAdapter(this, newsBean.newsList));
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
        ShareUtils.showShare(this, "");
    }
}
