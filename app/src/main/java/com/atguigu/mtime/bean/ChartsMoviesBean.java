package com.atguigu.mtime.bean;

import java.util.ArrayList;

/**
 * 排行榜电影
 * Created by yui-pc on 2015/12/12.
 */
public class ChartsMoviesBean {
    public ArrayList<MoviesBean> movies;
    public int pageCount;
    public TopListBean topList;
    public int totalCount;


    /**
     * 电影列表Bean
     */
    public static class MoviesBean{
        public String actor;//主演
        public String actor2;//导演
        public String decade;//年份
        public String director;//导演
        public int id;
        public String movieType;//类型
        public String name;//电影名称
        public String rating;//评分
        public String nameEn;//英文名字
        public String posterUrl;//封面地址
        public int rankNum;
//        public int rating;//评分
        public String releaseDate;//上映日期
        public String releaseLocation;//国家
        public String remark;//影片介绍


    }

    /**
     * 列表标题数据
     */
    public static  class TopListBean{
        public int id;
        public String name;//标题
        public String summary;//副标题
    }
}
