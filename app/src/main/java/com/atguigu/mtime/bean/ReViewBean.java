package com.atguigu.mtime.bean;

import java.util.List;

/**
 * Created by yiran on 2015/12/8.
 * 影评Josn数据
 */
public class ReViewBean {
       public  List<ReViewListData> data;

    public class ReViewListData{
        public int id;
        public String nickname;
        public String rating;
        public String summary;
        public String title;
        public String userImage;
        public ReViewListDataTag relatedObj ;

        public class ReViewListDataTag{
            public int id;
            public String image;
            public String rating;
            public String title;
            public int type;
        }
    }
}
