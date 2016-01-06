package com.atguigu.mtime.bean;

/**
 * Created by HanFeng on 2015/12/8.
 */
public class HomeAreaSecondBean {

    public HomeSubFifth subFifth;
    public HomeSubFirst subFirst;
    public HomeSubFourth subFourth;
    public HomeSubSecond subSecond;
    public HomeSubThird subThird;

    public static class HomeSubFifth {

        public String goodsId;
        public String image;

    }

    public static class HomeSubFirst {

        public String goodsId;
        public String image;

    }

    public static class HomeSubFourth {

        public String goodsId;
        public String image;

    }

    public static class HomeSubSecond {

        public String goodsId;
        public String image;

    }

    public static class HomeSubThird {

        public String goodsId;
        public String image;

    }

    @Override
    public String toString() {
        return "HomeAreaSecondBean{" +
                "subFifth=" + subFifth +
                ", subFirst=" + subFirst +
                ", subFourth=" + subFourth +
                ", subSecond=" + subSecond +
                ", subThird=" + subThird +
                '}';
    }
}
