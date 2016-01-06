package com.atguigu.mtime.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.MainActivity;
import com.atguigu.mtime.adapter.DiscoverPagerAdapter;
import com.atguigu.mtime.base.BasePage;
import com.atguigu.mtime.base.implement.ChartsPage;
import com.atguigu.mtime.base.implement.NewsPage;
import com.atguigu.mtime.base.implement.PrevuePage;
import com.atguigu.mtime.base.implement.ReviewPage;

import java.util.ArrayList;

/**
 * 发现Tab页面
 * Created by HanFeng on 2015/12/6.
 */
public class DiscoverFragment extends Fragment implements View.OnClickListener {

    private MainActivity mainActivity;
    private ViewPager viewPager;
    private TextView indicator;
    private LinearLayout llIndicator;
    private LinearLayout llTab;
    private TextView tvNews, tvPrevue, tvCharts, tvReview;

    private PagerAdapter adapter;
    private NewsPage newsPage;
    private PrevuePage prevuePage;
    private ChartsPage chartsPage;
    private ReviewPage reviewPage;
    private Bundle bundle;
    private int tvWidth;

    private ArrayList<BasePage> basePages;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, null);
        viewPager = (ViewPager) view.findViewById(R.id.vp_discover);
        indicator = (TextView) view.findViewById(R.id.view_discover_indicator);
        llIndicator = (LinearLayout) view.findViewById(R.id.ll_discover_indicator);
        llTab = (LinearLayout) view.findViewById(R.id.ll_discover_tab);
        tvNews = (TextView) view.findViewById(R.id.tv_discover_news);
        tvPrevue = (TextView) view.findViewById(R.id.tv_discover_prevue);
        tvCharts = (TextView) view.findViewById(R.id.tv_discover_charts);
        tvReview = (TextView) view.findViewById(R.id.tv_discover_review);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListener();
        initData();
        setAdapter();
    }

    private void initData() {
        basePages = new ArrayList<>();
        newsPage = new NewsPage(mainActivity);
        prevuePage = new PrevuePage(mainActivity);
        chartsPage = new ChartsPage(mainActivity);
        reviewPage = new ReviewPage(mainActivity);
        basePages.add(newsPage);
        basePages.add(prevuePage);
        basePages.add(chartsPage);
        basePages.add(reviewPage);
    }

    /**
     * 设置适配器
     */
    public void setAdapter() {
        adapter = new DiscoverPagerAdapter(mainActivity, basePages);
        viewPager.setAdapter(adapter);

        //获取动态传递过来的数据
        bundle = getArguments();
        if (bundle != null) {
            int index = bundle.getInt("index");
            viewPager.setCurrentItem(index);
        } else {
            basePages.get(0).initData();
        }
    }

    /**
     * 设置监听器
     */
    private void setListener() {

        llTab.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llIndicator.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                tvWidth = llTab.getChildAt(0).getWidth();

                indicator.setWidth(tvWidth);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) indicator.getLayoutParams();
                params.leftMargin = 0;
                indicator.setLayoutParams(params);

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setIndicator(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                basePages.get(position).initData();
                setTabState(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tvNews.setOnClickListener(this);
        tvPrevue.setOnClickListener(this);
        tvCharts.setOnClickListener(this);
        tvReview.setOnClickListener(this);

    }

    private void setIndicator(int position, float positionOffset) {
        //  移动的距离 ： tvWidth = positionOffset
        //  移动的距离 = positionOffset * tvWidth

        int leftMargin = (int) (tvWidth * positionOffset) + position * tvWidth;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) indicator.getLayoutParams();
        params.leftMargin = leftMargin;
        indicator.setLayoutParams(params);
    }

    /**
     * 设置被选中展示的页
     *
     * @param index 展示的页码
     */
    public void setSelected(int index) {
        if (index != -1) {
            viewPager.setCurrentItem(index);
        }
    }

    /**
     * 设置四个Tab文本的样式
     */
    private void setTabState(int index) {
        tvNews.setEnabled(true);
        tvPrevue.setEnabled(true);
        tvCharts.setEnabled(true);
        tvReview.setEnabled(true);
        setIndicator(index, 0);

        switch (index) {
            case 0:
                tvNews.setEnabled(false);
                break;
            case 1:
                tvPrevue.setEnabled(false);
                break;
            case 2:
                tvCharts.setEnabled(false);
                break;
            case 3:
                tvReview.setEnabled(false);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_discover_news:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv_discover_prevue:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv_discover_charts:
                viewPager.setCurrentItem(2);
                break;
            case R.id.tv_discover_review:
                viewPager.setCurrentItem(3);
                break;
        }
    }
}
