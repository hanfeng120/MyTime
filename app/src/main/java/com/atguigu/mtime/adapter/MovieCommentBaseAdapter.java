package com.atguigu.mtime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.bean.MovieCommentBean;
import com.atguigu.mtime.view.CircleTransform;
import com.squareup.picasso.Picasso;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by HanFeng on 2015/12/11.
 */
public class MovieCommentBaseAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MovieCommentBean.ShortCommentBean> data;
    private boolean showAll = false;
    private LayoutInflater inflater;

    public MovieCommentBaseAdapter(Context context, ArrayList<MovieCommentBean.ShortCommentBean> data, boolean shorAll) {
        this.context = context;
        this.data = data;
        this.showAll = shorAll;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (showAll) {
            return data.size();
        } else {
            if (data.size() > 3) {
                return 3;
            } else {
                return data.size();
            }
        }
    }

    @Override
    public MovieCommentBean.ShortCommentBean getItem(int position) {
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
            convertView = inflater.inflate(R.layout.item_movie_short_comment, null);
            holder.ivMovieIcon = (ImageView) convertView.findViewById(R.id.iv_movie_icon);
            holder.tvMovieUser = (TextView) convertView.findViewById(R.id.tv_movie_user);
            holder.tvMovieScore = (TextView) convertView.findViewById(R.id.tv_movie_score);
            holder.tvMovieTime = (TextView) convertView.findViewById(R.id.tv_movie_time);
            holder.tvMovieComment = (TextView) convertView.findViewById(R.id.tv_movie_comment);
            holder.tvMovieZan = (TextView) convertView.findViewById(R.id.tv_movie_zan);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MovieCommentBean.ShortCommentBean shortCommentBean = getItem(position);

        Picasso.with(context).load(shortCommentBean.caimg).into(holder.ivMovieIcon);
//        ImageOptions.Builder builder = new ImageOptions.Builder();
//        builder.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
//        ImageOptions options = builder.build();
//        x.image().bind(holder.ivMovieIcon, shortCommentBean.caimg, options);

        holder.tvMovieUser.setText(shortCommentBean.ca);
        holder.tvMovieScore.setText(shortCommentBean.cr);
//        holder.tvMovieTime.
        holder.tvMovieComment.setText(shortCommentBean.ce);
//        holder.tvMovieZan

        return convertView;
    }

    static class ViewHolder {
        public ImageView ivMovieIcon;
        public TextView tvMovieUser;
        public TextView tvMovieScore;
        public TextView tvMovieTime;
        public TextView tvMovieComment;
        public TextView tvMovieZan;
    }

}
