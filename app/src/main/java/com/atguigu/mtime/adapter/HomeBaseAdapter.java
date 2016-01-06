package com.atguigu.mtime.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.bean.HomeBean;
import com.atguigu.mtime.bean.HomeListBean;
import com.atguigu.mtime.utils.MTimeUtils;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by HanFeng on 2015/12/8.
 */
public class HomeBaseAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<HomeBean.HomeHotPoints> data;
    private LayoutInflater inflater;

    public HomeBaseAdapter(Context context, ArrayList<HomeBean.HomeHotPoints> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public HomeBean.HomeHotPoints getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).type;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
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
            switch (getItemViewType(position)) {
                case 0://图片在左
                    convertView = inflater.inflate(R.layout.type_zero_lv_home, null);
                    holder.ivHomeLvZero = (ImageView) convertView.findViewById(R.id.iv_home_lv_zero);
                    holder.tvHomeLvZeroTitle = (TextView) convertView.findViewById(R.id.tv_home_lv_zero_title);
                    holder.tvHomeLvZeroSubtitle = (TextView) convertView.findViewById(R.id.tv_home_lv_zero_subtitle);
                    holder.tvHomeLvZeroTime = (TextView) convertView.findViewById(R.id.tv_home_lv_zero_time);
                    break;
                case 2:
                    convertView = inflater.inflate(R.layout.type_two_lv_home, null);
                    holder.ivHomeLvZero = (ImageView) convertView.findViewById(R.id.iv_home_lv_zero);
                    holder.tvHomeLvZeroTitle = (TextView) convertView.findViewById(R.id.tv_home_lv_zero_title);
                    holder.tvHomeLvZeroSubtitle = (TextView) convertView.findViewById(R.id.tv_home_lv_zero_subtitle);
                    holder.tvHomeLvZeroTime = (TextView) convertView.findViewById(R.id.tv_home_lv_zero_time);
                    break;
                case 1://图片在中间
                    convertView = inflater.inflate(R.layout.type_one_lv_home, null);
                    holder.tvHomeLvOneTitle = (TextView) convertView.findViewById(R.id.tv_home_lv_one_title);
                    holder.tvHomeLvOneSubtitle = (TextView) convertView.findViewById(R.id.tv_home_lv_one_subtitle);
                    holder.ivHomeLvOne0 = (ImageView) convertView.findViewById(R.id.iv_home_lv_one_0);
                    holder.ivHomeLvOne1 = (ImageView) convertView.findViewById(R.id.iv_home_lv_one_1);
                    holder.ivHomeLvOne2 = (ImageView) convertView.findViewById(R.id.iv_home_lv_one_2);
                    holder.tvHomeLvOneTime = (TextView) convertView.findViewById(R.id.tv_home_lv_one_time);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HomeBean.HomeHotPoints hotPoints = getItem(position);

        switch (getItemViewType(position)) {
            case 0://图片在左
            case 2:
                x.image().bind(holder.ivHomeLvZero, hotPoints.img);
                holder.tvHomeLvZeroTitle.setText(hotPoints.title);
                holder.tvHomeLvZeroSubtitle.setText(hotPoints.desc);
                holder.tvHomeLvZeroTime.setText(MTimeUtils.setTime(hotPoints.publishTime));

                break;
            case 1://图片在中间
                ImageOptions.Builder builder = new ImageOptions.Builder();
                builder.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
                ImageOptions options = builder.build();
                x.image().bind(holder.ivHomeLvOne0, hotPoints.images.get(0).url1, options);
                x.image().bind(holder.ivHomeLvOne1, hotPoints.images.get(1).url1, options);
                x.image().bind(holder.ivHomeLvOne2, hotPoints.images.get(2).url1, options);

                holder.tvHomeLvOneTitle.setText(hotPoints.title);
                holder.tvHomeLvOneSubtitle.setText(hotPoints.desc);
                holder.tvHomeLvOneTime.setText(MTimeUtils.setTime(hotPoints.publishTime));
                break;
        }

        return convertView;
    }

    static class ViewHolder {

        private ImageView ivHomeLvZero;
        private TextView tvHomeLvZeroTitle;
        private TextView tvHomeLvZeroSubtitle;
        private TextView tvHomeLvZeroTime;
        private TextView tvHomeLvOneTitle;
        private TextView tvHomeLvOneSubtitle;
        private ImageView ivHomeLvOne0;
        private ImageView ivHomeLvOne1;
        private ImageView ivHomeLvOne2;
        private TextView tvHomeLvOneTime;

    }

}
