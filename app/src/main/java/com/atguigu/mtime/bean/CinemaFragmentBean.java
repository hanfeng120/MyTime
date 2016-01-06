package com.atguigu.mtime.bean;

import java.util.List;

/**
 * Created by yiran on 2015/12/10.
 */
public class CinemaFragmentBean {
    public List<CinemaListData> data;


    public class CinemaListData{
         public String address;
         public String cinameName;
        public Double baiduLatitude;
        public Double baiduLongitude;
        public Double latitude;
        public Double longitude;
        public int cinemaId;
        public int districtID;
        public boolean isETicket;
        public boolean isTicket;
        public int minPrice;
        public int movieCount;
        public int ratingFinal;
        public int showtimeCount;
        public Feattue feature;
        public List<CouponActivityList>  couponActivityList;


       public  class Feattue{
            public int has3D;
            public int hasFeature4D;
            public int hasFeature4K;
            public int hasFeatureDolby;
            public int hasFeatureHuge;
            public int hasIMAX;
            public int hasLoveseat;
            public int hasPark;
            public int hasServiceTicket;
            public int hasVIP;
            public int hasWifi;

        }
       public class CouponActivityList{
               public String desc;
               public String tag;
               public String url;
            public boolean isSelected;
            public int id;

        }

    }
}
