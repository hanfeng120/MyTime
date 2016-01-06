package com.atguigu.mtime.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.base.BasePage;
import com.atguigu.mtime.bean.HomeBean;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

/**
 * 首页
 * Created by HanFeng on 2015/12/7.
 */
public class HomePagerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<HomeBean.HomeAdvBean> basePages;
    private LayoutInflater inflater;

    public HomePagerAdapter(Context context, ArrayList<HomeBean.HomeAdvBean> basePages) {
        this.context = context;
        this.basePages = basePages;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (basePages.size() > 1) {
            return 1000;
        } else {
            return basePages.size();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % basePages.size();
        ImageView imageView = (ImageView) inflater.inflate(R.layout.item_home_pager_adapter, null);
        ImageOptions.Builder builder = new ImageOptions.Builder();
        builder.setImageScaleType(ImageView.ScaleType.FIT_XY);
        ImageOptions options = builder.build();
        x.image().bind(imageView, basePages.get(position).img, options);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
