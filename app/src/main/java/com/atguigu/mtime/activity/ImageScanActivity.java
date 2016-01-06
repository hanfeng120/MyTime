package com.atguigu.mtime.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.adapter.ImageScanPagerAdapter;
import com.atguigu.mtime.bean.ImageBean;

import java.util.ArrayList;

public class ImageScanActivity extends AppCompatActivity {

    private ImageView ivImagescanShare;
    private ViewPager vpImagescan;
    private ImageView ivImagescanSave;
    private TextView tvTitle;

    private ArrayList<ImageBean> data;
    private int currentPosition;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_scan);

        initView();
        getData();
        initData();
        setListener();

    }

    private void setListener() {
        vpImagescan.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvTitle.setText(position + "/" + total);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        tvTitle.setText(currentPosition + "/" + total);
        vpImagescan.setAdapter(new ImageScanPagerAdapter(this, data));
        vpImagescan.setCurrentItem(currentPosition);
    }

    private void getData() {
        data = getIntent().getParcelableArrayListExtra("data");
        currentPosition = getIntent().getIntExtra("position",0);
        total = data.size();
    }

    private void initView() {
        ivImagescanShare = (ImageView) findViewById(R.id.iv_imagescan_share);
        vpImagescan = (ViewPager) findViewById(R.id.iv_imagescan_content);
        ivImagescanSave = (ImageView) findViewById(R.id.iv_imagescan_save);
        tvTitle = (TextView) findViewById(R.id.tv_imagescan_title);
    }


}
