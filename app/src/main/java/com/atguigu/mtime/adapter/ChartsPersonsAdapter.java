package com.atguigu.mtime.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.bean.ChartsPersonsBean;

import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by yui-pc on 2015/12/14.
 */
public class ChartsPersonsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ChartsPersonsBean.PersonsBean> data;

    public ChartsPersonsAdapter(Context context, ArrayList<ChartsPersonsBean.PersonsBean> data) {
        this.context = context;
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
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
            convertView = View.inflate(context, R.layout.activity_charts_person_item, null);
            holder.ivChartspersonItemNum = (TextView) convertView.findViewById(R.id.iv_chartsperson_item_num);
            holder.tvChartspersonItemPhoto = (ImageView) convertView.findViewById(R.id.tv_chartsperson_item_photo);
            holder.tvChartspersonItemName = (TextView) convertView.findViewById(R.id.tv_chartsperson_item_name);
            holder.tvChartspersonItemRating = (TextView) convertView.findViewById(R.id.tv_chartsperson_item_rating);
            holder.tvChartspersonItemNameEn = (TextView) convertView.findViewById(R.id.tv_chartsperson_item_nameEn);
            holder.tvChartspersonItemSexanddate = (TextView) convertView.findViewById(R.id.tv_chartsperson_item_sexanddate);
            holder.tvChartspersonItemSummary = (TextView) convertView.findViewById(R.id.tv_chartsperson_item_summary);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (data.get(position).rankNum < 10) {
            holder.ivChartspersonItemNum.setText("0" + data.get(position).rankNum);
        } else {
            holder.ivChartspersonItemNum.setText(data.get(position) + "");
        }

        if (data.get(position).rankNum == 1) {
            holder.ivChartspersonItemNum.setBackgroundResource(R.mipmap.topmovie_list_num1);
        } else if (data.get(position).rankNum == 2) {
            holder.ivChartspersonItemNum.setBackgroundResource(R.mipmap.topmovie_list_num2);
        } else if (data.get(position).rankNum == 3) {
            holder.ivChartspersonItemNum.setBackgroundResource(R.mipmap.topmovie_list_num3);
        } else {
            holder.ivChartspersonItemNum.setBackgroundResource(R.mipmap.topmovie_list_num_normal);
        }
        x.image().bind(holder.tvChartspersonItemPhoto, data.get(position).posterUrl);
        holder.tvChartspersonItemName.setText(data.get(position).nameCn);
        holder.tvChartspersonItemNameEn.setText(data.get(position).nameEn);
        holder.tvChartspersonItemRating.setText(data.get(position).rating);
        holder.tvChartspersonItemSexanddate.setText(data.get(position).sex + "," + data.get(position).birthDay + "(" + data.get(position).birthLocation + ")");
        holder.tvChartspersonItemSummary.setText(data.get(position).summary);
        return convertView;
    }

    static class ViewHolder {
        private TextView ivChartspersonItemNum;
        private ImageView tvChartspersonItemPhoto;
        private LinearLayout chartspersonTitle;
        private TextView tvChartspersonItemName;
        private TextView tvChartspersonItemRating;
        private TextView tvChartspersonItemNameEn;
        private TextView tvChartspersonItemSexanddate;
        private TextView tvChartspersonItemSummary;
    }
}
