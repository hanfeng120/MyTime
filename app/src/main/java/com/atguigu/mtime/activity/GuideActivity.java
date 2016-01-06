package com.atguigu.mtime.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.adapter.GuideAdapter;
import com.atguigu.mtime.view.DrawableChangeView;
import com.atguigu.mtime.view.RotateDownPageTransformer;
import com.atguigu.mtime.view.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yiran on 2015/12/11.
 * 引导页面
 */
public class GuideActivity extends AppCompatActivity {
    private ViewPager vpGuide;
    private DrawableChangeView darwableView;

    private List<Drawable> drawables;
    private List<ImageView> imageViewList;
    private GuideAdapter adapter;
    private Button btnStartMain;


    /**
     * 图片数据
     *
     * @param savedInstanceState
     */
    private static final int[] mImageViews = new int[]{R.mipmap.lead_bg1, R.mipmap.lead_bg2, R.mipmap.lead_bg3, R.mipmap.lead_bg4,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
        initData();

    }


    private void initView() {
        vpGuide = (ViewPager) findViewById(R.id.viewpager);
        darwableView = (DrawableChangeView) this.findViewById(R.id.drawableChangeView);
        btnStartMain = (Button) findViewById(R.id.btn_start_main);

    }


    /**
     * 初始化页面
     */
    private void initData() {
        imageViewList = new ArrayList<ImageView>();
        drawables = new ArrayList<Drawable>();
        for (int i = 0; i < mImageViews.length; i++) {
            ImageView image = new ImageView(this);
            image.setBackgroundResource(mImageViews[i]);

            imageViewList.add(image);
            if (i % 2 == 0) {
                drawables.add(this.getResources().getDrawable(R.mipmap.tk_bg));
            } else {
                drawables.add(this.getResources().getDrawable(R.mipmap.lx_bg));

            }
        }

        setAdapter();
        vpGuide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//arg1 显示的view前一个view所占屏幕的比例
                //arg2 viewpager的总宽度
                System.out.println("position所占屏幕的比例" + positionOffset);
                darwableView.setPosition(position);
                darwableView.setDegree(positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == (imageViewList.size() - 1)) {//最后一个页面，2
                    //把按钮显示出来
                    btnStartMain.setVisibility(View.VISIBLE);
                } else {
                    //把按钮隐藏
                    btnStartMain.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        darwableView.setDrawables(drawables);

        btnStartMain.setOnClickListener(new MyOnClickListener());
        vpGuide.setPageTransformer(true, new RotateDownPageTransformer());

    }

    private void setAdapter() {
        adapter = new GuideAdapter(imageViewList);
        vpGuide.setAdapter(adapter);
    }


    class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            startActivity(new Intent(GuideActivity.this, MainActivity.class));
            finish();
        }
    }
}
