package com.atguigu.mtime.bean;

import java.util.ArrayList;

/**
 * 排行榜人物
 * Created by yui-pc on 2015/12/14.
 */
public class ChartsPersonsBean {
    public NextTopListBean nextTopList;
    public int pageCount;
    public ArrayList<PersonsBean> persons;
    public PreviousTopListBean previousTopList;
    public TopListBean topList;
    public int totalCount;


    public static class TopListBean{
        public String id;
        public String name;
        public String summary;

    }
    public static class PreviousTopListBean{
        public String toplistId;
        public String toplistItemType;
        public String toplistType;
    }
    public static class PersonsBean {
        public String birthDay;
        public String birthLocation;
        public String birthYear;
        public String id;
        public String nameCn;
        public String nameEn;
        public String posterUrl;//图片
        public int rankNum;//名次
        public String rating;//评分
        public String sex;
        public String summary;//简介
    }

    public static class NextTopListBean {
        public int toplistId;
        public int toplistItemType;
        public int toplistType;
    }
}
