package com.atguigu.mtime.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.base.BaseActivity;
import com.atguigu.mtime.adapter.ChartsMoviesAdapter;
import com.atguigu.mtime.bean.ChartsMoviesBean;
import com.atguigu.mtime.utils.MTimeUtils;
import com.atguigu.mtime.utils.UrlConstants;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

/**
 * 排行榜列表电影
 * Created by yui-pc on 2015/12/12.
 */
public class ChartsMoviesActivity extends BaseActivity {
    private ListView lvChartsMoviesListview;
    private TextView tvChartsMoviesTitle;
    private TextView tvChartsMoviesSubtitle;


    private ChartsMoviesBean chartsMoviesBean;
    private ArrayList<ChartsMoviesBean.MoviesBean> movoesData;
    private ChartsMoviesBean.TopListBean topListData;
    private String id;
    private String type;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getData();
    }

    /**
     * 获取传递过来的数据
     */
    private void getData() {
        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");
        if (id != null && !TextUtils.isEmpty(id)) {
            if ("top".equals(type)) {
                url = UrlConstants.DISCOVER_PAGE_CHARTS_MOVIES_TOP + id + "&locationId={2}";
            } else {
                url = UrlConstants.DISCOVER_PAGE_CHARTS_MOVIES_ITEM + id;
            }
            getDataFromNet();
        } else {
            MTimeUtils.showMessage(this, "无数据");
        }
        setListener();
    }

    /**
     * 从网络获取数据
     */
    public void getDataFromNet() {
        RequestParams params = new RequestParams(url);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                parseData(result);
                Log.i("RES", result);
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
     * 解析json数据
     *
     * @return
     */
    public void parseData(String result) {
        chartsMoviesBean = new Gson().fromJson(result, ChartsMoviesBean.class);
        movoesData = chartsMoviesBean.movies;
        topListData = chartsMoviesBean.topList;

        setData();
        setAdapter();
    }

    /**
     * 设置数据
     */
    private void setData(){
        tvChartsMoviesTitle.setText(chartsMoviesBean.topList.name);
        tvChartsMoviesSubtitle.setText(chartsMoviesBean.topList.summary);

    }
    private void setAdapter(){
        lvChartsMoviesListview.setAdapter(new ChartsMoviesAdapter(this, chartsMoviesBean.movies));
    }

    @Override
    protected View getView() {
        View view = View.inflate(this,R.layout.activity_charts_movies_listview,null);
        View topview = View.inflate(this, R.layout.activity_charts_movies, null);

        tvChartsMoviesTitle = (TextView) topview.findViewById(R.id.tv_charts_movies_title);
        tvChartsMoviesTitle = (TextView) topview.findViewById(R.id.tv_charts_movies_title);
        tvChartsMoviesSubtitle = (TextView) topview.findViewById(R.id.tv_charts_movies_subtitle);
        lvChartsMoviesListview = (ListView) view.findViewById(R.id.lv_charts_movies_listview);
        lvChartsMoviesListview.addHeaderView(topview);
        return view;
    }

    private void setListener() {
        lvChartsMoviesListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ChartsMoviesActivity.this, MovieDetailActivity.class);
                intent.putExtra("id", movoesData.get(position).id + "");
                startActivity(intent);
            }
        });
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


}
