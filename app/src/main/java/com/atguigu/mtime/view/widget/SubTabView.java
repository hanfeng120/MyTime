package com.atguigu.mtime.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.mtime.R;

/**
 * 自定义Tab，用在图片浏览ImageScanActivity和MovieFragment
 *
 * Created by HanFeng on 2015/12/7.
 */
public class SubTabView extends RelativeLayout {

    private String nameSpace = "http://schemas.android.com/apk/com.atguigu.mtime";
    private String mTitle;
    private boolean isChecked;

    private TextView tvTitle;
    private ImageView ivIndicator;

    public SubTabView(Context context) {
        super(context);
        initView();
    }

    public SubTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTitle = attrs.getAttributeValue(nameSpace, "title");
        isChecked = attrs.getAttributeBooleanValue(nameSpace, "checked", false);
        initView();
    }

    private void initView() {
        View.inflate(getContext(), R.layout.layout_sub_tab, this);
        tvTitle = (TextView) findViewById(R.id.tv_sub_tab_title);
        ivIndicator = (ImageView) findViewById(R.id.iv_sub_tab_indicator);

        tvTitle.setText(mTitle);
        setChecked(isChecked);
    }

    public void setPadding(int left, int top, int right, int bottom) {
        tvTitle.setPadding(left, top, right, bottom);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setChecked(boolean isChecked) {
        if (isChecked) {
            tvTitle.setEnabled(false);
            ivIndicator.setBackgroundResource(R.color.text_subtab_blue);
        } else {
            tvTitle.setEnabled(true);
            ivIndicator.setBackgroundResource(R.color.color_sub_tab_bg);
        }
    }

}
