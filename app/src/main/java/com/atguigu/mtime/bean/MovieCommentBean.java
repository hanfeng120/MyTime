package com.atguigu.mtime.bean;

import android.app.ActivityManager;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by HanFeng on 2015/12/11.
 */
public class MovieCommentBean implements Parcelable {

    public ArrayList<ShortCommentBean> cts;
    public int totalCommentCount;
    public int totalCount;
    public int tpc;

    public MovieCommentBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(cts);
        dest.writeInt(totalCommentCount);
        dest.writeInt(totalCount);
        dest.writeInt(tpc);
    }

    public static class ShortCommentBean implements Parcelable{

        public String ca;
        public String caimg;
        public String cal;
        public String cd;
        public String ce;
        public String ceimg;
        public String commentCount;
        public String lcd;
        public String cr;
        public String tweetId;

        public ShortCommentBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(ca);
            dest.writeString(caimg);
            dest.writeString(cal);
            dest.writeString(cd);
            dest.writeString(ce);
            dest.writeString(ceimg);
            dest.writeString(commentCount);
            dest.writeString(lcd);
            dest.writeString(cr);
            dest.writeString(tweetId);
        }

        public static final Parcelable.Creator<ShortCommentBean> CREATOR = new Creator<ShortCommentBean>()
        {
            @Override
            public ShortCommentBean[] newArray(int size)
            {
                return new ShortCommentBean[size];
            }

            @Override
            public ShortCommentBean createFromParcel(Parcel in)
            {
                return new ShortCommentBean(in);
            }
        };

        public ShortCommentBean(Parcel in){
            ca = in.readString();
            caimg = in.readString();
            cal = in.readString();
            cd = in.readString();
            ce = in.readString();
            ceimg = in.readString();
            commentCount = in.readString();
            lcd = in.readString();
            cr = in.readString();
            tweetId = in.readString();
        }

    }

    public static final Parcelable.Creator<MovieCommentBean> CREATOR = new Creator<MovieCommentBean>()
    {
        @Override
        public MovieCommentBean[] newArray(int size)
        {
            return new MovieCommentBean[size];
        }

        @Override
        public MovieCommentBean createFromParcel(Parcel in)
        {
            return new MovieCommentBean(in);
        }
    };

    public MovieCommentBean(Parcel in)
    {
        cts = in.readArrayList(getClass().getClassLoader());
        totalCommentCount = in.readInt();
        totalCount = in.readInt();
        tpc = in.readInt();
    }

}
