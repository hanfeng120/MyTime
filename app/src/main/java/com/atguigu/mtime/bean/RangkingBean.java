package com.atguigu.mtime.bean;

import java.util.List;

/**
 * Created by yiran on 2015/12/11.
 * 排行榜数据
 */
public class RangkingBean {
    public List<TopListData> topList;

    public class TopListData {
        public String pageSubAreaId;
        public String title;
        public String titleSmall;

         public List<SubTopList> subTopList;


        public class SubTopList {
            public String pageSubAreaId;
            public String title;
            public String titleSmall;

            @Override
            public String toString() {
                return "SubTopList{" +
                        "pageSubAreaId='" + pageSubAreaId + '\'' +
                        ", title='" + title + '\'' +
                        ", titleSmall='" + titleSmall + '\'' +
                        '}';
            }
        }

    }
}
