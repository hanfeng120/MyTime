package com.atguigu.mtime.bean;

import java.util.List;

/**
 * 新闻详情页面的数据
 * Created by HanFeng on 2015/12/13.
 */
public class NewsDetailBean {

    private int type;
    private int id;
    private String title;
    private String title2;
    private String content;
    private String time;
    private String source;
    private String author;
    private String editor;
    private int commentCount;
    private String url;
    private String wapUrl;
    private int previousNewsID;
    private int nextNewsID;

    private List<RelationsBean> relations;

    public NewsDetailBean() {

    }

    public void setType(int type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setWapUrl(String wapUrl) {
        this.wapUrl = wapUrl;
    }

    public void setPreviousNewsID(int previousNewsID) {
        this.previousNewsID = previousNewsID;
    }

    public void setNextNewsID(int nextNewsID) {
        this.nextNewsID = nextNewsID;
    }

    public void setRelations(List<RelationsBean> relations) {
        this.relations = relations;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTitle2() {
        return title2;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public String getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getEditor() {
        return editor;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public String getUrl() {
        return url;
    }

    public String getWapUrl() {
        return wapUrl;
    }

    public int getPreviousNewsID() {
        return previousNewsID;
    }

    public int getNextNewsID() {
        return nextNewsID;
    }

    public List<RelationsBean> getRelations() {
        return relations;
    }

    public static class RelationsBean {
        private int type;
        private int id;
        private String name;
        private String nameEn;
        private String image;
        private int year;
        private double rating;

        public RelationsBean() {

        }

        public void setType(int type) {
            this.type = type;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setNameEn(String nameEn) {
            this.nameEn = nameEn;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public int getType() {
            return type;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getNameEn() {
            return nameEn;
        }

        public String getImage() {
            return image;
        }

        public int getYear() {
            return year;
        }

        public double getRating() {
            return rating;
        }
    }
}
