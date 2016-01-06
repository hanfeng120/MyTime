package com.atguigu.mtime.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.base.BaseActivity;
import com.atguigu.mtime.adapter.ChartsPersonsAdapter;
import com.atguigu.mtime.bean.ChartsPersonsBean;
import com.atguigu.mtime.utils.MTimeUtils;
import com.atguigu.mtime.utils.UrlConstants;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

/**
 * 排行榜人物列表
 * Created by yui-pc on 2015/12/14.
 */
public class ChartsPersonActivity extends BaseActivity {


    private TextView tv_charts_persons_title;
    private TextView tv_charts_persons_subhead;
    private ListView lv_charts_persons_listview;

    private ChartsPersonsBean chartsPersonsBean;
    private ChartsPersonsBean.NextTopListBean nextTopListData;
    private int pageCount;
    private ArrayList<ChartsPersonsBean.PersonsBean> personsData;
    private ChartsPersonsBean.PreviousTopListBean previousTopListData;
    private ChartsPersonsBean.TopListBean topListData;
    private int totalCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getdata();
        setListener();
    }

    /**
     * 设置id
     */
    private String topListId;
    private String url;

    public void getdata() {
        topListId = getIntent().getStringExtra("id");
        if (topListId != null && !TextUtils.isEmpty(topListId)) {
            url = UrlConstants.DISCOVER_PAGE_CHARTS_MOVIES_ITEM + topListId;
            getDataFromNet();
        } else {
            MTimeUtils.showMessage(this, "无数据");
        }
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
        chartsPersonsBean = new Gson().fromJson(result, ChartsPersonsBean.class);
        nextTopListData = chartsPersonsBean.nextTopList;
        personsData = chartsPersonsBean.persons;
        previousTopListData = chartsPersonsBean.previousTopList;
        topListData = chartsPersonsBean.topList;
        setData();
        setAdapter();
    }

    /**
     * 设置数据
     */
    private void setData() {
        tv_charts_persons_title.setText(topListData.name);
        tv_charts_persons_subhead.setText(topListData.summary);
    }

    private void setAdapter() {
        lv_charts_persons_listview.setAdapter(new ChartsPersonsAdapter(this, chartsPersonsBean.persons));
    }


    @Override
    protected View getView() {
        View view = View.inflate(this, R.layout.activity_charts_persons_listview, null);
        View person = View.inflate(this, R.layout.activity_charts_person, null);

        tv_charts_persons_title = (TextView) person.findViewById(R.id.tv_charts_persons_title);
        tv_charts_persons_subhead = (TextView) person.findViewById(R.id.tv_charts_persons_subhead);
        lv_charts_persons_listview = (ListView) view.findViewById(R.id.lv_charts_persons_listview);
        lv_charts_persons_listview.addHeaderView(person);
        return view;
    }

    private void setListener() {
        lv_charts_persons_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    protected void clickBack() {

    }

    @Override
    protected void clickFavorite() {

    }

    @Override
    protected void clickShare() {

    }


}

