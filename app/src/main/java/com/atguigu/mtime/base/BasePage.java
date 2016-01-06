package com.atguigu.mtime.base;

import android.content.Context;
import android.view.View;

/**
 * 页面的基类，所有页面都需要继承该类并实现initView方法加载布局
 * Created by HanFeng on 2015/12/6.
 */
public abstract class BasePage {

    /**
     * 根布局View
     */
    public View rootView;
    protected Context context;

    public BasePage(Context context) {
        this.context = context;
        View view = initView();
        if (view != null) {
            rootView = view;
        }
    }

    /**
     * 子类重写加载布局
     * @return
     */
    public abstract View initView();

    /**
     * 加载数据，需要的话重写
     */
    public void initData() {

    }
}
