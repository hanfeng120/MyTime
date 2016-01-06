package com.atguigu.mtime.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.MainActivity;
import com.atguigu.mtime.bean.MallBottomBean;

import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by yui-pc on 2015/12/10.
 */
public class MallGridAdapter extends BaseAdapter {
    private MainActivity mainActivity;
    private ArrayList<MallBottomBean.GoodsListBean> data;
    private LayoutInflater inflater;

    public MallGridAdapter(MainActivity mainActivity, ArrayList<MallBottomBean.GoodsListBean> data) {
        this.mainActivity = mainActivity;
        this.data = data;
        inflater = LayoutInflater.from(mainActivity);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.mall_home_intrested_item, null);
            holder.product_img = (ImageView) convertView.findViewById(R.id.product_img);
            holder.product_name = (TextView) convertView.findViewById(R.id.product_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.product_name.setText(data.get(position).name);
        x.image().bind(holder.product_img, data.get(position).image);
        return convertView;
    }

    static class ViewHolder {
        public ImageView product_img;
        public TextView product_name;
    }
}
