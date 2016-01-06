package com.atguigu.mtime.base.implement;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.MTimeApplication;
import com.atguigu.mtime.adapter.ChartsPageAdapter;
import com.atguigu.mtime.adapter.PrevuePageAdapter;
import com.atguigu.mtime.base.BasePage;
import com.atguigu.mtime.bean.ChartsListBean;
import com.atguigu.mtime.bean.DiscoverTopBean;
import com.atguigu.mtime.bean.PrevueListBean;
import com.atguigu.mtime.utils.UrlConstants;
import com.atguigu.mtime.view.widget.LoadingView;
import com.google.gson.Gson;

import org.w3c.dom.Text;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现Tab——预告片
 * Created by 申瑞芹 on 2015/12/6.
 */
public class PrevuePage extends BasePage {

    private static final String TAG = PrevuePage.class.getSimpleName();
    private ListView listviewPrevue;//listVIew
    private ImageView ivVideoIcon;//顶部的背景
    private TextView tvVideoTitle;//标题
    private LoadingView loadingView;

    private ArrayList<PrevueListBean.Trailers> data;//适配器中的数据
    private PrevuePageAdapter adapter;//排行榜的适配器
    private PrevueListBean prevueListBean;
    private DiscoverTopBean discoverTopBean;

    public PrevuePage(Context context) {
        super(context);
    }

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.prevue_page, null);
        listviewPrevue = (ListView) view.findViewById(R.id.listview_prevue);
        loadingView = (LoadingView) view.findViewById(R.id.loading_prevue);

        View topChartsView = View.inflate(context, R.layout.top_prevue, null);
        listviewPrevue.addHeaderView(topChartsView);

        /**
         * 排行榜 顶部的数据
         */
        ivVideoIcon = (ImageView) topChartsView.findViewById(R.id.tv_discover_prevue_top_bg);//顶部背景
        tvVideoTitle = (TextView) topChartsView.findViewById(R.id.iv_discover_prevue_top_title);//名字

        return view;

    }

    @Override
    public void initData() {
        setListener();
        loadingView.setVisibility(View.VISIBLE);
        getDataFromNet();//联网请求预告片下面部分的数据

        if (MTimeApplication.getDiscoverTopBean() != null) {
            discoverTopBean = MTimeApplication.getDiscoverTopBean();
            setTopData();
        } else {
            getDataFromNetTop();//联网请求预告片顶部的数据
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

    private void setTopData() {
        String title = discoverTopBean.trailer.title;
        String ImageUrl = discoverTopBean.trailer.imageUrl;

        tvVideoTitle.setText(title);
        x.image().bind(ivVideoIcon, ImageUrl);
    }


    /**
     * 网络请求预告片下面部分的Json数据
     */
    private void getDataFromNet() {
        RequestParams params = new RequestParams(UrlConstants.DISCOVER_PAGE_PREVUE_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                prevueListBean = gson.fromJson(result, PrevueListBean.class);
                loadingView.setVisibility(View.GONE);
                listviewPrevue.setVisibility(View.VISIBLE);

                data = prevueListBean.trailers;

                adapter = new PrevuePageAdapter(context, data);
                listviewPrevue.setAdapter(adapter);
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
        ivVideoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(discoverTopBean.trailer.mp4Url), "video/*");
                context.startActivity(intent);
            }
        });

        listviewPrevue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0 && prevueListBean != null) {
                    position = position - 1;
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(prevueListBean.trailers.get(position).url), "video/*");
                    context.startActivity(intent);
                }
            }
        });
    }

}
