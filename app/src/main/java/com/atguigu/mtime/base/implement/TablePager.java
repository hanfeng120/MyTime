package com.atguigu.mtime.base.implement;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.MovieDetailActivity;
import com.atguigu.mtime.adapter.TavlePagerAdapter;
import com.atguigu.mtime.base.BasePage;
import com.atguigu.mtime.bean.GoldleItemBean;
import com.atguigu.mtime.utils.UrlConstants;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by yiran on 2015/12/11.
 * 排行榜也签页面
 */
public class TablePager extends BasePage {

    private static final String TAG = TablePager.class.getSimpleName();
    private boolean hasLoad = false;
    private String pageSubAreaId;
    private ListView lvTabContent;
    private TextView tvTabTitle;
    private TavlePagerAdapter tavlePagerAdapter;
    private GoldleItemBean goldleItemBean;
    private List<GoldleItemBean.MoviesData> movies;

    public TablePager(Context context, String pageSubAreaId) {
        super(context);
        this.pageSubAreaId = pageSubAreaId;
    }


    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.tablepager, null);
        lvTabContent = (ListView) view.findViewById(R.id.lv_tab_content);

        View headView = View.inflate(context, R.layout.table_head, null);
        tvTabTitle = (TextView) headView.findViewById(R.id.tv_tab_title);

        lvTabContent.addHeaderView(headView);

        return view;

    }

    @Override
    public void initData() {
        setListener();
        getDataFromNet();
    }

    /**
     * 联网请求数据
     */
    private void getDataFromNet() {
        RequestParams params = new RequestParams(UrlConstants.GOLDLE_ITEM_URL + pageSubAreaId + UrlConstants.GOLDLE_ITEM1_URL);
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
     * 数据解析
     *
     * @param result
     */
    private void processData(String result) {
        Gson gson = new Gson();
        goldleItemBean = gson.fromJson(result, GoldleItemBean.class);
        movies = goldleItemBean.movies;

        tvTabTitle.setText(goldleItemBean.topList.summary);
        setAdapter();
    }

    private void setAdapter() {
        tavlePagerAdapter = new TavlePagerAdapter(context, movies);
        lvTabContent.setAdapter(tavlePagerAdapter);
    }

    private void setListener() {
        lvTabContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("id", goldleItemBean.movies.get(position).id);
                context.startActivity(intent);
            }
        });
    }

}

