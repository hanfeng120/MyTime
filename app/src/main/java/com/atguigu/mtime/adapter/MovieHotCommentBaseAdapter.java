package com.atguigu.mtime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.bean.MovieHotCommentBean;
import com.atguigu.mtime.view.CircleTransform;
import com.squareup.picasso.Picasso;

import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by HanFeng on 2015/12/11.
 */
public class MovieHotCommentBaseAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MovieHotCommentBean.HotCommentBean> data;
    private boolean showAll;
    private LayoutInflater inflater;

    public MovieHotCommentBaseAdapter(Context context, ArrayList<MovieHotCommentBean.HotCommentBean> data, boolean showAll) {
        this.context = context;
        this.data = data;
        this.showAll = showAll;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (showAll) {
            return data.size();
        } else {
            if (data.size() > 0) {
                return 1;
            } else {
                return data.size();
            }
        }
    }

    @Override
    public MovieHotCommentBean.HotCommentBean getItem(int position) {
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
            convertView = inflater.inflate(R.layout.item_movie_hot_comment, null);
            holder.tvMovieHotCommentTitle = (TextView) convertView.findViewById(R.id.tv_movie_hot_comment_title);
            holder.tvMovieHotCommentContent = (TextView) convertView.findViewById(R.id.tv_movie_hot_comment_content);
            holder.tvMovieHotCommentIcon = (ImageView) convertView.findViewById(R.id.tv_movie_hot_comment_icon);
            holder.tvMovieHotCommentUser = (TextView) convertView.findViewById(R.id.tv_movie_hot_comment_user);
            holder.tvMovieHotCommentTime = (TextView) convertView.findViewById(R.id.tv_movie_hot_comment_time);
            holder.tvMovieScore = (TextView) convertView.findViewById(R.id.tv_movie_score);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MovieHotCommentBean.HotCommentBean hotCommentBean = getItem(position);

        holder.tvMovieHotCommentTitle.setText(hotCommentBean.nickname);
        holder.tvMovieHotCommentContent.setText(hotCommentBean.content.replace(" ", ""));
        Picasso.with(context).load(hotCommentBean.headurl).into(holder.tvMovieHotCommentIcon);

        holder.tvMovieHotCommentUser.setText(hotCommentBean.nickname);
//                        holder.tvMovieHotCommentTime = (
//                                holder.tvMovieScore.setText(hotCommentBean.rating);


        return convertView;
    }

    static class ViewHolder {
        public TextView tvMovieHotCommentTitle;
        public TextView tvMovieHotCommentContent;
        public ImageView tvMovieHotCommentIcon;
        public TextView tvMovieHotCommentUser;
        public TextView tvMovieHotCommentTime;
        public TextView tvMovieScore;

    }

}
