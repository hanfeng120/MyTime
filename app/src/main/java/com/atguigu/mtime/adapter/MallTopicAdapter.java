package com.atguigu.mtime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.bean.MallHomeBean;

import org.xutils.x;

import java.util.ArrayList;

/**
 * 商城上部横向滚动scrollview下的gridview适配器
 * Created by yui-pc on 2015/12/14.
 */
public class MallTopicAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<MallHomeBean.SubArrayListBean> topicData;
    private LayoutInflater inflater;
    public MallTopicAdapter(Context context, ArrayList<MallHomeBean.SubArrayListBean> topicData) {
        this.context = context;
        this.topicData = topicData;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return topicData.size();
    }

    @Override
    public MallHomeBean.SubArrayListBean getItem(int position) {
        return topicData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.mall_home_topic_item,null);
            holder.topic_list_img = (ImageView) convertView.findViewById(R.id.topic_list_img);
            holder.topic_list_title = (TextView) convertView.findViewById(R.id.topic_list_title);
            holder.topic_list_price = (TextView) convertView.findViewById(R.id.topic_list_price);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        x.image().bind(holder.topic_list_img,topicData.get(position).image);
        holder.topic_list_title.setText(topicData.get(position).title);
        return convertView;
    }
    static class ViewHolder{
        public ImageView topic_list_img;
        public TextView topic_list_title;
        public TextView topic_list_price;

    }
}
