package com.atguigu.mtime.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.MainActivity;
import com.atguigu.mtime.bean.MallHomeBean;

import org.xutils.x;

import java.util.ArrayList;

/**
 * 商城下部
 * Created by yui-pc on 2015/12/9.
 */
public class MallListAdapter extends BaseAdapter {


    private MainActivity mainActivity;

    private ArrayList<MallHomeBean.CategoryBean> data;

    private LayoutInflater inflater;

    public MallListAdapter(MainActivity mainActivity, ArrayList<MallHomeBean.CategoryBean> data) {
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
            convertView = inflater.inflate(R.layout.mall_home_category_layout, null);
            holder.categoryColor = (View) convertView.findViewById(R.id.category_color);
            holder.categoryName = (TextView) convertView.findViewById(R.id.category_name);
            holder.categoryMore = (TextView) convertView.findViewById(R.id.category_more);
            holder.iconArrow = (ImageView) convertView.findViewById(R.id.icon_arrow);
            holder.categoryImg = (ImageView) convertView.findViewById(R.id.category_img);
            holder.categorySeparateView = (ImageView) convertView.findViewById(R.id.category_separate_view);

            holder.mall_category_img1 = (ImageView) convertView.findViewById(R.id.mall_category_img1);
            holder.mall_category_img2 = (ImageView) convertView.findViewById(R.id.mall_category_img2);
            holder.mall_category_img3 = (ImageView) convertView.findViewById(R.id.mall_category_img3);
            holder.mall_category_title1 = (TextView) convertView.findViewById(R.id.mall_category_title1);
            holder.mall_category_title2 = (TextView) convertView.findViewById(R.id.mall_category_title2);
            holder.mall_category_title3 = (TextView) convertView.findViewById(R.id.mall_category_title3);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        holder.categoryColor.setbac
        holder.categoryName.setText(data.get(position).name);
        x.image().bind(holder.categoryImg,data.get(position).image);
        x.image().bind(holder.mall_category_img1,data.get(position).subList.get(0).image);
        x.image().bind(holder.mall_category_img2,data.get(position).subList.get(1).image);
        x.image().bind(holder.mall_category_img3,data.get(position).subList.get(2).image);

        return convertView;
    }

    static class ViewHolder {

        public View categoryColor;
        public TextView categoryName;
        public TextView categoryMore;
        public ImageView iconArrow;
        public ImageView categoryImg;
        public ImageView categorySeparateView;

        public ImageView mall_category_img1;
        public TextView mall_category_title1;
        public ImageView mall_category_img2;
        public TextView mall_category_title2;
        public ImageView mall_category_img3;
        public TextView mall_category_title3;
       // public TextView mall_category_price1;
    }
}
