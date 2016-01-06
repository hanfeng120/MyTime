package com.atguigu.mtime.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.base.BaseActivity;
import com.atguigu.mtime.adapter.GoldleAdapter;
import com.atguigu.mtime.base.BasePage;
import com.atguigu.mtime.base.implement.TablePager;
import com.atguigu.mtime.bean.RangkingBean;
import com.atguigu.mtime.utils.UrlConstants;
import com.google.gson.Gson;
import com.viewpagerindicator.TabPageIndicator;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yiran on 2015/12/11.
 * 全球排行榜页面
 */
public class GoldleActivity extends BaseActivity {
    private static final String TAG = GoldleActivity.class.getSimpleName();
    private ViewPager vpGoldle;
    private TabPageIndicator tabPageIndicator;
    private TextView tvTitle;
    private ImageView ivBack;
    private GoldleAdapter adapter;
    private List<RangkingBean.TopListData.SubTopList> subTopLists;
    private List<BasePage> basePages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        setListener();

    }

    private void setListener() {

    }

    @Override
    protected View getView() {
        View view = View.inflate(this, R.layout.activity_goldle, null);
        vpGoldle = (ViewPager) view.findViewById(R.id.vp_goldle);
        tabPageIndicator = (TabPageIndicator) view.findViewById(R.id.tabPageIndicator);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        ivBack = (ImageView) view.findViewById(R.id.iv_back);
        return view;
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

    private void initData() {
        super.setFavoriteVisiable(View.GONE);
        super.setShareVisiable(View.GONE);
        getDataFromNet();//联网请求全球排行榜的数据
    }

    /**
     * 从网络获取数据
     */
    private void getDataFromNet() {
        RequestParams params = new RequestParams(UrlConstants.GOLDLE_URL);
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

//        Volley.newRequestQueue(this).add(new StringRequest(UrlConstants.GOLDLE_URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                processData(s);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//            }
//        }));
    }

    /**
     * 解析数据
     *
     * @param result
     */
    private void processData(String result) {
        Gson gson = new Gson();
        RangkingBean rangkingBean = gson.fromJson(result, RangkingBean.class);
        subTopLists = rangkingBean.topList.get(2).subTopList;
        setAdapter();
    }

    private void setAdapter() {
        basePages = new ArrayList<BasePage>();
        for (int i = 0; i < subTopLists.size(); i++) {
            TablePager tablePager = new TablePager(GoldleActivity.this, subTopLists.get(i).pageSubAreaId);
            basePages.add(tablePager);
        }
        adapter = new GoldleAdapter(basePages,subTopLists);
        vpGoldle.setAdapter(adapter);
        tabPageIndicator.setViewPager(vpGoldle);
        tabPageIndicator.setVisibility(View.VISIBLE);
        basePages.get(0).initData();

    }

}
