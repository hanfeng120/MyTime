package com.atguigu.mtime.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.atguigu.mtime.bean.ImageBean;

import org.xutils.x;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by HanFeng on 2015/12/12.
 */
public class ImageScanPagerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<ImageBean> data;

    public ImageScanPagerAdapter(Context context, ArrayList<ImageBean> data) {
        this.context = context;
        this.data = data;

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
        ViewPager.LayoutParams params = new ViewPager.LayoutParams();
        PhotoView imageView = new PhotoView(context);
        x.image().bind(imageView, data.get(position).image);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
