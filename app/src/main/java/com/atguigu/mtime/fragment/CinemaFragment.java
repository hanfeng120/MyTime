package com.atguigu.mtime.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.MainActivity;
import com.atguigu.mtime.adapter.CinemaFragmentAdapter;
import com.atguigu.mtime.bean.CinemaFragmentBean;
import com.atguigu.mtime.utils.UrlConstants;
import com.atguigu.mtime.view.widget.LoadingView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HanFeng on 2015/12/7.
 */
public class CinemaFragment extends Fragment {

    private static final String TAG = CinemaFragment.class.getSimpleName();
    private MainActivity mainActivity;
    private TextView tvAll;
    private TextView tvOdds;
    private TextView tvNearby;
    private TextView tvScreen;
    private ListView lvCinema;
    private ImageView ivHeadcard;
    private LinearLayout llRoot;
    private LoadingView loadingView;
    private List<CinemaFragmentBean.CinemaListData> data;
    private CinemaFragmentAdapter adapter ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mainActivity, R.layout.fragment_cinema, null);
        View cardView = View.inflate(mainActivity, R.layout.cinema_head, null);

        tvAll = (TextView) view.findViewById(R.id.tv_all);
        tvOdds = (TextView) view.findViewById(R.id.tv_odds);
        tvNearby = (TextView) view.findViewById(R.id.tv_nearby);
        tvScreen = (TextView) view.findViewById(R.id.tv_screen);
        lvCinema = (ListView) view.findViewById(R.id.lv_cinema);
        ivHeadcard = (ImageView) cardView.findViewById(R.id.iv_headcard);
        llRoot = (LinearLayout) view.findViewById(R.id.ll_cinema_root);
        loadingView = (LoadingView) view.findViewById(R.id.loading_cinema);

        lvCinema.addHeaderView(cardView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getDataFromNet();
    }

    /**
     * 从网络获取数据
     */
    private void getDataFromNet() {
        RequestParams params = new RequestParams(UrlConstants.CINEMA_URL);
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
     * 解析数据并显示
     * @param result
     */
    private void parseData(String result) {
        CinemaFragmentBean cinemaFragmentBean = manualprocessData1(result);
        data = cinemaFragmentBean.data;
        loadingView.setVisibility(View.GONE);
        llRoot.setVisibility(View.VISIBLE);

        setadapter();
    }

    private void setadapter() {
        adapter = new CinemaFragmentAdapter(mainActivity,data);
        lvCinema.setAdapter(adapter);

    }

    /**
     * 手动解析Json数据
     *
     * @param result
     */
    private  CinemaFragmentBean manualprocessData1 (String result) {
        CinemaFragmentBean cinemaFragmentBean = new CinemaFragmentBean();
        try {
            JSONArray jsonArray = new JSONArray(result);

            if (jsonArray != null) {
                List<CinemaFragmentBean.CinemaListData> data = new ArrayList<CinemaFragmentBean.CinemaListData>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    CinemaFragmentBean.CinemaListData cinemaListData = cinemaFragmentBean.new CinemaListData();
                    data.add(cinemaListData);
                    JSONObject jsonObject = jsonArray.optJSONObject(i);

                    cinemaListData.isETicket = jsonObject.optBoolean("isETicket");
                    cinemaListData.isETicket = jsonObject.optBoolean("isTicket");
                    cinemaListData.cinemaId = jsonObject.optInt("cinemaId");
                    cinemaListData.districtID = jsonObject.optInt("districtID");
                    cinemaListData.minPrice = jsonObject.optInt("minPrice");
                    cinemaListData.movieCount = jsonObject.optInt("movieCount");
                    cinemaListData.ratingFinal = jsonObject.optInt("ratingFinal");
                    cinemaListData.showtimeCount = jsonObject.optInt("showtimeCount");
                    cinemaListData.address = jsonObject.optString("address");
                    cinemaListData.cinameName = jsonObject.optString("cinameName");
                    cinemaListData.baiduLatitude = jsonObject.optDouble("baiduLatitude");
                    cinemaListData.baiduLatitude = jsonObject.optDouble("baiduLongitude");
                    cinemaListData.baiduLatitude = jsonObject.optDouble("latitude");
                    cinemaListData.baiduLatitude = jsonObject.optDouble("longitude");


                    JSONObject jsonObject1 = jsonObject.optJSONObject("feature");
                    if (jsonObject1 != null) {
                        CinemaFragmentBean.CinemaListData.Feattue feattue = cinemaListData.new Feattue();
                        feattue.has3D = jsonObject1.optInt("has3D");
                        feattue.hasFeature4D = jsonObject1.optInt("hasFeature4D");
                        feattue.hasFeature4K = jsonObject1.optInt("hasFeature4K");
                        feattue.hasFeatureDolby = jsonObject1.optInt("hasFeatureDolby");
                        feattue.hasFeatureHuge = jsonObject1.optInt("hasFeatureHuge");
                        feattue.hasIMAX = jsonObject1.optInt("hasIMAX");
                        feattue.hasLoveseat = jsonObject1.optInt("hasLoveseat");
                        feattue.hasPark = jsonObject1.optInt("hasPark");
                        feattue.hasServiceTicket = jsonObject1.optInt("hasServiceTicket");
                        feattue.hasVIP = jsonObject1.optInt("hasVIP");
                        feattue.hasWifi = jsonObject1.optInt("hasWifi");

                        cinemaListData.feature = feattue;
                    }

                    JSONArray jsonArray1 = jsonObject.optJSONArray("couponActivityList");
                    if (jsonArray1 != null) {
                        List<CinemaFragmentBean.CinemaListData.CouponActivityList> couponActivityList = new ArrayList<CinemaFragmentBean.CinemaListData.CouponActivityList>();
                        for (int j = 0; j < jsonArray1.length(); j++) {
                            CinemaFragmentBean.CinemaListData.CouponActivityList couponActivity = cinemaListData.new CouponActivityList();
                            JSONObject jsonObject2 = jsonArray1.optJSONObject(j);
                            couponActivity.desc = jsonObject2.optString("desc");
                            couponActivity.isSelected = jsonObject2.optBoolean("isSelected");
                            couponActivity.tag = jsonObject2.optString("tag");
                            couponActivity.url = jsonObject2.optString("url");
                            couponActivity.id = jsonObject2.optInt("id");

                            couponActivityList.add(couponActivity);
                        }
                        cinemaListData.couponActivityList = couponActivityList;
                    }
                    cinemaFragmentBean.data = data;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cinemaFragmentBean;

    }

}
