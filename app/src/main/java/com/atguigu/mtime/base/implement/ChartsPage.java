package com.atguigu.mtime.base.implement;

import android.content.Context;
import android.content.Intent;
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
import com.atguigu.mtime.activity.ChartsMoviesActivity;
import com.atguigu.mtime.activity.ChartsPersonActivity;
import com.atguigu.mtime.activity.GoldleActivity;
import com.atguigu.mtime.activity.MTimeApplication;
import com.atguigu.mtime.adapter.ChartsPageAdapter;
import com.atguigu.mtime.base.BasePage;
import com.atguigu.mtime.bean.ChartsListBean;
import com.atguigu.mtime.bean.ChartsTopListBean;
import com.atguigu.mtime.bean.DiscoverTopBean;
import com.atguigu.mtime.utils.UrlConstants;
import com.atguigu.mtime.view.widget.LoadingView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.http.loader.LoaderFactory;
import org.xutils.x;

import java.util.ArrayList;

/**
 * 发现Tab——排行榜
 * <p/>
 * Created by 申瑞芹 on 2015/12/9.
 */
public class ChartsPage extends BasePage {

    private static final String TAG = ChartsPage.class.getSimpleName();
    private ListView listviewCharts;//listVIew
    private ImageView ivVideoIcon;//顶部的背景
    private TextView tvVideoTitle;//标题
    private LinearLayout llMtime;
    private LinearLayout llCn;
    private LinearLayout llWorld;
    private LoadingView loadingView;
    private ImageView ivLoadFailed;

    private ArrayList<ChartsListBean.TopLists> data;//适配器中的数据
    private ChartsPageAdapter adapter;//排行榜的适配器
    private DiscoverTopBean discoverTopBean;
    private ChartsTopListBean chartsTopListBean;


    public ChartsPage(Context context) {
        super(context);
    }

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.chartspage, null);
        listviewCharts = (ListView) view.findViewById(R.id.listview_charts);
        loadingView = (LoadingView) view.findViewById(R.id.loading_charts);
        ivLoadFailed = (ImageView) view.findViewById(R.id.iv_charts_load_failed);

        View topChartsView = View.inflate(context, R.layout.top_charts, null);
        listviewCharts.addHeaderView(topChartsView);

        llMtime = (LinearLayout) topChartsView.findViewById(R.id.ll_charts_page_mtime);
        llCn = (LinearLayout) topChartsView.findViewById(R.id.ll_charts_page_cn);
        llWorld = (LinearLayout) topChartsView.findViewById(R.id.ll_charts_page_world);
        ivVideoIcon = (ImageView) topChartsView.findViewById(R.id.iv_discover_charts_top_bg);//顶部背景
        tvVideoTitle = (TextView) topChartsView.findViewById(R.id.tv_discover_charts_top_title);//名字
        return view;
    }

    @Override
    public void initData() {
        setListener();

        getDataFromNet();//联网请求预告片下面部分的数据

        if (MTimeApplication.getDiscoverTopBean() != null) {
            discoverTopBean = MTimeApplication.getDiscoverTopBean();
            setTopData();
        } else {
            getDataFromNetTop();//联网请求预告片顶部的数据
        }

        if (MTimeApplication.getChartsTopListBean() != null) {
            chartsTopListBean = MTimeApplication.getChartsTopListBean();
        } else {
            getChartsTopDataFromNet();
        }
    }

    private void getDataFromNetTop() {
        RequestParams params = new RequestParams(UrlConstants.DISCOVER_PAGE_TOP_URL);
        Callback.Cancelable cancelable = x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                discoverTopBean = gson.fromJson(result, DiscoverTopBean.class);
                MTimeApplication.setDiscoverTopBean(discoverTopBean);

                setTopData();
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
     * 设置头部数据
     */
    private void setTopData() {
        String title = discoverTopBean.topList.title;
        String ImageUrl = discoverTopBean.topList.imageUrl;

        tvVideoTitle.setText(title);
        x.image().bind(ivVideoIcon, ImageUrl);
    }


    int index = 1;//起始页面

    /**
     * 网络请求预告片下面部分的Json数据
     */
    private void getDataFromNet() {
        RequestParams params = new RequestParams(UrlConstants.DISCOVER_PAGE_CHARTS_URL + index);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ChartsListBean chartsPageBean = gson.fromJson(result, ChartsListBean.class);
                loadingView.setVisibility(View.GONE);
                ivLoadFailed.setVisibility(View.GONE);
                listviewCharts.setVisibility(View.VISIBLE);

                data = chartsPageBean.topLists;
                adapter = new ChartsPageAdapter(context, data);
                listviewCharts.setAdapter(adapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ivLoadFailed.setVisibility(View.VISIBLE);
                loadingView.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void getChartsTopDataFromNet() {

        MTimeApplication.getRequestQueue().add(new StringRequest(UrlConstants.CHARTS_TOP_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                chartsTopListBean = JSON.parseObject(s, ChartsTopListBean.class);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }));

    }

    private void setListener() {
        ivVideoIcon.setOnClickListener(new View.OnClickListener() {//顶部数据
            @Override
            public void onClick(View v) {
                if (discoverTopBean != null && discoverTopBean.topList.type == 0) {
                    Intent intent = new Intent(context, ChartsMoviesActivity.class);
                    intent.putExtra("id", discoverTopBean.topList.id);
                    context.startActivity(intent);
                }
            }
        });

        llMtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chartsTopListBean != null) {
                    Intent intent = new Intent(context, ChartsMoviesActivity.class);
                    intent.putExtra("id", chartsTopListBean.getTopList().get(0).getPageSubAreaId()
                            + "");
                    intent.putExtra("type", "top");
                    context.startActivity(intent);
                }
            }
        });

        llCn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chartsTopListBean != null) {
                    Intent intent = new Intent(context, ChartsMoviesActivity.class);
                    intent.putExtra("id", chartsTopListBean.getTopList().get(1).getPageSubAreaId()
                            + "");
                    intent.putExtra("type", "top");
                    context.startActivity(intent);
                }
            }
        });

        llWorld.setOnClickListener(new View.OnClickListener() {//全球票房榜
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoldleActivity.class);
                context.startActivity(intent);
            }
        });

        listviewCharts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    if (data.get(position - 1).type == 0) {
                        Intent intent = new Intent(context, ChartsMoviesActivity.class);
                        intent.putExtra("id", data.get(position - 1).getId() + "");
                        context.startActivity(intent);
                    } else if (data.get(position - 1).type == 2) {
                        Intent intent = new Intent(context, ChartsPersonActivity.class);
                        intent.putExtra("id", data.get(position - 1).getId() + "");
                        context.startActivity(intent);
                    }
                }
            }

        });

        ivLoadFailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingView.setVisibility(View.VISIBLE);
                ivLoadFailed.setVisibility(View.GONE);
                initData();
            }
        });

    }
}
