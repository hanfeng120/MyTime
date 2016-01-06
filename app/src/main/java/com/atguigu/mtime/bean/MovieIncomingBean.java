package com.atguigu.mtime.bean;

import java.util.ArrayList;

/**
 * Created by HanFeng on 2015/12/10.
 */
public class MovieIncomingBean {

    public ArrayList<AttentionBean> attention;
    public ArrayList<MovieComingsBean> moviecomings;

    public MovieIncomingBean() {
    }

    public static class AttentionBean{
        public String actor1;
        public String actor2;
        public String director;
        public String image;
        public String locationName;
        public String releaseDate;
        public String title;
        public String type;

        public boolean isFilter;
        public boolean isTicket;
        public boolean isVideo;

        public int id;
        public int rDay;
        public int rMonth;
        public int rYear;
        public int videoCount;
        public int wantedCount;
        public ArrayList<VideosBean> videos;

        public AttentionBean() {
        }
    }

    public static class MovieComingsBean{
        public String actor1;
        public String actor2;
        public String director;
        public String image;
        public String locationName;
        public String title;
        public String type;

        public boolean isFilter;
        public boolean isTicket;
        public boolean isVideo;

        public int id;
        public int rDay;
        public int rMonth;
        public int rYear;
        public int wantedCount;

        public ArrayList<VideosBean> videos;

        public MovieComingsBean() {
        }

        @Override
        public String toString() {
            return "MovieComingsBean{" +
                    "actor1='" + actor1 + '\'' +
                    ", actor2='" + actor2 + '\'' +
                    ", director='" + director + '\'' +
                    ", image='" + image + '\'' +
                    ", locationName='" + locationName + '\'' +
                    ", title='" + title + '\'' +
                    ", type='" + type + '\'' +
                    ", isFilter=" + isFilter +
                    ", isTicket=" + isTicket +
                    ", isVideo=" + isVideo +
                    ", id=" + id +
                    ", rDay=" + rDay +
                    ", rMonth=" + rMonth +
                    ", rYear=" + rYear +
                    ", wantedCount=" + wantedCount +
                    ", videos=" + videos +
                    '}';
        }
    }



}
