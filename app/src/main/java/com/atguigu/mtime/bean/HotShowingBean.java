package com.atguigu.mtime.bean;

import java.util.ArrayList;

/**
 * 购票页面正在热映
 * Created by HanFeng on 2015/12/10.
 */
public class HotShowingBean {

    public String bImg;
    public String date;
    public int lid;
    public int newActivitiesTime;
    public int totalComingMovie;
    public String voucherMsg;
    public ArrayList<ShowingMovieBean> ms;

    public HotShowingBean() {
    }

    public static class ShowingMovieBean{

        public int NearestCinemaCount;
        public int NearestDay;
        public int NearestShowtimeCount;
        public int cC;
        public int id;
        public int rc;
        public int rsC;
        public int sC;
        public int ua;
        public int wantedCount;

        public String r;
        public String aN1;
        public String aN2;
        public String commonSpecial;
        public String d;
        public String dN;
        public String img;
        public String movieType;
        public String rd;
        public String t;
        public String tCn;
        public String tEn;

        public boolean is3D;
        public boolean isDMAX;
        public boolean isFilter;
        public boolean isHot;
        public boolean isIMAX;
        public boolean isIMAX3D;
        public boolean isNew;
        public boolean isTicket;

        public ShowingMovieBean() {
        }
    }

    @Override
    public String toString() {
        return "HotShowingBean{" +
                "bImg='" + bImg + '\'' +
                ", date='" + date + '\'' +
                ", lid='" + lid + '\'' +
                ", newActivitiesTime='" + newActivitiesTime + '\'' +
                ", totalComingMovie='" + totalComingMovie + '\'' +
                ", voucherMsg='" + voucherMsg + '\'' +
                ", ms=" + ms +
                '}';
    }
}
