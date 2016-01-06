package com.atguigu.mtime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.bean.MovieVideoBean;
import com.atguigu.mtime.bean.PrevueListBean;
import com.atguigu.mtime.bean.VideosBean;
import com.atguigu.mtime.utils.MTimeUtils;

import org.xutils.x;

import java.util.ArrayList;

/**
 * 电影详情视频列表 适配器
 * Created by 赵迅艺 on 2015/12/12.
 */
public class VideoActBaseAdapter extends BaseAdapter {

    private static final String TAG = VideoActBaseAdapter.class.getSimpleName();
    private Context context;
    private ArrayList<VideosBean> data;
    private LayoutInflater inflater;

    public VideoActBaseAdapter(Context context, ArrayList<VideosBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public VideosBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_videos, null);
            holder.ivPhoto = (ImageView) convertView.findViewById(R.id.iv_video_act_photo);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_video_act_title);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tv_video_act_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        VideosBean videosBean = getItem(position);

        x.image().bind(holder.ivPhoto, videosBean.image);
        holder.tvTitle.setText(videosBean.title);
        holder.tvTime.setText(MTimeUtils.stringForTimeCn(videosBean.length));

        return convertView;
    }


    static class ViewHolder {
        ImageView ivPhoto;
        TextView tvTitle;
        TextView tvTime;

    }

}
