package com.atguigu.mtime.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 自定义的ScrollGridview
 * Created by yui-pc on 2015/12/9.
 */
public class ScrollGridview extends GridView{


    public ScrollGridview(Context context) {
        super(context);
    }
    public ScrollGridview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public ScrollGridview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
