package com.atguigu.mtime.view.widget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.mtime.R;

import org.xutils.image.ImageOptions;

/**
 * Created by HanFeng on 2015/12/10.
 */
public class GalleryItemViewGroup extends RelativeLayout {

    private TextView textView;
    private ImageView imageView;
    private View rootView;

    private Context context;

    public GalleryItemViewGroup(Context context) {
        super(context);
        this.context = context;
        initView();

    }

    private void initView() {
        rootView = View.inflate(context, R.layout.item_gallery_home, this);
        imageView = (ImageView) rootView.findViewById(R.id.iv_item_gallery);
    }

    private TextView getTextView() {
        return textView;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
