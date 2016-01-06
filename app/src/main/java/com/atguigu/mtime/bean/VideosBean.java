package com.atguigu.mtime.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Video数据
 * <p/>
 * Created by HanFeng on 2015/12/10.
 */
public class VideosBean implements Parcelable {

    public String hightUrl;
    public String image;
    public String title;
    public String url;

    public int length;
    public int videoId;
    public int type;
    public int id;

    public VideosBean() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hightUrl);
        dest.writeString(image);
        dest.writeString(title);
        dest.writeString(url);

        dest.writeInt(length);
        dest.writeInt(videoId);
        dest.writeInt(type);
        dest.writeInt(id);
    }

    public static final Parcelable.Creator<VideosBean> CREATOR = new Creator<VideosBean>() {
        @Override
        public VideosBean createFromParcel(Parcel source) {
            return new VideosBean(source);
        }

        @Override
        public VideosBean[] newArray(int size) {
            return new VideosBean[size];
        }
    };

    public VideosBean(Parcel parcel) {
        hightUrl = parcel.readString();
        image = parcel.readString();
        title = parcel.readString();
        url = parcel.readString();

        length = parcel.readInt();
        videoId = parcel.readInt();
        type = parcel.readInt();
        id = parcel.readInt();
    }

}
