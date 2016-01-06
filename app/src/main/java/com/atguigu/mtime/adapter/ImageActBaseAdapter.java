package com.atguigu.mtime.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.bean.HotShowingBean;
import com.atguigu.mtime.bean.ImageBean;
import com.atguigu.mtime.utils.MTimeUtils;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by HanFeng on 2015/12/12.
 */
public class ImageActBaseAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ImageBean> data;
    private LayoutInflater inflater;

    public ImageActBaseAdapter(Context context, ArrayList<ImageBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public ImageBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.adapter_image_act, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv_image_adp);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageOptions.Builder builder = new ImageOptions.Builder();
        builder.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageOptions options = builder.build();
        x.image().bind(holder.imageView, data.get(position).image, options);

        return convertView;
    }

    static class ViewHolder {
        public ImageView imageView;
    }

}
