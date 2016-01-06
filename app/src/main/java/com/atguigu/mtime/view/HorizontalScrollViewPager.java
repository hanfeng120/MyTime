package com.atguigu.mtime.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 水平滑动的Viewpager
 * Created by yui-pc on 2015/12/9.
 */
public class HorizontalScrollViewPager extends ViewPager {
    public HorizontalScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalScrollViewPager(Context context) {
        super(context);
    }

    /**
     * 记录起始坐标
     */
    private float startX;
    private float startY;

    /**
     * 1.竖直方向滑动--不处理 getParent().requestDisallowInterceptTouchEvent(false);
     * <p/>
     * 2.水平方向滑动 2.1当水平方向滑动，并且滑动的页面是第0个的时候，并且滑动的方向是左到右（endX - startX>0）
     * getParent().requestDisallowInterceptTouchEvent(false);
     * 2.2当水平方向滑动，并且滑动的页面是最后一个的时候，并且滑动的方向是右到左（endX - startX <0）
     * getParent().requestDisallowInterceptTouchEvent(false);
     * <p/>
     * 2.3当滑动的时候在中间页面的时候 getParent().requestDisallowInterceptTouchEvent(true);
     * <p/>
     * 在按下事件中 getParent().requestDisallowInterceptTouchEvent(true);
     * <p/>
     * 判断竖直方向滑动和水平方向滑动
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // 让父层View不拦截当前控件的事件
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                //告诉父view，我的事件自己处理
                getParent().requestDisallowInterceptTouchEvent(true);
                // 1.记录起始坐标
                startX = ev.getX();
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                // 2.来到新的坐标
                float endX = ev.getX();
                float endY = ev.getY();
                // 3.记录滑动偏移量
                float distanceX = endX - startX;//在水平方向滑动的偏移量
                float distanceY = endY - startY;//在垂直方向滑动的偏移量
                // 4.判断滑动方向
                if (Math.abs(distanceX) > Math.abs(distanceY)) {
                    //水平方向滑动
                    if (getCurrentItem() == 0 & distanceX > 0) {
                        // 2.1当水平方向滑动，并且滑动的页面是第0个的时候，并且滑动的方向是左到右（endX - startX>0）
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else if (getCurrentItem() == getAdapter().getCount() - 1 && distanceX < 0) {
                        // 2.2当水平方向滑动，并且滑动的页面是最后一个的时候，并且滑动的方向是右到左（endX - startX <0）
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        // 2.3当滑动的时候在中间页面的时候
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                } else {
                    //竖直方向滑动
                    getParent().requestDisallowInterceptTouchEvent(false);
                }

                break;
            case MotionEvent.ACTION_UP:

                break;
            default:

                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
