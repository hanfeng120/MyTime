package com.atguigu.mtime.bean;

import java.util.ArrayList;

/**
 *
 * Created by HanFeng on 2015/12/12.
 */
public class NewsBean {

    public ArrayList<NewsListBean.NewsList> newsList;
    public int totalCount;
    public int totalPage;

    public NewsBean() {
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "newsList=" + newsList +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                '}';
    }

}
