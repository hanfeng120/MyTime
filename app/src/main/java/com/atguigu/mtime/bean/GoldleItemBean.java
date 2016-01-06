package com.atguigu.mtime.bean;

import java.util.List;

/**
 * Created by yiran on 2015/12/12.
 * 全球排行榜
 */
public class GoldleItemBean {
    public List<MoviesData> movies;
    public NxtTopListData nextTopList;
    public int pageCount;
    public PreviousTopListData previousTopList;
    public TopListData1 topList;
    public int totalCount;

    public class MoviesData{
        public String actor;
        public String director;
        public String movieType;
        public String nameEn;
        public String posterUrl;
        public String name;
        public String releaseDate;
        public String releaseLocation;
        public String remark;
        public String totalBoxOffice;
        public String weekBoxOffice;

        public int decade;
        public int id;
        public int rankNum;
        public String rating;
        public boolean isPresell;
        public boolean isTicket;
    }

    public class NxtTopListData{
        public int toplistId;
        public int toplistItemType;
        public int toplistType;
    }

    public class PreviousTopListData{
        public int toplistId;
        public int toplistItemType;
        public int toplistType;
    }

    public class TopListData1{
        public int id;
        public int totalCount;
        public String name;
        public String summary;
    }
}
