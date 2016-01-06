package com.atguigu.mtime.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.MainActivity;
import com.atguigu.mtime.bean.MallHomeBean;

import org.xutils.x;

import java.util.ArrayList;

/**
 * viewpager
 * Created by yui-pc on 2015/12/10.
 */
public class MallPagerAdapter extends PagerAdapter{
    private MainActivity mainActivity;
    private ArrayList<MallHomeBean.ScrollImgBean> scrollImgData;
    private LayoutInflater inflater;

    public MallPagerAdapter(MainActivity mainActivity, ArrayList<MallHomeBean.ScrollImgBean> scrollImgData) {
        this.mainActivity = mainActivity;
        this.scrollImgData = scrollImgData;

        inflater = LayoutInflater.from(mainActivity);
    }

    @Override
    public int getCount() {
        return 5000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % scrollImgData.size();
        ImageView imageView = (ImageView) inflater.inflate(R.layout.item_home_pager_adapter,null);
        Log.e("tag","scrollviewpager");
        x.image().bind(imageView,scrollImgData.get(position).image);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
