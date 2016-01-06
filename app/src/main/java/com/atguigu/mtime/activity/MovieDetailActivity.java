package com.atguigu.mtime.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.atguigu.mtime.R;
import com.atguigu.mtime.adapter.MovieCommentBaseAdapter;
import com.atguigu.mtime.adapter.MovieHotCommentBaseAdapter;
import com.atguigu.mtime.bean.MovieCommentBean;
import com.atguigu.mtime.bean.MovieHotCommentBean;
import com.atguigu.mtime.bean.MovieImageBean;
import com.atguigu.mtime.bean.MovieVideoBean;
import com.atguigu.mtime.bean.PrevueListBean;
import com.atguigu.mtime.utils.ShareUtils;
import com.atguigu.mtime.utils.UrlConstants;
import com.atguigu.mtime.view.widget.LoadingView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class MovieDetailActivity extends AppCompatActivity {

    private ListView lvShortComment;
    private ListView lvComment;
    private TextView tvShortComments;
    private RelativeLayout rlVideoTitle;
    private TextView tvVideoTitle;
    private TextView tvVideoVideoNum;
    private ImageView ivVideoIcon;
    private TextView tvVideoImgNum;
    private ImageView ivVideoImgIcon;
    private TextView tvShortCommentTitle;
    private TextView tvHotCommentTitle;
    private ImageView ivBack;
    private ScrollView scrollView;
    private LoadingView loadingView;

    private String id;
    private MovieCommentBean commentBean;
    private MovieHotCommentBean hotCommentBean;
    private MovieVideoBean videosBean;
    private PrevueListBean.Trailers videosData;
    private MovieImageBean imageBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initView();
        initData();
        setListener();

    }

    private void initView() {
        lvComment = (ListView) findViewById(R.id.lv_movie_comment);
        lvShortComment = (ListView) findViewById(R.id.lv_movie_short_comment);
        tvShortComments = (TextView) findViewById(R.id.tv_movie_short_comment_showall);
        rlVideoTitle = (RelativeLayout) findViewById(R.id.rl_movie_video_title);
        tvVideoTitle = (TextView) findViewById(R.id.tv_movie_video_title);
        tvVideoVideoNum = (TextView) findViewById(R.id.tv_movie_video_video_num);
        ivVideoIcon = (ImageView) findViewById(R.id.iv_movie_video_icon);
        tvVideoImgNum = (TextView) findViewById(R.id.tv_movie_video_img_num);
        ivVideoImgIcon = (ImageView) findViewById(R.id.iv_movie_video_img_icon);
        tvShortCommentTitle = (TextView) findViewById(R.id.tv_movie_short_comment_title);
        tvHotCommentTitle = (TextView) findViewById(R.id.tv_movie_hot_comment_bar);
        ivBack = (ImageView) findViewById(R.id.iv_title_bar_normal_back);
        scrollView = (ScrollView) findViewById(R.id.sc_movie_detail);
        loadingView = (LoadingView) findViewById(R.id.loading_moviedetail);
    }

    private void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        if (id != null && !TextUtils.isEmpty(id)) {
            Log.e("ID", id);
            getDataFromNet();
        } else {
            Log.e("ID", id);
        }
    }

    public void getDataFromNet() {
        /**
         * 请求短评数据
         */
        RequestParams params = new RequestParams(UrlConstants.DETAIL_MOVIE_SHORT_COMMENT + "movieId=" + id + "&pageIndex=1");
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                parseShortCommentsData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

        /**
         * 请求精选评论数据
         */
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, UrlConstants.DETAIL_MOVIE_COMMENT + "movieId=" + id + "&pageIndex=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                parseHotCommentsData(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
        requestQueue.add(request);

        /**
         * 请求视频数据
         */
        Volley.newRequestQueue(this).add(new StringRequest(UrlConstants.DETAIL_MOVIE_VIDEOS + "movieId=" + id + "&pageIndex=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                parseVideoData(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }));

        /**
         * 请求图片数据
         */
        Volley.newRequestQueue(this).add(new StringRequest(UrlConstants.DETAIL_MOVIE_IMAGES + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                parseImageData(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }));
    }

    /**
     * 解析视频剧照中图片数据
     *
     * @param s
     */
    private void parseImageData(String s) {
        imageBean = JSON.parseObject(s, MovieImageBean.class);
        scrollView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);

        tvVideoImgNum.setText(imageBean.images.size() + "张图片");
        x.image().bind(ivVideoImgIcon, imageBean.images.get(0).image);
    }

    /**
     * 解析相关视频数据
     *
     * @param s
     */
    private void parseVideoData(String s) {
        videosBean = JSON.parseObject(s, MovieVideoBean.class);
        scrollView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
        setVideoData();
    }

    /**
     * 视频剧照部分数据
     */
    private void setVideoData() {
        tvVideoVideoNum.setText(videosBean.totalCount + "个视频");
        if (videosBean.videoList != null && videosBean.videoList.size() > 0) {
            x.image().bind(ivVideoIcon, videosBean.videoList.get(0).image);
        }
    }

    /**
     * 解析精选评论数据
     *
     * @param result
     */
    private void parseHotCommentsData(String result) {
        hotCommentBean = JSON.parseObject(result, MovieHotCommentBean.class);
        scrollView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);

        lvComment.setAdapter(new MovieHotCommentBaseAdapter(this, hotCommentBean.comments, false));
        tvHotCommentTitle.setText(hotCommentBean.totalCount + "条精选评论");
    }

    /**
     * 解析短评数据
     *
     * @param result
     */
    private void parseShortCommentsData(String result) {
        commentBean = JSON.parseObject(result, MovieCommentBean.class);
        scrollView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);

        lvShortComment.setAdapter(new MovieCommentBaseAdapter(this, commentBean.cts, false));
        tvShortCommentTitle.setText(commentBean.totalCommentCount + "条网友短评");
    }

    private void setListener() {

    }

    public void clickItem(View view) {
        switch (view.getId()) {
            case R.id.rl_movie_comment://精选影评title
                if (hotCommentBean != null) {
                    Intent intent = new Intent(MovieDetailActivity.this, HotCommentActivity.class);
                    intent.putExtra("data", hotCommentBean);
                    startActivity(intent);
                }
                break;
            case R.id.rl_movie_short_comment://短评title
            case R.id.tv_movie_short_comment_showall://短评查看更多
                if (commentBean.cts != null) {
                    Intent intent = new Intent(MovieDetailActivity.this, ShortCommentActivity.class);
                    intent.putExtra("data", commentBean);
                    startActivity(intent);
                }
                break;
            case R.id.rl_movie_video_title:
            case R.id.rl_movie_video_video:
                if (videosBean != null) {
                    Intent intent = new Intent(this, VideoActivity.class);
                    intent.putParcelableArrayListExtra("data", videosBean.videoList);
                    startActivity(intent);
                }
                break;
            case R.id.ll_movie_video_img:
                if (imageBean != null) {
                    Intent intent = new Intent(MovieDetailActivity.this, MovieImageActivity.class);
                    intent.putExtra("data", imageBean);
                    intent.putExtra("title", "师父");
                    startActivity(intent);
                }
                break;
            case R.id.iv_title_bar_normal_back:
                finish();
                break;
            case R.id.iv_title_bar_normal_share:
                ShareUtils.showShare(this, "Hello World");
                break;
        }
    }

}
