package com.atguigu.mtime.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.mtime.base.BasePage;

import java.util.ArrayList;

/**
 * 发现页面ViewPager的适配器
 *
 * Created by HanFeng on 2015/12/6.
 */
public class DiscoverPagerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<BasePage> data;

    public DiscoverPagerAdapter(Context context, ArrayList<BasePage> basePages) {
        this.context = context;
        this.data = basePages;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = data.get(position).rootView;
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
