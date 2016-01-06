package com.atguigu.mtime.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.mtime.base.BasePage;

import java.util.ArrayList;

/**
 * Created by HanFeng on 2015/12/7.
 */
public class MoviePagerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<BasePage> basePages;
    private LayoutInflater inflater;

    public MoviePagerAdapter(Context context, ArrayList<BasePage> basePages) {
        this.context = context;
        this.basePages = basePages;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return basePages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = basePages.get(position).rootView;
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
