package com.atguigu.mtime.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.bean.ReViewBean;
import com.atguigu.mtime.utils.Constants;
import com.atguigu.mtime.utils.SPUtils;
import com.atguigu.mtime.view.CircleTransform;
import com.squareup.picasso.Picasso;

import org.xutils.x;

import java.util.List;

/**
 * Created by yiran on 2015/12/9.
 */
public class ReViewPagerAdapter extends BaseAdapter {
    private Context context;
    private List<ReViewBean.ReViewListData> data;

    public ReViewPagerAdapter(Context context, List<ReViewBean.ReViewListData> data) {
        this.context = context;
        this.data = data;
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
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.review_item, null);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvMovies = (TextView) convertView.findViewById(R.id.tv_movies);
            viewHolder.tvNumber = (TextView) convertView.findViewById(R.id.tv_number);
            viewHolder.ivCard = (ImageView) convertView.findViewById(R.id.iv_card);
            viewHolder.ivMoviesname = (ImageView) convertView.findViewById(R.id.iv_moviesname);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ReViewBean.ReViewListData reViewListData = data.get(position);
        viewHolder.tvTitle.setText(reViewListData.title);

        viewHolder.tvContent.setText(reViewListData.summary);
        viewHolder.tvNumber.setText(reViewListData.relatedObj.rating);
        viewHolder.tvName.setText(reViewListData.nickname + "-评");
        viewHolder.tvMovies.setText("《" + reViewListData.relatedObj.title + "》");

        Picasso.with(context).load(reViewListData.userImage).transform(new CircleTransform()).into(viewHolder.ivCard);
//        x.image().bind(viewHolder.ivCard, reViewListData.userImage);
        x.image().bind(viewHolder.ivMoviesname, reViewListData.relatedObj.image);

        String idArray = SPUtils.getInstance(context).getValue(Constants.READ_ARRAY_ID, null);
        if (idArray != null) {
            if (idArray.contains(reViewListData.id + "")) {
                viewHolder.tvTitle.setTextColor(Color.GRAY);
            } else {
                viewHolder.tvTitle.setTextColor(Color.BLACK);

            }
        }
        return convertView;
    }

    class ViewHolder {
        private TextView tvTitle;
        private TextView tvContent;
        private ImageView ivCard;
        private TextView tvName;
        private TextView tvMovies;
        private TextView tvNumber;
        private ImageView ivMoviesname;


    }
}
