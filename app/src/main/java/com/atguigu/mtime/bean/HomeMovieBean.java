package com.atguigu.mtime.bean;

import java.util.ArrayList;

/**
 * 首页选座购票的Bean
 * Created by HanFeng on 2015/12/9.
 */
public class HomeMovieBean {

    public int count;
    public int totalCinemaCount;
    public int totalComingMovie;
    public int totalHotMovie;

    public ArrayList<HomeMoviesBean> movies;

    public HomeMovieBean() {
    }

    public static class HomeMoviesBean{

        public String actorName1;
        public String actorName2;
        public String commonSpecial;
        public String directorName;
        public String img;
        public String is3D;
        public String isDMAX;
        public String isFilter;
        public String isHot;
        public String isIMAX;
        public String isIMAX3D;
        public String isNew;
        public String length;
        public String movieId;
        public String rDay;
        public String rMonth;
        public String rYear;
        public String ratingFinal;
        public String titleEn;
        public String titleCn;
        public String type;
        public String wantedCount;
        public NearestShowtimeBean nearestShowtime;

        public HomeMoviesBean() {
        }
    }

    public static class NearestShowtimeBean {

        public String isTicket;
        public int nearestCinemaCount;
        public int nearestShowDay;
        public int nearestShowtimeCount;

        public NearestShowtimeBean() {
        }
    }

    @Override
    public String toString() {
        return "HomeMovieBean{" +
                "count=" + count +
                ", totalCinemaCount=" + totalCinemaCount +
                ", totalComingMovie=" + totalComingMovie +
                ", totalHotMovie=" + totalHotMovie +
                ", movies=" + movies +
                '}';
    }
}
