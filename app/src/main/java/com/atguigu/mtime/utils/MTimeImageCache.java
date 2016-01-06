package com.atguigu.mtime.utils;


import android.graphics.Bitmap;

import com.android.volley.toolbox.ImageLoader;

import org.xutils.cache.LruCache;

/**
 * Created by HanFeng on 2015/12/14.
 */
public class MTimeImageCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> lruCache;
    private int max = 10 * 1024 * 1024;

    public MTimeImageCache() {
        lruCache = new LruCache<String, Bitmap>(max) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String s) {
        return null;
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {

    }
}
