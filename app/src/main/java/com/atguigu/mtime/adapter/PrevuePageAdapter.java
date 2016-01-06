package com.atguigu.mtime.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.bean.PrevueListBean;
import com.atguigu.mtime.utils.CacheUtils;
import com.atguigu.mtime.utils.UrlConstants;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现Tab——预告片 适配器
 * Created by 申瑞芹 on 2015/12/8.
 */
public class PrevuePageAdapter extends BaseAdapter {


    /**
     * 保存
     */
    private static final String READ_ARRAY_ID = "read_array_id";
    private static final String TAG = PrevuePageAdapter.class.getSimpleName();
    private Context context;
    private ArrayList<PrevueListBean.Trailers> data;

    public PrevuePageAdapter(Context context, ArrayList<PrevueListBean.Trailers> data) {
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
            convertView = View.inflate(context,
                    R.layout.item_prevue_page, null);
            holder = new ViewHolder();

            holder.iv_video_icon = (ImageView) convertView
                    .findViewById(R.id.iv_video_prevue_icon);
            holder.tv_video_title = (TextView) convertView
                    .findViewById(R.id.tv_video_title);
            holder.tv_video_desc = (TextView) convertView
                    .findViewById(R.id.tv_video_desc);
            // 容器和View关联起来
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        /**
         * 通过位置得到对应的数据
         */
        PrevueListBean.Trailers trailers = data.get(position);

        /**
         * 通过XUtils从网上加载图片
         */
        x.image().bind(holder.iv_video_icon, trailers.coverImg);

        holder.tv_video_title.setText(trailers.movieName);
        holder.tv_video_desc.setText(trailers.summary);

        return convertView;
    }


    static class ViewHolder {
        ImageView iv_video_icon;
        TextView tv_video_title;
        TextView tv_video_desc;

    }

}
