package com.atguigu.mtime.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.MainActivity;
import com.atguigu.mtime.adapter.MoviePagerAdapter;
import com.atguigu.mtime.base.BasePage;
import com.atguigu.mtime.base.implement.ShowingPage;
import com.atguigu.mtime.base.implement.SoonPage;
import com.atguigu.mtime.view.widget.SubTabView;

import java.util.ArrayList;

/**
 * 电影页面
 * Created by HanFeng on 2015/12/7.
 */
public class MovieFragment extends Fragment implements View.OnClickListener {

    private MainActivity mainActivity;
    private View rootView;
    private SubTabView stShowing, stSoon;
    private ViewPager viewPager;
    private MoviePagerAdapter adapter;
    private ArrayList<BasePage> basePages;
    private ShowingPage showingPage;
    private SoonPage soonPage;
    private Bundle bundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_movie, null);
        stShowing = (SubTabView) rootView.findViewById(R.id.st_movie_showing);
        stSoon = (SubTabView) rootView.findViewById(R.id.st_movie_sooning);
        viewPager = (ViewPager) rootView.findViewById(R.id.vp_fragment_movie);


        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListener();
        initData();
        setAdapter();
    }

    /**
     * 设置监听器
     */
    private void setListener() {
        stShowing.setOnClickListener(this);
        stSoon.setOnClickListener(this);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setSubTab(position);
                basePages.get(position).initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        basePages = new ArrayList<>();
        showingPage = new ShowingPage(mainActivity);
        soonPage = new SoonPage(mainActivity);
        basePages.add(showingPage);
        basePages.add(soonPage);
    }

    /**
     * 设置适配器
     */
    private void setAdapter() {
        adapter = new MoviePagerAdapter(mainActivity, basePages);
        viewPager.setAdapter(adapter);
        bundle = getArguments();
        if (bundle != null) {
            int tabIndex = bundle.getInt("tabIndex");
            if (tabIndex == 0 || tabIndex == 1) {
                viewPager.setCurrentItem(tabIndex);
            }
        } else {
            viewPager.setCurrentItem(0);
            basePages.get(0).initData();
        }
    }

    /**
     * 设置被选中展示的页面
     * @param index
     */
    public void setSelected(int index) {
        if (index == 0) {
            viewPager.setCurrentItem(0);
            setSubTab(0);
        }else if (index == 1) {
            viewPager.setCurrentItem(1);
            setSubTab(1);
        }
    }

    /**
     * 设置SubTab的选中状态
     * @param index
     */
    private void setSubTab(int index) {
        if (index == 0) {
            stShowing.setChecked(true);
            stSoon.setChecked(false);
        }else if (index == 1) {
            stShowing.setChecked(false);
            stSoon.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.st_movie_showing:
                setSelected(0);
                break;
            case R.id.st_movie_sooning:
                setSelected(1);
                break;
        }
    }
}
