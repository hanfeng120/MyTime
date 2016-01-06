package com.atguigu.mtime.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.bean.NewsListBean;
import com.atguigu.mtime.utils.MTimeUtils;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现Tab——新闻页 适配器
 * Created by 申瑞芹 on 2015/12/10.
 * <p/>
 * 分类型ListView的适配器
 * 在getView之前，BaseAdapter就获取到当前准备在getView返回的View类型。然后完成同种类型的复用
 * 它的复用 需要使用 getViewTypeCount getItemViewType 这两个方法。
 */
public class NewsPageAdapter extends BaseAdapter {

    private Context context;
    public LayoutInflater mInflater;
    private List<NewsListBean.NewsList> data;

    public NewsPageAdapter(Context context, List<NewsListBean.NewsList> data) {
        this.context = context;
        this.data = data;
        this.mInflater = LayoutInflater.from(context);
    }

    /**
     * 分类型ListView，告之适配器 当前子适配器有几种类型的Layout
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 3;
    }

    /**
     * 分类型ListView 根据position 获取当前ListView里面，对应数据的 tpye字段
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return getItem(position).type;
    }

    @Override
    public int getCount() {
        if (data != null)
            return data.size();
        return 0;
    }

    @Override
    public NewsListBean.NewsList getItem(int position) {
        if (data != null)
            return data.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            switch (type) {
                case 0:
                    convertView = mInflater.inflate(R.layout.item_discover_news_type0, parent, false);
                    holder.iv_discover_news_type0_bg = (ImageView) convertView.findViewById(R.id.iv_discover_news_type0_bg);
                    holder.tv_discover_news_type0_title = (TextView) convertView.findViewById(R.id.tv_discover_news_type0_title);
                    holder.tv_discover_news_type0_desc = (TextView) convertView.findViewById(R.id.tv_discover_news_type0_desc);
                    holder.tv_discover_news_type0_time = (TextView) convertView.findViewById(R.id.tv_discover_news_type0_time);
                    holder.tv_discover_news_type0_commentCount = (TextView) convertView.findViewById(R.id.tv_discover_news_type0_commentCount);
                    break;
                case 1:
                    /**
                     *        TextView tv_discover_news_type1_time;
                     TextView tv_discover_news_type1_commentCount;

                     */
                    convertView = mInflater.inflate(R.layout.item_discover_news_type1, parent, false);
                    holder.tv_discover_news_type1_title = (TextView) convertView.findViewById(R.id.tv_discover_news_type1_title);
                    holder.iv_discover_news_type1_image1 = (ImageView) convertView.findViewById(R.id.iv_discover_news_type1_image1);
                    holder.iv_discover_news_type1_image2 = (ImageView) convertView.findViewById(R.id.iv_discover_news_type1_image2);
                    holder.iv_discover_news_type1_image3 = (ImageView) convertView.findViewById(R.id.iv_discover_news_type1_image3);
                    holder.tv_discover_news_type1_time = (TextView) convertView.findViewById(R.id.tv_discover_news_type1_time);
                    holder.tv_discover_news_type1_commentCount = (TextView) convertView.findViewById(R.id.tv_discover_news_type1_commentCount);
                    break;
                default:
                    convertView = mInflater.inflate(R.layout.item_discover_news_type2, parent, false);
                    holder.iv_discover_news_type2_bg = (ImageView) convertView.findViewById(R.id.iv_discover_news_type2_bg);
                    holder.tv_discover_news_type2_title = (TextView) convertView.findViewById(R.id.tv_discover_news_type2_title);
                    holder.tv_discover_news_type2_desc = (TextView) convertView.findViewById(R.id.tv_discover_news_type2_desc);
                    holder.tv_discover_news_type2_time = (TextView) convertView.findViewById(R.id.tv_discover_news_type2_time);
                    holder.tv_discover_news_type2_commentCount = (TextView) convertView.findViewById(R.id.tv_discover_news_type2_commentCount);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NewsListBean.NewsList item = getItem(position);
        switch (type) {
            case 0:
                /**
                 holder. = (TextView) convertView.findViewById(R.id.tv_discover_news_type0_commentCount);

                 */
                x.image().bind(holder.iv_discover_news_type0_bg, data.get(position).image);
                holder.tv_discover_news_type0_title.setText(data.get(position).title);
                holder.tv_discover_news_type0_desc.setText(data.get(position).title2);
                holder.tv_discover_news_type0_time.setText(MTimeUtils.setTime(data.get(position).publishTime));
                holder.tv_discover_news_type0_commentCount.setText("评论" + data.get(position).commentCount);
                break;
            case 1:
                /**
                 *      case 1:
                 holder. = (ImageView) convertView.findViewById(R.id.iv_discover_news_type1_image2);
                 holder. = (ImageView) convertView.findViewById(R.id.iv_discover_news_type1_image3);
                 */
                holder.tv_discover_news_type1_title.setText(data.get(position).title);
                x.image().bind(holder.iv_discover_news_type1_image1, data.get(position).images.get(0).url1);
                x.image().bind(holder.iv_discover_news_type1_image2, data.get(position).images.get(1).url1);
                x.image().bind(holder.iv_discover_news_type1_image3, data.get(position).images.get(2).url1);
                holder.tv_discover_news_type1_time.setText(MTimeUtils.setTime(data.get(position).publishTime));
                holder.tv_discover_news_type1_commentCount.setText("评论" + data.get(position).commentCount);
                break;
            case 2:
                x.image().bind(holder.iv_discover_news_type2_bg, data.get(position).image);
                holder.tv_discover_news_type2_title.setText(data.get(position).title);
                holder.tv_discover_news_type2_desc.setText(data.get(position).title2);
                holder.tv_discover_news_type2_time.setText(MTimeUtils.setTime(data.get(position).publishTime));
                holder.tv_discover_news_type2_commentCount.setText("评论" + data.get(position).commentCount);
                break;
        }
        return convertView;
    }

    /**
     * 静态内部类 不持有当前外部类的引用
     */
    static class ViewHolder {
        ImageView iv_discover_news_type0_bg;
        TextView tv_discover_news_type0_title;
        TextView tv_discover_news_type0_desc;
        TextView tv_discover_news_type0_time;
        TextView tv_discover_news_type0_commentCount;

        TextView tv_discover_news_type1_title;
        ImageView iv_discover_news_type1_image1;
        ImageView iv_discover_news_type1_image2;
        ImageView iv_discover_news_type1_image3;
        TextView tv_discover_news_type1_time;
        TextView tv_discover_news_type1_commentCount;


        ImageView iv_discover_news_type2_bg;
        TextView tv_discover_news_type2_title;
        TextView tv_discover_news_type2_desc;
        TextView tv_discover_news_type2_time;
        TextView tv_discover_news_type2_commentCount;


        /**
         * 公共的
         */
        ImageView iv_discover_news_type_bg;
        TextView tv_discover_news_type_title;
        TextView tv_discover_news_type_desc;
        TextView tv_discover_news_type_time;
        TextView tv_discover_news_type_commentCount;
        ImageView iv_discover_news_type_image1;
        ImageView iv_discover_news_type_image2;
        ImageView iv_discover_news_type_image3;

    }
}
