package com.atguigu.mtime.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.base.BaseActivity;
import com.atguigu.mtime.adapter.VideoActBaseAdapter;
import com.atguigu.mtime.bean.VideosBean;

import java.util.ArrayList;

public class VideoActivity extends BaseActivity {

    private ListView listView;
    private ArrayList<VideosBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initTitle();
        getData();
        initData();
        setListener();

    }

    private void initTitle() {
        setFavoriteVisiable(View.GONE);
        setShareVisiable(View.GONE);
        super.setTitle("预告片&拍摄花絮");
    }

    private void getData() {
        data = getIntent().getParcelableArrayListExtra("data");
    }

    private void initData() {
        if (data != null) {
            listView.setAdapter(new VideoActBaseAdapter(this, data));
        }
    }

    @Override
    protected View getView() {
        View view = View.inflate(this, R.layout.activity_video, null);
        listView = (ListView) view.findViewById(R.id.lv_video);
        return view;
    }

    private void setListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (data != null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(data.get(position).url), "video/*");
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    protected void clickBack() {
        finish();
    }

    @Override
    protected void clickFavorite() {

    }

    @Override
    protected void clickShare() {

    }
}
