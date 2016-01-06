package com.atguigu.mtime.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.mtime.base.BasePage;
import com.atguigu.mtime.bean.RangkingBean;

import java.util.List;

/**
 * Created by yiran on 2015/12/11.
 * 全球排行榜
 */

public class GoldleAdapter extends PagerAdapter {

    private List<BasePage> basePages;
    private List<RangkingBean.TopListData.SubTopList> subTopLists;

    public GoldleAdapter(List<BasePage> basePages, List<RangkingBean.TopListData.SubTopList> subTopLists) {
        this.basePages = basePages;
        this.subTopLists = subTopLists;
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
        BasePage basePage = basePages.get(position);
        View rootView = basePage.rootView;
        basePage.initData();
        container.addView(rootView);
        return rootView;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return subTopLists.get(position).title;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
