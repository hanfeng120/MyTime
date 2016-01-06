package com.atguigu.mtime.base.implement;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.GoldleActivity;
import com.atguigu.mtime.activity.MTimeApplication;
import com.atguigu.mtime.activity.NewsBrowserActivity;
import com.atguigu.mtime.adapter.NewsPageAdapter;
import com.atguigu.mtime.base.BasePage;
import com.atguigu.mtime.bean.ChartsTopListBean;
import com.atguigu.mtime.bean.DiscoverTopBean;
import com.atguigu.mtime.bean.DiscoverTopRefreshBean;
import com.atguigu.mtime.bean.NewsListBean;
import com.atguigu.mtime.utils.UrlConstants;
import com.atguigu.mtime.view.RefreshListView;
import com.atguigu.mtime.view.widget.LoadingView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * 发现Tab——新闻
 * Created by 申瑞芹 on 2015/12/9.
 */
public class NewsPage extends BasePage {

    public static final String TAG = NewsPage.class.getSimpleName();

    private ImageView ivPrevueBg;//新闻顶部的背景
    private TextView tvTopVideoTitle;//新闻顶部的标题
    private RefreshListView listView;//新闻的listView
    private LinearLayout llTopCn;
    private LinearLayout llTopWorld;
    private LoadingView loadingView;

    private List<NewsListBean.NewsList> data;
    private NewsPageAdapter newsPageAdapter;
    private DiscoverTopBean discoverTopBean;

    /**
     * 是否下拉刷新
     */
    private boolean isOnPullDwonRrefresh = false;

    public NewsPage(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.news_page, null);
        listView = (RefreshListView) view.findViewById(R.id.listview_news);

        View topPrevueView = View.inflate(context, R.layout.top_news, null);
        listView.addSecondView(topPrevueView);

        llTopCn = (LinearLayout) topPrevueView.findViewById(R.id.ll_news_page_cn);
        llTopWorld = (LinearLayout) topPrevueView.findViewById(R.id.ll_news_page_world);
        ivPrevueBg = (ImageView) topPrevueView.findViewById(R.id.iv_discover_news_top_video_icon);//顶部背景
        tvTopVideoTitle = (TextView) topPrevueView.findViewById(R.id.tv_discover_news_top_video_title);//名字
        loadingView = (LoadingView) view.findViewById(R.id.loading_news);

        return view;
    }

    class MyOnRefreshListener implements RefreshListView.OnRefreshListener {


        @Override
        public void onPullDwonRrefresh() {
            isOnPullDwonRrefresh = true;
            getDataFromNetTopRefresh();
        }

        @Override
        public void onUpwardPullRefresh() {

        }
    }

    @Override
    public void initData() {
        setListener();

        getDataFromNet();//联网请求预告片下面部分的数据

        if (MTimeApplication.getDiscoverTopBean() != null) {
            discoverTopBean = MTimeApplication.getDiscoverTopBean();
        } else {
            getDataFromNetTop();//联网请求预告片顶部的数据
        }

    }

    /**
     * 新闻页面的起始页面
     */

    int index = 1;

    /**
     * 新闻页面内容的联网
     */
    private void getDataFromNet() {
        RequestParams params = new RequestParams(UrlConstants.DISCOVER_PAGE_NEWS_URL + index);
        Callback.Cancelable cancelable = x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                NewsListBean newsListBean = gson.fromJson(result, NewsListBean.class);

                loadingView.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);

                data = newsListBean.newsList;
                newsPageAdapter = new NewsPageAdapter(context, data);
                listView.setAdapter(newsPageAdapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**
     * 新闻页面顶部数据的联网
     */
    private void getDataFromNetTop() {
        RequestParams params = new RequestParams(UrlConstants.DISCOVER_PAGE_TOP_URL);
        Callback.Cancelable cancelable = x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                discoverTopBean = gson.fromJson(result, DiscoverTopBean.class);
                MTimeApplication.setDiscoverTopBean(discoverTopBean);

                loadingView.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);

                String title = discoverTopBean.news.title;
                String ImageUrl = discoverTopBean.news.imageUrl;

                tvTopVideoTitle.setText(title);
                x.image().bind(ivPrevueBg, ImageUrl);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void setListener() {
        ivPrevueBg.setOnClickListener(new View.OnClickListener() {//顶部图片
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsBrowserActivity.class);
                intent.putExtra("id", discoverTopBean.news.newsID + "");
                context.startActivity(intent);
            }
        });

        llTopCn.setOnClickListener(new View.OnClickListener() {//票房排行榜
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, GoldleActivity.class));
            }
        });

        llTopWorld.setOnClickListener(new View.OnClickListener() {//票房排行榜
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, GoldleActivity.class));
            }
        });

        listView.setOnRefreshListener(new MyOnRefreshListener());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    if (data != null) {
                        Intent intent = new Intent(context, NewsBrowserActivity.class);
                        intent.putExtra("id", data.get(position - 1).id + "");
                        context.startActivity(intent);
                    }
                }
            }
        });
    }


    /**
     * 新闻页面的顶部刷新数据的刷新
     */
    public void getDataFromNetTopRefresh() {
        if (isOnPullDwonRrefresh) {
            isOnPullDwonRrefresh = false;


            //得到数据成功
            //下拉刷新隐藏掉
            listView.onRefreshFinish(true);

        }
    }
}
