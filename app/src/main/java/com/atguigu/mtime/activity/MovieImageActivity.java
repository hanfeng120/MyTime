package com.atguigu.mtime.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.base.BaseActivity;
import com.atguigu.mtime.adapter.ImageActBaseAdapter;
import com.atguigu.mtime.bean.MovieImageBean;
import com.atguigu.mtime.utils.DensityUtil;
import com.atguigu.mtime.view.widget.SubTabView;

import java.util.ArrayList;

public class MovieImageActivity extends BaseActivity {

    private LinearLayout llImageActTab;
    private GridView gvImageAct;

    private MovieImageBean data;
    private ArrayList<SubTabView> subTabViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initTitle();
        getData();
        setListener();

    }

    private void initTitle() {
        setFavoriteVisiable(View.GONE);
        setShareVisiable(View.GONE);
    }

    private void getData() {
        data = getIntent().getParcelableExtra("data");
        super.setTitle(getIntent().getStringExtra("title"));
        if (data != null) {
            setData();
            setAdapter();
        }
    }

    private void setAdapter() {
        gvImageAct.setAdapter(new ImageActBaseAdapter(this, data.images));
    }

    private void setData() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        subTabViews = new ArrayList<>();
        for (int i = 0; i < data.imageTypes.size(); i++) {
            final SubTabView subTabView = new SubTabView(this);
            subTabView.setTitle(data.imageTypes.get(i).typeName);
            subTabView.setPadding(DensityUtil.dip2px(this, 15), 0, DensityUtil.dip2px(this, 15), 0);
            if (i == 0) {
                subTabView.setChecked(true);
            }
            subTabViews.add(subTabView);
            llImageActTab.addView(subTabView, params);

            subTabView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < subTabViews.size(); i++) {
                        SubTabView st = subTabViews.get(i);
                        if (st == v) {
                            int type = data.imageTypes.get(i).type;
                            notifyAdapter(type);
                            st.setChecked(true);
                        } else {
                            st.setChecked(false);
                        }
                    }
                }
            });
        }
    }

    /**
     * 根据选择的Tab更新GridView
     * @param type
     */
    private void notifyAdapter(int type) {
    }

    @Override
    protected View getView() {
        View view = View.inflate(this, R.layout.activity_movie_image, null);
        llImageActTab = (LinearLayout) view.findViewById(R.id.ll_image_act_tab);
        gvImageAct = (GridView) view.findViewById(R.id.gv_image_act);
        return view;
    }

    private void setListener() {
        gvImageAct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MovieImageActivity.this, ImageScanActivity.class);
                intent.putParcelableArrayListExtra("data", data.images);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
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
