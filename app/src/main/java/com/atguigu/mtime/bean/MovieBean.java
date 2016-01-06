package com.atguigu.mtime.bean;

/**
 * 电影信息Bean
 * Created by HanFeng on 2015/12/8.
 */
public class MovieBean {

    public String desc;
    public String image;
    public String movieId;
    public String titleCn;
    public String titleEn;
    public String year;

    public MovieBean() {
    }

    @Override
    public String toString() {
        return "MovieBean{" +
                "desc='" + desc + '\'' +
                ", image='" + image + '\'' +
                ", movieId='" + movieId + '\'' +
                ", titleCn='" + titleCn + '\'' +
                ", titleEn='" + titleEn + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
