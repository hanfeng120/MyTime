package com.atguigu.mtime.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.MTimeApplication;
import com.atguigu.mtime.bean.HomeMovieBean;
import com.atguigu.mtime.utils.DensityUtil;
import com.atguigu.mtime.utils.MTimeImageCache;
import com.atguigu.mtime.view.widget.GalleryItemViewGroup;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

import at.technikum.mti.fancycoverflow.FancyCoverFlow;
import at.technikum.mti.fancycoverflow.FancyCoverFlowAdapter;

/**
 * Created by HanFeng on 2015/12/9.
 */
public class HomeGalleryAdapter extends FancyCoverFlowAdapter {

    private Context context;
    private ArrayList<HomeMovieBean.HomeMoviesBean> data;
    private ArrayList<Bitmap> bitmaps;
    private LayoutInflater inflater;

    public HomeGalleryAdapter(Context context, ArrayList<HomeMovieBean.HomeMoviesBean> data, ArrayList<Bitmap> bitmaps) {
        this.context = context;
        this.data = data;
        this.bitmaps = bitmaps;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public HomeMovieBean.HomeMoviesBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getCoverFlowItem(int position, View reuseableView, ViewGroup viewGroup) {
        GalleryItemViewGroup customViewGroup = null;

        if (reuseableView != null) {
            customViewGroup = (GalleryItemViewGroup) reuseableView;
        } else {
            customViewGroup = new GalleryItemViewGroup(viewGroup.getContext());
            customViewGroup.setLayoutParams(new FancyCoverFlow.LayoutParams(DensityUtil.dip2px(context, 130), DensityUtil.dip2px(context, 180)));
        }

//        final ImageView imageView = customViewGroup.getImageView();
//        imageView.setTag(position);
//
//        MTimeApplication.getRequestQueue().add(new ImageRequest(data.get(position).img, new Response.Listener<Bitmap>() {
//            @Override
//            public void onResponse(Bitmap bitmap) {
//                imageView.setImageBitmap(bitmap);
//                bitmaps.add(bitmap);
//            }
//        }, 0, 0, Bitmap.Config.ARGB_4444, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//            }
//        }));

        ImageLoader imageLoader = new ImageLoader(MTimeApplication.getRequestQueue(), new MTimeImageCache());
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(customViewGroup.getImageView(), R.mipmap.img_default_90x90, R.mipmap.loading_failed);
        imageLoader.get(data.get(position).img, imageListener);
        return customViewGroup;
    }

}
