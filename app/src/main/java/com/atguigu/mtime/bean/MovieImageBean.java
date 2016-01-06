package com.atguigu.mtime.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HanFeng on 2015/12/12.
 */
public class MovieImageBean implements Parcelable {

    public ArrayList<ImageTypeBean> imageTypes;
    public ArrayList<ImageBean> images;

    public static class ImageTypeBean implements Parcelable {

        public int type;
        public String typeName;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.type);
            dest.writeString(this.typeName);
        }

        public ImageTypeBean() {
        }

        protected ImageTypeBean(Parcel in) {
            this.type = in.readInt();
            this.typeName = in.readString();
        }

        public static final Creator<ImageTypeBean> CREATOR = new Creator<ImageTypeBean>() {
            public ImageTypeBean createFromParcel(Parcel source) {
                return new ImageTypeBean(source);
            }

            public ImageTypeBean[] newArray(int size) {
                return new ImageTypeBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.imageTypes);
        dest.writeList(this.images);
    }

    public MovieImageBean() {
    }

    protected MovieImageBean(Parcel in) {
        imageTypes = in.readArrayList(getClass().getClassLoader());
        images = in.readArrayList(getClass().getClassLoader());
    }

    public static final Parcelable.Creator<MovieImageBean> CREATOR = new Parcelable.Creator<MovieImageBean>() {
        public MovieImageBean createFromParcel(Parcel source) {
            return new MovieImageBean(source);
        }

        public MovieImageBean[] newArray(int size) {
            return new MovieImageBean[size];
        }
    };
}
