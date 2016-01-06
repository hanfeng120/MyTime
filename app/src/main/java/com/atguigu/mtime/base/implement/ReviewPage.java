package com.atguigu.mtime.base.implement;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.ViewUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.MTimeApplication;
import com.atguigu.mtime.activity.NewsBrowserActivity;
import com.atguigu.mtime.activity.ReviewBrowserActivity;
import com.atguigu.mtime.adapter.ReViewPagerAdapter;
import com.atguigu.mtime.base.BasePage;
import com.atguigu.mtime.bean.DiscoverTopBean;
import com.atguigu.mtime.bean.ReViewBean;
import com.atguigu.mtime.utils.Constants;
import com.atguigu.mtime.utils.MTimeUtils;
import com.atguigu.mtime.utils.SPUtils;
import com.atguigu.mtime.utils.UrlConstants;
import com.atguigu.mtime.view.widget.LoadingView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static com.atguigu.mtime.bean.ReViewBean.*;

/**
 * 发现Tab——影评
 * Created by HanFeng on 2015/12/6.
 */
public class ReviewPage extends BasePage {
    private ReViewPagerAdapter adapter;
    /**
     * 读取保存的ID
     */
    private static final String TAG = ReviewPage.class.getSimpleName();
    private ListView listView;
    private ImageView ivHeadtitle;
    private ImageView ivMincard;
    private TextView tvHead_title;
    private TextView tvHead_content;
    private List<ReViewBean.ReViewListData> data;
    private DiscoverTopBean discoverTopBean;
    private LoadingView loadingView;
    private ImageView ivLoadFailed;


    public ReviewPage(Context context) {
        super(context);
    }

    public View initView() {

        View view = View.inflate(context, R.layout.reviewpage, null);
        listView = (ListView) view.findViewById(R.id.listView);
        ivLoadFailed = (ImageView) view.findViewById(R.id.iv_review_load_failed);
        loadingView = (LoadingView) view.findViewById(R.id.loading_review);

        View viewtitle = View.inflate(context, R.layout.reviewpager_title, null);
        ivHeadtitle = (ImageView) viewtitle.findViewById(R.id.iv_headtitle);
        ivMincard = (ImageView) viewtitle.findViewById(R.id.iv_mincard);
        tvHead_title = (TextView) viewtitle.findViewById(R.id.tv_head_title);
        tvHead_content = (TextView) viewtitle.findViewById(R.id.tv_head_content);

        //给ListView添加头
        listView.addHeaderView(viewtitle);

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
    }

    private void getDataFromNetTop() {
        RequestParams params = new RequestParams(UrlConstants.DISCOVER_PAGE_TOP_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                processDataTop(result);
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

    private void processDataTop(String result) {
        Gson gson = new Gson();
        discoverTopBean = gson.fromJson(result, DiscoverTopBean.class);
        MTimeApplication.setDiscoverTopBean(discoverTopBean);

        setTopData();
    }

    /**
     * 设置头部数据
     */
    private void setTopData() {
        String title = discoverTopBean.review.movieName;
        String ImageUrl = discoverTopBean.review.imageUrl;
        String PosterUrl = discoverTopBean.review.posterUrl;
        String content = discoverTopBean.review.title;

        tvHead_title.setText(title);
        tvHead_content.setText(content);
        x.image().bind(ivHeadtitle, ImageUrl);
        x.image().bind(ivMincard, PosterUrl);
    }


    /**
     * 从网络获取数据
     */
    private void getDataFromNet() {
        RequestParams params = new RequestParams(UrlConstants.REVIEW_LISTVIEW);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                processData(result);
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


    /**
     * 解析和显示数据
     *
     * @param result
     */
    private void processData(String result) {
        ReViewBean reViewBean = manualprocessData(result);
        data = reViewBean.data;

        loadingView.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        ivLoadFailed.setVisibility(View.GONE);

        setAdapter();
    }

    /**
     * 设置适配器
     */
    private void setAdapter() {
        adapter = new ReViewPagerAdapter(context, data);
        listView.setAdapter(adapter);
    }

    private ReViewBean manualprocessData(String result) {
        String res = result.replace("\\n", "");

        ReViewBean reViewBean = new ReViewBean();
        try {
            JSONArray jsonArray = new JSONArray(result);
            if (jsonArray != null) {
                List<ReViewBean.ReViewListData> data = new ArrayList<ReViewBean.ReViewListData>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    ReViewBean.ReViewListData reViewListData = reViewBean.new ReViewListData();
                    data.add(reViewListData);
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    reViewListData.id = jsonObject.optInt("id");
                    reViewListData.nickname = jsonObject.optString("nickname");
                    reViewListData.rating = jsonObject.optString("rating");
                    reViewListData.summary = jsonObject.optString("summary");
                    reViewListData.title = jsonObject.optString("title");
                    reViewListData.userImage = jsonObject.optString("userImage");

                    JSONObject jsonObject1 = jsonObject.optJSONObject("relatedObj");
                    if (jsonObject1 != null) {
                        ReViewBean.ReViewListData.ReViewListDataTag relatedObj = reViewListData.new ReViewListDataTag();
                        relatedObj.id = jsonObject1.optInt("id");
                        relatedObj.image = jsonObject1.optString("image");
                        relatedObj.rating = jsonObject1.optString("rating");
                        relatedObj.title = jsonObject1.optString("title");
                        relatedObj.type = jsonObject1.optInt("type");

                        reViewListData.relatedObj = relatedObj;
                    }
                    reViewBean.data = data;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reViewBean;
    }

    private void setListener() {
        ivHeadtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//顶部

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position = position - 1;
                if (position < 0) {
                    return;
                }
                //标记点击的item
                ReViewBean.ReViewListData reViewListData = data.get(position);
                String idArray = SPUtils.getInstance(context).getValue(Constants.READ_ARRAY_ID, "");
                if (!idArray.contains(reViewListData.id + "")) {
                    String values = idArray + reViewListData.id + ",";
                    SPUtils.getInstance(context).save(Constants.READ_ARRAY_ID, values);
                    adapter.notifyDataSetChanged();
                }
                //跳转到内容页面
                if (data != null) {
                    Intent intent = new Intent(context, ReviewBrowserActivity.class);
                    intent.putExtra("id", data.get(position).id + "");
                    context.startActivity(intent);
                }
            }
        });

        ivLoadFailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });

    }

}
