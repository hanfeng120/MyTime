package com.atguigu.mtime.bean;

import java.util.List;

/**
 * Created by HanFeng on 2015/12/14.
 */
public class ChartsTopListBean {

    /**
     * title : 时光Top100
     * titleSmall : Mtime Top 100
     * pageSubAreaId : 2065
     */

    private List<TopListEntity> topList;

    public void setTopList(List<TopListEntity> topList) {
        this.topList = topList;
    }

    public List<TopListEntity> getTopList() {
        return topList;
    }

    public ChartsTopListBean() {

    }

    public static class TopListEntity {
        private String title;
        private String titleSmall;
        private String pageSubAreaId;

        public TopListEntity() {

        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTitleSmall(String titleSmall) {
            this.titleSmall = titleSmall;
        }

        public void setPageSubAreaId(String pageSubAreaId) {
            this.pageSubAreaId = pageSubAreaId;
        }

        public String getTitle() {
            return title;
        }

        public String getTitleSmall() {
            return titleSmall;
        }

        public String getPageSubAreaId() {
            return pageSubAreaId;
        }
    }
}
