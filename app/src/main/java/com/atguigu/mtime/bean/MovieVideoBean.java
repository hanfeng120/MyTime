package com.atguigu.mtime.bean;

import java.util.ArrayList;

/**
 * Created by HanFeng on 2015/12/12.
 */
public class MovieVideoBean {

    public int totalCount;
    public int totalPageCount;
    public ArrayList<VideosBean> videoList;

    public MovieVideoBean() {

    }

    @Override
    public String toString() {
        return "MovieVideoBean{" +
                "totalCount=" + totalCount +
                ", totalPageCount=" + totalPageCount +
                ", videoList=" + videoList +
                '}';
    }
}
