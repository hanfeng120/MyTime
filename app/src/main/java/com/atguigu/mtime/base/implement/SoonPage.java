package com.atguigu.mtime.base.implement;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.MovieDetailActivity;
import com.atguigu.mtime.adapter.IncomingBaseAdapter;
import com.atguigu.mtime.adapter.IncomingRecycleAdapter;
import com.atguigu.mtime.base.BasePage;
import com.atguigu.mtime.bean.MovieIncomingBean;
import com.atguigu.mtime.utils.UrlConstants;
import com.atguigu.mtime.view.widget.LoadingView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 购票Tab——即将上映
 * Created by HanFeng on 2015/12/6.
 */
public class SoonPage extends BasePage {

    private ListView listView;
    private LinearLayout listHeaderOff;
    private LinearLayout attentionNumLayout;
    private TextView attentionNum;
    private RecyclerView rcIncoming;
    private ImageView ad2Seperate;
    private ViewPager ad1;
    private ImageView ad1Seperate;
    private TextView incomingNum;
    private LoadingView loadingView;
    private ScrollView scrollView;

    private MovieIncomingBean incomingBean;
    public boolean hasLoad = false;

    public SoonPage(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View rootView = View.inflate(context, R.layout.page_soon, null);
        View headerView = View.inflate(context, R.layout.list_header_film_off, null);
        listView = (ListView) rootView.findViewById(R.id.lv_incoming);
        loadingView = (LoadingView) rootView.findViewById(R.id.loading_soon);

        listHeaderOff = (LinearLayout)headerView.findViewById( R.id.list_header_off );
        attentionNumLayout = (LinearLayout)headerView.findViewById( R.id.attention_num_layout );
        attentionNum = (TextView)headerView.findViewById( R.id.attention_num );
        rcIncoming = (RecyclerView)headerView.findViewById( R.id.rc_incoming );
        ad2Seperate = (ImageView)headerView.findViewById( R.id.ad2_seperate );
        ad1 = (ViewPager)headerView.findViewById( R.id.ad1 );
        ad1Seperate = (ImageView)headerView.findViewById( R.id.ad1_seperate );
        incomingNum = (TextView)headerView.findViewById( R.id.incoming_num );

        listView.addHeaderView(headerView);
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
                intent.putExtra("id", incomingBean.moviecomings.get(position).id + "");
                context.startActivity(intent);
            }
        });
    }

    private void getDataFromNet() {
        RequestParams params = new RequestParams(UrlConstants.TICKET_INCOMING);
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

    private void parseData(String result) {
        hasLoad = true;
        incomingBean = JSON.parseObject(result, MovieIncomingBean.class);
        loadingView.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);

        setRCAdapter();
        setLVAdapter();
    }

    /**
     * 给RecycleView设置数据
     */
    private void setRCAdapter() {
        IncomingRecycleAdapter adapter = new IncomingRecycleAdapter(context, incomingBean.attention);
        rcIncoming.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        rcIncoming.setAdapter(adapter);

    }

    /**
     * 给底部ListView设置适配器
     */
    private void setLVAdapter() {
        incomingNum.setText("(" + incomingBean.moviecomings.size() + "部)");
        Log.e("SIZE", incomingBean.moviecomings.size() + "");
        IncomingBaseAdapter incomingBaseAdapter = new IncomingBaseAdapter(context, incomingBean.moviecomings);
        listView.setAdapter(incomingBaseAdapter);
    }
}
