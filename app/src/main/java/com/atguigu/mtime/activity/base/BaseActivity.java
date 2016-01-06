package com.atguigu.mtime.activity.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mtime.R;

/**
 * Activity的基类，所有的Activity都需要继承该类
 * created by 赵迅艺 at 2015/12/7
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBack, ivFavorite, ivShare;
    private TextView tvTitle;
    private FrameLayout flContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initView();
        setListener();
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_title_bar_normal_back);
        ivFavorite = (ImageView) findViewById(R.id.iv_title_bar_normal_favorite);
        ivShare = (ImageView) findViewById(R.id.iv_title_bar_normal_share);
        tvTitle = (TextView) findViewById(R.id.tv_title_bar_normal_title);
        flContent = (FrameLayout) findViewById(R.id.fl_base_content);

        //加载子类的布局
        View view = getView();
        if (view != null) {
            flContent.addView(view);
        }

    }

    /**
     * 设置监听器
     */
    private void setListener() {
        ivBack.setOnClickListener(this);
        ivFavorite.setOnClickListener(this);
        ivShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_bar_normal_back:
                clickBack();
                break;
            case R.id.iv_title_bar_normal_favorite:
                clickFavorite();
                break;
            case R.id.iv_title_bar_normal_share:
                clickShare();
                break;
        }
    }

    /**
     * 加载布局
     *
     * @return 返回当前Activity的布局
     */
    protected abstract View getView();

    /**
     * 返回按钮的点击事件
     */
    protected abstract void clickBack();

    /**
     * 收藏按钮的点击事件
     */
    protected abstract void clickFavorite();

    /**
     * 分享按钮的点击事件
     */
    protected abstract void clickShare();

    /**
     * 设置标题
     */
    protected void setTitle(String title) {
        tvTitle.setText(title);
    }

    /**
     * 设置收藏按钮是否可见
     *
     * @param visiable
     */
    protected void setFavoriteVisiable(int visiable) {
        ivFavorite.setVisibility(visiable);
    }

    /**
     * 设置分享按钮是否可见
     *
     * @param visiable
     */
    protected void setShareVisiable(int visiable) {
        ivShare.setVisibility(visiable);
    }
}
