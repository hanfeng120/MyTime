package com.atguigu.mtime.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HanFeng on 2015/12/12.
 */
public class ImageBean implements Parcelable {

    public String id;
    public String image;
    public int type;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.image);
        dest.writeInt(this.type);
    }

    public ImageBean() {
    }

    protected ImageBean(Parcel in) {
        this.id = in.readString();
        this.image = in.readString();
        this.type = in.readInt();
    }

    public static final Parcelable.Creator<ImageBean> CREATOR = new Parcelable.Creator<ImageBean>() {
        public ImageBean createFromParcel(Parcel source) {
            return new ImageBean(source);
        }

        public ImageBean[] newArray(int size) {
            return new ImageBean[size];
        }
    };
}
