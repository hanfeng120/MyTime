package com.atguigu.mtime.bean;

/**
 * 影评详情页
 * Created by HanFeng on 2015/12/14.
 */
public class ReviewDetailBean {

    private int commentCount;
    private int id;

    private String content;
    private String nickname;
    private String rating;
    private String summaryInfo;
    private String time;
    private String title;
    private String topImgUrl;
    private String url;
    private String userImage;
    private ReviewRelatedBean relatedObj;

    public ReviewDetailBean() {

    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setSummaryInfo(String summaryInfo) {
        this.summaryInfo = summaryInfo;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTopImgUrl(String topImgUrl) {
        this.topImgUrl = topImgUrl;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public void setRelatedObj(ReviewRelatedBean relatedObj) {
        this.relatedObj = relatedObj;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getNickname() {
        return nickname;
    }

    public String getRating() {
        return rating;
    }

    public String getSummaryInfo() {
        return summaryInfo;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getTopImgUrl() {
        return topImgUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getUserImage() {
        return userImage;
    }

    public ReviewRelatedBean getRelatedObj() {
        return relatedObj;
    }

    public static class ReviewRelatedBean{
        private String id;
        private String image;
        private String name;
        private String rating;
        private String releaseDate;
        private String releaseLocation;
        private String runtime;
        private String title;
        private String titleCn;
        private String titleEn;
        private String url;
        private String wapUrl;
        private int type;

        public ReviewRelatedBean() {

        }

        public void setId(String id) {
            this.id = id;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public void setReleaseLocation(String releaseLocation) {
            this.releaseLocation = releaseLocation;
        }

        public void setRuntime(String runtime) {
            this.runtime = runtime;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTitleCn(String titleCn) {
            this.titleCn = titleCn;
        }

        public void setTitleEn(String titleEn) {
            this.titleEn = titleEn;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setWapUrl(String wapUrl) {
            this.wapUrl = wapUrl;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public String getImage() {
            return image;
        }

        public String getName() {
            return name;
        }

        public String getRating() {
            return rating;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public String getReleaseLocation() {
            return releaseLocation;
        }

        public String getRuntime() {
            return runtime;
        }

        public String getTitle() {
            return title;
        }

        public String getTitleCn() {
            return titleCn;
        }

        public String getTitleEn() {
            return titleEn;
        }

        public String getUrl() {
            return url;
        }

        public String getWapUrl() {
            return wapUrl;
        }

        public int getType() {
            return type;
        }
    }

}
