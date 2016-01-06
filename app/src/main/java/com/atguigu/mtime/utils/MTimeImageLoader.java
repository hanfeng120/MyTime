package com.atguigu.mtime.utils;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.atguigu.mtime.activity.MTimeApplication;

/**
 * Created by HanFeng on 2015/12/15.
 */
public class MTimeImageLoader {

    private ImageView imageView;
    private String imageUrl;
    private MTimeImageCache imageCache;

    public MTimeImageLoader() {
        imageCache = new MTimeImageCache();
    }

    /**
     * 普通加载图片
     * @param imageView
     * @param imageUrl
     */
    public void loadImage(final ImageView imageView, final String imageUrl) {
        Bitmap bitmap = imageCache.getBitmap(imageUrl);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            MTimeApplication.getRequestQueue().add(new ImageRequest(imageUrl, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap bitmap) {
                    imageCache.putBitmap(imageUrl, bitmap);
                    imageView.setImageBitmap(bitmap);
                }
            }, 0, 0, Bitmap.Config.ARGB_4444, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            }));
        }
    }

    /**
     * 指定图片参数加载图片
     * @param imageView
     * @param imageUrl
     * @param width
     * @param height
     * @param config
     */
    public void loadImage(final ImageView imageView, final String imageUrl, int width, int height, Bitmap.Config config) {
        Bitmap bitmap = imageCache.getBitmap(imageUrl);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            MTimeApplication.getRequestQueue().add(new ImageRequest(imageUrl, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap bitmap) {
                    imageCache.putBitmap(imageUrl, bitmap);
                    imageView.setImageBitmap(bitmap);
                }
            }, width, height, config, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            }));
        }
    }

    /**
     * 解决图片闪动加载方式
     * @param imageView
     * @param imageUrl
     * @param width
     * @param height
     */
    public void loadImageWithTag(final ImageView imageView, final String imageUrl, int width, int height) {
        Bitmap bitmap = imageCache.getBitmap(imageUrl);
        if (bitmap != null) {
            if (imageUrl == imageView.getTag()) {
                imageView.setImageBitmap(bitmap);
            }
        } else {
            MTimeApplication.getRequestQueue().add(new ImageRequest(imageUrl, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap bitmap) {
                    if (imageUrl == imageView.getTag()) {
                        imageCache.putBitmap(imageUrl, bitmap);
                        imageView.setImageBitmap(bitmap);
                        Log.i("TAG", imageUrl);
                    }
                }
            }, width, height, Bitmap.Config.ARGB_4444, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            }));
        }
    }

}
