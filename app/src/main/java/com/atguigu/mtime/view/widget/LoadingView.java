package com.atguigu.mtime.view.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.mtime.R;

/**
 * 自定义加载动画
 *
 * Created by HanFeng on 2015/12/7.
 */
public class LoadingView extends RelativeLayout {

    private ImageView ivIndicator;
    private LayoutInflater inflater;

    public LoadingView(Context context) {
        super(context);
        initView();
        inflater = LayoutInflater.from(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        inflater = LayoutInflater.from(context);
    }

    private void initView() {
        View.inflate(getContext(), R.layout.view_loading, this);

        ImageView imageView = (ImageView) findViewById(R.id.iv_loading_view);
        imageView.setImageResource(R.drawable.animation_list);
        AnimationDrawable rocketAnimation = (AnimationDrawable) imageView.getDrawable();
        rocketAnimation.start();
    }
}
