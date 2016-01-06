package com.atguigu.mtime.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.base.BaseActivity;
import com.atguigu.mtime.adapter.MovieCommentBaseAdapter;
import com.atguigu.mtime.bean.MovieCommentBean;

public class ShortCommentActivity extends BaseActivity {

    private ListView listView;

    private MovieCommentBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initTitleBar();
        getData();
        initData();
        
    }

    private void initTitleBar() {
        setFavoriteVisiable(View.GONE);
        setShareVisiable(View.GONE);
    }

    private void getData() {
        Intent intent = getIntent();
        data = intent.getParcelableExtra("data");
        super.setTitle("短评("+ data.totalCommentCount +")");
    }

    private void initData() {
        listView.setAdapter(new MovieCommentBaseAdapter(this, data.cts, true));
    }

    @Override
    protected View getView() {
        View view = View.inflate(this, R.layout.activity_short_comment, null);
        listView = (ListView) view.findViewById(R.id.lv_shortcomment);
        return view;
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
