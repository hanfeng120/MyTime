package com.atguigu.mtime.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 用来装 发现页面中的 四个页面的 顶部的数据
 * Created by 申瑞芹 on 2015/12/9.
 */
public class DiscoverTopBean {


    public News news;//新闻
    public Review review;//影评
    public TopList topList;//排行榜
    public Trailer trailer;//预告片
    public ViewingGuide viewingGuide;

    public class News {
        public int newsID;
        public String imageUrl;
        public String title;//标题
        public int type;//类型
    }

    public class Review {
        public String imageUrl;//图片
        public String movieName;//电影名字
        public String posterUrl;//小型的图片
        public int reviewID;
        public String title;
    }

    public class TopList {
        public String imageUrl;
        public String title;
        public int type;
        public String id;

    }

    public class Trailer {
        public String hightUrl;//高清视频网址
        public String imageUrl;//图片
        public int movieId;//电影编号
        public String mp4Url;//视频地址
        public String title;//标题
        public int trailerID;//预告片本身的id
    }

    private class ViewingGuide {
        public String id;
        public String imageUrl;
    }
}
