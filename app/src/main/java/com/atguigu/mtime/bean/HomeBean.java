package com.atguigu.mtime.bean;

import java.util.ArrayList;

/**
 * 首页数据Bean
 * Created by HanFeng on 2015/12/8.
 */
public class HomeBean {

    public ArrayList<HomeAdvBean> advList;
    public HomeAreaSecondBean areaSecond;
    public HomeHotMovie hotMovie;
    public ArrayList<HomeHotPoints> hotPoints;

    public HomeBean() {
    }

    /**
     * 首页广告Bean
     */
    public static class HomeAdvBean{

        public String img;
        public String img2;
        public String url;

        public HomeAdvBean() {
        }

        @Override
        public String toString() {
            return "HomeAdvBean{" +
                    "img='" + img + '\'' +
                    ", img2='" + img2 + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    /**
     * 每日佳片Bean
     */
    public static class HomeHotMovie{

        public String newsId;
        public String title;
        public String topCover;
        public MovieBean movie;

        @Override
        public String toString() {
            return "HomeHotMovie{" +
                    "newsId='" + newsId + '\'' +
                    ", title='" + title + '\'' +
                    ", topCover='" + topCover + '\'' +
                    ", movie=" + movie +
                    '}';
        }
    }

    /**
     * 今日热点Bean
     */
    public static class HomeHotPoints{

        public int commentCount;
        public String desc;
        public String id;
        public ArrayList<HomeImagesBean> images;
        public String img;
        public String img2;
        public String img3;
        public int publishTime;
        public String tag;
        public String title;
        public int type;

        public HomeHotPoints() {
        }

        @Override
        public String toString() {
            return "HomeHotPoints{" +
                    "commentCount=" + commentCount +
                    ", desc='" + desc + '\'' +
                    ", id='" + id + '\'' +
                    ", images=" + images +
                    ", img='" + img + '\'' +
                    ", img2='" + img2 + '\'' +
                    ", img3='" + img3 + '\'' +
                    ", publishTime=" + publishTime +
                    ", tag='" + tag + '\'' +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    '}';
        }
    }

    /**
     * 今日热点type1图片
     */
    public static class HomeImagesBean{

        public String desc;
        public String gId;
        public String title;
        public String url1;
        public String url2;

        @Override
        public String toString() {
            return "HomeImagesBean{" +
                    "desc='" + desc + '\'' +
                    ", gId='" + gId + '\'' +
                    ", title='" + title + '\'' +
                    ", url1='" + url1 + '\'' +
                    ", url2='" + url2 + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HomeBean{" +
                "advList=" + advList +
                ", areaSecond=" + areaSecond +
                ", hotMovie=" + hotMovie +
                ", hotPoints=" + hotPoints +
                '}';
    }
}
