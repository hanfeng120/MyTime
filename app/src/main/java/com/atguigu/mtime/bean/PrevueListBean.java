package com.atguigu.mtime.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现页面中 的预告
 * <p/>
 * Created by 申瑞芹 on 2015/12/10.
 */
public class PrevueListBean {
    public ArrayList<Trailers> trailers;

    public class Trailers {
        public String coverImg;//视频图片
        public String hightUrl;//高清视频地址
        public int id;//
        public int movieId;//电影id
        public String movieName;//电影名称
//        public int rating;
        public String summary;
//                public List<Type> type;
        public String url;//普通网址
        public int videoLength;//电影长度
        public String videoTitle;//视频标题


//        public class Type {//类型，由于是[0]所以没分装
//
//        }

    }
}
