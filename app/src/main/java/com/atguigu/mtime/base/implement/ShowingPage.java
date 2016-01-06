package com.atguigu.mtime.base.implement;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.MovieDetailActivity;
import com.atguigu.mtime.activity.NewsBrowserActivity;
import com.atguigu.mtime.adapter.HotShowingBaseAdapter;
import com.atguigu.mtime.base.BasePage;
import com.atguigu.mtime.bean.HotShowingBean;
import com.atguigu.mtime.utils.UrlConstants;
import com.atguigu.mtime.view.widget.LoadingView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 购票Tab——正在热映
 * Created by HanFeng on 2015/12/6.
 */
public class ShowingPage extends BasePage {

    private View rootView;
    private ListView listView;
    private LoadingView loadingView;

    private HotShowingBean hotShowingBean;
    private boolean hasLoad = false;

    public ShowingPage(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        rootView = View.inflate(context, R.layout.page_hot_showing, null);
        listView = (ListView) rootView.findViewById(R.id.lv_hot_showing);
        loadingView = (LoadingView) rootView.findViewById(R.id.loading_showing);
        return rootView;
    }

    @Override
    public void initData() {
        setListener();
        if (!hasLoad) {
            getDataFromNet();
        }
    }

    private void setListener() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("id", hotShowingBean.ms.get(position).id + "");
                context.startActivity(intent);
            }
        });

    }

    /**
     * 请求网络获取数据
     */
    public void getDataFromNet() {
        RequestParams params = new RequestParams(UrlConstants.TICKET_HOT_SHOWING);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                processData(result);
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
     * 解析数据
     * @param result
     */
    private void processData(String result) {
        hasLoad = true;
        hotShowingBean = JSON.parseObject(result, HotShowingBean.class);
        loadingView.setVisibility(View.GONE);

        listView.setAdapter(new HotShowingBaseAdapter(context, hotShowingBean.ms));

    }
}

