package com.atguigu.mtime.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.atguigu.mtime.R;

/**
 * 自定义刷新，往上和往下刷新
 * <p/>
 * Created by 申瑞芹 on 2015/12/11.
 */
public class RefreshListView extends ListView {
    private Context context;

    /**
     * 整个头部布局
     */
    private LinearLayout headerView;

    /**
     * 整个底部布局
     */
    private LinearLayout footerView;

    /**
     * 下拉刷新控件的高
     */
    private int pullDownRefreshHeight;

    /**
     * 上拉刷新空间的高
     */
    private int upwardPullRefreshHeight;

    /**
     * 记录下拉刷新的起始坐标
     */
    private float pullDownStartY = 0;

    /**
     * 记录上拉刷新的起始坐标
     */
    private float upwardPullStartY = 0;

    /**
     * 下拉刷新控件的视图
     */
    private View pullDownRefresh;

    /**
     * 上拉刷新控件的视图
     */
    private View upwardPullRefresh;

    /**
     * ListView在屏幕上Y轴的坐标
     */
    private int listViewOnScreenY = 0;


    /**
     * ] 顶部图片
     */
    private View topnews;

    /**
     * 底部图片
     */
    private View footernews;


    /**
     * 下拉刷新
     */
    private static final int PULL_DOWN_REFRESH = 0;


    /**
     * 上拉刷新
     */
    private static final int Upward_Pull_REFRESH = 20;

    /**
     * 顶部手松刷新
     */
    private static final int TOP_RELEASE_REFRESH = 1;

    /**
     * 底部手松刷新
     */
    private static final int Footer_RELEASE_REFRESH = 21;


    /**
     * 顶部正在刷新
     */
    private static final int TOP_REFRESHING = 2;

    /**
     * 底部正在刷新
     */
    private static final int FOOTER_REFRESH = 32;

    private static final String TAG = RefreshListView.class.getSimpleName();

    /**
     * 顶部刷新状态
     */
    private int TopCurrenStatus = PULL_DOWN_REFRESH;

    /**
     * 底部刷新状态
     */
    private int FooterCurrenStatus = Upward_Pull_REFRESH;


    /**
     * 加载更多的高度
     */
    private int footerViewHeight;

    /**
     *
     */
    private OnRefreshListener onRefreshListener;


    /**
     * 监听下拉刷新，并且请求网络
     */
    public interface OnRefreshListener {

        /**
         * 当下拉刷新的刷新的时候，回调这个方法
         * 在这个方法里面写请求网络更新数据的代码
         */
        void onPullDwonRrefresh();// 监听联网请求

        void onUpwardPullRefresh();
    }


    /**
     * 设置下拉刷新的监听
     */
    public void setOnRefreshListener(OnRefreshListener l) {
        onRefreshListener = l;
    }


    private TextView tv_head_refredsh_title;//头部刷新的标题
    private TextView tv_head_refredsh_summary;//
    private TextView tv_head_refresh_status;
    private ProgressBar pb_progressbar;
    private ImageView iv_head_refresh_arrow;
    private Animation upAnim;
    private Animation downAnim;


    private ImageView iv_footer_refresh_arrow;//上拉刷新的图片
    private ProgressBar pb_footer_refresh_progressbar;
    private TextView tv_footer_refresh_status;


    /**
     * 在布局文件中定义，当实例化的时候一定用到的构造方法，如果该方法不写 出现崩溃
     * ，所以一定要写，只要在布局中用就要写这个方法
     *
     * @param context
     * @param attrs
     */
    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        // 初始化下拉刷新布局，并且把下拉刷新布局添加到ListView的头部 - addHeaderView();
        initHeaderView(context);

        initFooterView(context);

        initTopAnimation(context);
//
//        initFooterAnimation(context);
    }

    /**
     * 设置底部动画
     */
    private void initFooterAnimation(Context context2) {

        upAnim = new RotateAnimation(0, -180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);

        upAnim.setDuration(500);
        upAnim.setFillAfter(true);

        downAnim = new RotateAnimation(-180, -360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);

        downAnim.setDuration(500);
        downAnim.setFillAfter(true);
    }

    /**
     * 设置顶部动画
     */
    private void initTopAnimation(Context context2) {

        upAnim = new RotateAnimation(0, -180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);

        upAnim.setDuration(500);
        upAnim.setFillAfter(true);

        downAnim = new RotateAnimation(-180, -360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);

        downAnim.setDuration(500);
        downAnim.setFillAfter(true);
    }


    /**
     * 初始化尾
     *
     * @param context
     */
    private void initFooterView(Context context) {
        footerView = (LinearLayout) View.inflate(context,
                R.layout.listview_footer_refresh, null);
        upwardPullRefresh = footerView.findViewById(R.id.ll_upward_pull);
        upwardPullRefresh.measure(0, 0);// 让系统测量控件的高度

        // 得到下拉刷新控件的高
        upwardPullRefreshHeight = upwardPullRefresh.getMeasuredHeight();

        //初始化对应的
        iv_footer_refresh_arrow = (ImageView) footerView.findViewById(R.id.iv_footer_refresh_arrow);
        pb_footer_refresh_progressbar = (ProgressBar) footerView
                .findViewById(R.id.pb_footer_refresh_progressbar);
        tv_footer_refresh_status = (TextView) footerView.findViewById(R.id.tv_footer_refresh_status);


        upwardPullRefresh.setPadding(0, 0, 0, -upwardPullRefreshHeight);

        //添加到尾
        RefreshListView.this.addFooterView(footerView);


//        getLastVisiblePosition() == getAdapter().getCount()- 1;

    }

    /**
     * 初始化头
     *
     * @param context
     */
    private void initHeaderView(Context context) {
        headerView = (LinearLayout) View.inflate(context,
                R.layout.listview_head_refresh, null);
        pullDownRefresh = headerView.findViewById(R.id.ll_pull_donw_refresh);
        pullDownRefresh.measure(0, 0);// 让系统测量控件的高度

        // 得到下拉刷新控件的高
        pullDownRefreshHeight = pullDownRefresh.getMeasuredHeight();

        iv_head_refresh_arrow = (ImageView) headerView.findViewById(R.id.iv_head_refresh_arrow);
        pb_progressbar = (ProgressBar) headerView
                .findViewById(R.id.pb_progressbar);
        tv_head_refredsh_title = (TextView) headerView.findViewById(R.id.tv_head_refredsh_title);
        tv_head_refresh_status = (TextView) headerView.findViewById(R.id.tv_head_refresh_status);
        tv_head_refredsh_summary = (TextView) headerView.findViewById(R.id.tv_head_refredsh_summary);

        /**
         *
         *
         * view.setPading(0,-控件高,0,0);//完全隐藏状态 view.setPading(0,0,0,0);//完全显示
         * view.setPading(0,控件高,0,0);//完全两倍高方式显示 下拉刷新的原理 int pading = -控件高 +
         * (endY - pullDownStartY); view.setPading(0,pading,0,0);
         */

        pullDownRefresh.setPadding(0, -pullDownRefreshHeight, 0, 0);

        // 添加到头
        RefreshListView.this.addHeaderView(headerView);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:// 手指在屏幕上按下
                // 1.记录下拉刷新的起始坐标
                pullDownStartY = ev.getY();
                //2.记录上拉刷新的起始坐标
                upwardPullStartY = ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:// 手指在屏幕滑动

                //底部 上拉刷新
                if (getLastVisiblePosition() == getAdapter().getCount() - 1) {
                    if (pullDownStartY == 0) {
                        pullDownStartY = ev.getY();
                    }
                    // 2.来到新的坐标
                    float endY = ev.getY();

                    // 正在刷新就不处理
                    if (FooterCurrenStatus == FOOTER_REFRESH) {
                        break;
                    }

                    float dinstanceY = endY - pullDownStartY;// 向下拉肯定小于0
                    if (dinstanceY < 0) {
                        // int pading = -控件高 + (endY - pullDownStartY);

                        float pading = -upwardPullRefreshHeight + dinstanceY / 2;

                        if (pading < 0 && FooterCurrenStatus != Footer_RELEASE_REFRESH) {
                            // 状态设置为手松刷新
                            FooterCurrenStatus = Footer_RELEASE_REFRESH;
                            refreshFooterStatus();
                            Log.e(TAG, "手松刷新........");
                        } else if (pading > 0 && FooterCurrenStatus != Upward_Pull_REFRESH) {
                            // 上拉刷新
                            TopCurrenStatus = Upward_Pull_REFRESH;
                            refreshFooterStatus();
                            Log.e(TAG, "上拉刷新........");

                        }

                        upwardPullRefresh.setPadding(0, 0, 0, (int) pading);

                        return true;
                    }

                    // pullDownStartY = ev.getY();//不要加这个重新赋值
                    break;


                }


                //顶部上拉刷新

                if (pullDownStartY == 0) {
                    pullDownStartY = ev.getY();
                }

                // 2.来到新的坐标
                float endY = ev.getY();

                // 正在刷新就不处理
                if (TopCurrenStatus == TOP_REFRESHING) {
                    break;
                }

                // 如果图没有完全显示，没有必要显示动态显示下拉刷新控件
                boolean isDisplaySecondView = isDisplaySecondView();
                if (!isDisplaySecondView) {
                    break;
                }
                // break;
                // 当图完全显示，我们就要显示下拉控件

                // 3.计算偏移量
                float dinstanceY = endY - pullDownStartY;// 向下拉肯定大于0
                if (dinstanceY > 0) {
                    // int pading = -控件高 + (endY - pullDownStartY);

                    float pading = -pullDownRefreshHeight + dinstanceY / 2;

                    if (pading > 0 && TopCurrenStatus != TOP_RELEASE_REFRESH) {
                        // 状态设置为手势刷新
                        TopCurrenStatus = TOP_RELEASE_REFRESH;
                        refreshHeaderStatus();
                        Log.e(TAG, "手松刷新........");
                    } else if (pading < 0 && TopCurrenStatus != PULL_DOWN_REFRESH) {
                        // 下拉刷新
                        TopCurrenStatus = PULL_DOWN_REFRESH;
                        refreshHeaderStatus();
                        Log.e(TAG, "下拉刷新........");

                    }

                    pullDownRefresh.setPadding(0, (int) pading, 0, 0);

                    return true;
                }

                // pullDownStartY = ev.getY();//不要加这个重新赋值
                break;

            case MotionEvent.ACTION_UP:// 手指在屏幕滑动
                pullDownStartY = 0;
                if (TopCurrenStatus == PULL_DOWN_REFRESH) {
                    //把下拉刷新控件隐藏
                    pullDownRefresh.setPadding(0, -pullDownRefreshHeight, 0, 0);//还原初始值
                } else if (TopCurrenStatus == TOP_RELEASE_REFRESH) {
                    //切换成正在刷新
                    TopCurrenStatus = TOP_REFRESHING;
                    refreshHeaderStatus();
                    //完整显示下拉刷新控件
                    pullDownRefresh.setPadding(0, 0, 0, 0);

                    //联网请求-接口

                    onRefreshListener.onPullDwonRrefresh();

                }

                break;

            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 更新状态
     */
    private void refreshHeaderStatus() {
        switch (TopCurrenStatus) {
            case PULL_DOWN_REFRESH:// 下拉刷新
                iv_head_refresh_arrow.startAnimation(downAnim);
                tv_head_refresh_status.setText("上拉刷新...");
                break;

            case TOP_RELEASE_REFRESH:// 手松刷新
                iv_head_refresh_arrow.startAnimation(upAnim);
                tv_head_refresh_status.setText("手松刷新...");
                break;

            case TOP_REFRESHING:// 正在刷新
                iv_head_refresh_arrow.clearAnimation();
                iv_head_refresh_arrow.setVisibility(View.GONE);
                pb_progressbar.setVisibility(View.VISIBLE);
                tv_head_refresh_status.setText("正在刷新...");

                break;


            default:
                break;
        }

    }


    /**
     * 更新底部状态
     */
    private void refreshFooterStatus() {
        switch (FooterCurrenStatus) {
            case Upward_Pull_REFRESH:// 上拉刷新
                iv_footer_refresh_arrow.startAnimation(downAnim);
                tv_footer_refresh_status.setText("下拉刷新...");

                break;

            case Footer_RELEASE_REFRESH:// 手松刷新
                iv_footer_refresh_arrow.startAnimation(upAnim);
                tv_footer_refresh_status.setText("手松刷新...");
                break;

            case FOOTER_REFRESH:// 正在刷新
                iv_footer_refresh_arrow.clearAnimation();
                iv_head_refresh_arrow.setVisibility(View.GONE);
                pb_footer_refresh_progressbar.setVisibility(View.VISIBLE);
                tv_footer_refresh_status.setText("正在刷新...");
                break;

            default:
                break;
        }

    }

    /**
     * 判断图是否完全显示 当ListView在屏幕上的Y轴坐标小于或者等于顶部轮播图在屏幕上的Y轴坐标的时候， 顶部轮播图就是出于完全显示的状态
     *
     * @return
     */
    private boolean isDisplaySecondView() {
        int[] location = new int[2];
        // 得到ListView在屏幕上的Y轴坐标
        if (listViewOnScreenY == 0) {

            this.getLocationOnScreen(location);
            listViewOnScreenY = location[1];// 坐标不变
        }
        // 得到顶部图在屏幕上的Y轴坐标

        topnews.getLocationOnScreen(location);
        int topnewsOnscreenY = location[1];

        // if(listViewOnScreenY <= topnewsOnscreenY){
        // return true;
        // }else{
        // return false;
        // }

        return listViewOnScreenY <= topnewsOnscreenY;
    }

    /**
     * 传入顶部的图片
     *
     * @param topnews
     */
    public void addSecondView(View topnews) {
        this.topnews = topnews;

        // 添加顶部图，让下拉刷新和顶部图合为一个整体
        headerView.addView(topnews);
    }

    /**
     * 上拉刷新的次数
     */
    int i = 0;
    int topRefreshCount = 0;

    /**
     * 当请求成功或者失败状态返回
     *
     * @param sucess
     */
    public void onRefreshFinish(boolean sucess) {

        TopCurrenStatus = PULL_DOWN_REFRESH;
        iv_head_refresh_arrow.clearAnimation();
        iv_head_refresh_arrow.setVisibility(View.VISIBLE);
        pb_progressbar.setVisibility(View.GONE);
        tv_head_refresh_status.setText("下拉刷新...");

        //下拉刷新控件也隐藏
        pullDownRefresh.setPadding(0, -pullDownRefreshHeight, 0, 0);

        topRefreshCount = i++;//下拉刷新的次数
        if (sucess) {
            /**
             * 测试
             */
            tv_head_refredsh_title.setText("标题" + topRefreshCount);
            tv_head_refredsh_summary.setText("描述" + topRefreshCount);
        }
    }
}
