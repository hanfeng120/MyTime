package com.atguigu.mtime.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by HanFeng on 2015/12/11.
 */
public class MovieHotCommentBean implements Parcelable {

    public ArrayList<HotCommentBean> comments;
    public int totalCount;

    public MovieHotCommentBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(comments);
        dest.writeInt(totalCount);
    }

    public static class HotCommentBean implements Parcelable {

        public String commentCount;
        public String content;
        public String headurl;
        public String id;
        public String isWantSee;
        public String location;
        public String modifyTime;
        public String nickname;
        public String rating;
        public String title;

        public HotCommentBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(commentCount);
            dest.writeString(content);
            dest.writeString(headurl);
            dest.writeString(id);
            dest.writeString(isWantSee);
            dest.writeString(location);
            dest.writeString(modifyTime);
            dest.writeString(nickname);
            dest.writeString(rating);
            dest.writeString(title);
        }

        public static Parcelable.Creator<HotCommentBean> CREATOR = new Creator<HotCommentBean>() {
            @Override
            public HotCommentBean createFromParcel(Parcel source) {
                return new HotCommentBean(source);
            }

            @Override
            public HotCommentBean[] newArray(int size) {
                return new HotCommentBean[0];
            }
        };

        public HotCommentBean(Parcel in) {
            commentCount = in.readString();
            content = in.readString();
            headurl = in.readString();
            id = in.readString();
            isWantSee = in.readString();
            location = in.readString();
            modifyTime = in.readString();
            nickname = in.readString();
            rating = in.readString();
            title = in.readString();
        }
    }

    public static Parcelable.Creator<MovieHotCommentBean> CREATOR = new Creator<MovieHotCommentBean>() {
        @Override
        public MovieHotCommentBean createFromParcel(Parcel source) {
            return new MovieHotCommentBean(source);
        }

        @Override
        public MovieHotCommentBean[] newArray(int size) {
            return new MovieHotCommentBean[0];
        }
    };

    public MovieHotCommentBean(Parcel in) {
        comments = in.readArrayList(getClass().getClassLoader());
        totalCount = in.readInt();
    }

}
