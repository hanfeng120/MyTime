package com.atguigu.mtime.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.base.BaseActivity;
import com.atguigu.mtime.adapter.MovieHotCommentBaseAdapter;
import com.atguigu.mtime.bean.MovieHotCommentBean;

public class HotCommentActivity extends BaseActivity {

    private ListView listView;

    private MovieHotCommentBean data;

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
        super.setTitle("精选影评");
    }

    private void initData() {
        listView.setAdapter(new MovieHotCommentBaseAdapter(this, data.comments, true));
    }

    @Override
    protected View getView() {
        View view = View.inflate(this, R.layout.activity_hot_comment, null);
        listView = (ListView) view.findViewById(R.id.lv_hot_comment);
        return view;
    }

    @Override
    protected void clickBack() {

    }

    @Override
    protected void clickFavorite() {

    }

    @Override
    protected void clickShare() {

    }
}
