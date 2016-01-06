package com.atguigu.mtime.bean;

import java.util.List;

/**
 * 发现页面中的 新闻的数据
 * Created by 申瑞芹 on 2015/12/9.
 */
public class NewsListBean {
    public List<NewsList> newsList;
    public int pageCount;
    public int totalCount;

    public NewsListBean() {
    }

    public class NewsList {
        public int commentCount;
        public int id;
        public String image;
        public List<Images> images;
        public int publishTime;//发布时间
        public String summary;//描述
        public String summaryInfo;
        public String tag;
        public String title;//标题
        public String title2;//标题2
        public int type;//类型

        public NewsList() {
        }

        public class Images {
            public String desc;
            public int gId;
            public String title;
            public String url1;
            public String url2;

            public Images() {
            }
        }


    }
}
