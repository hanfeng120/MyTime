package com.atguigu.mtime.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.MTimeApplication;
import com.atguigu.mtime.base.BasePage;
import com.atguigu.mtime.bean.HotShowingBean;
import com.atguigu.mtime.bean.MovieIncomingBean;
import com.atguigu.mtime.utils.MTimeImageCache;

import org.xutils.x;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by HanFeng on 2015/12/10.
 */
public class IncomingBaseAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MovieIncomingBean.MovieComingsBean> data;
    private LayoutInflater inflater;
    private int day;

    public IncomingBaseAdapter(Context context, ArrayList<MovieIncomingBean.MovieComingsBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public MovieIncomingBean.MovieComingsBean getItem(int position) {
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
            convertView = inflater.inflate(R.layout.adapter_filmlist_off_lowapi, null);
            holder.text = (TextView) convertView.findViewById(R.id.tv_incoming);
            holder.layoutItem = (LinearLayout) convertView.findViewById(R.id.layout_item);
            holder.itemMoviedate = (TextView) convertView.findViewById(R.id.item_moviedate);
            holder.movieListItemIvPhoto = (ImageView) convertView.findViewById(R.id.movie_list_item_iv_photo);
            holder.movieListItemTagNew = (ImageView) convertView.findViewById(R.id.movie_list_item_tag_new);
            holder.movieCoverFilterText = (TextView) convertView.findViewById(R.id.movie_cover_filter_text);
            holder.itemMoviename = (TextView) convertView.findViewById(R.id.item_moviename);
            holder.itemWantseenNum = (TextView) convertView.findViewById(R.id.item_wantseen_num);
            holder.itemMovietype = (TextView) convertView.findViewById(R.id.item_movietype);
            holder.itemMoviedirector = (TextView) convertView.findViewById(R.id.item_moviedirector);
            holder.buttonPresell = (Button) convertView.findViewById(R.id.button_presell);
            holder.buttonRemind = (Button) convertView.findViewById(R.id.button_remind);
            holder.buttonVedio = (Button) convertView.findViewById(R.id.button_vedio);
            holder.dividerLineLong = (ImageView) convertView.findViewById(R.id.divider_line_long);
            holder.dividerLineShort = (ImageView) convertView.findViewById(R.id.divider_line_short);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MovieIncomingBean.MovieComingsBean movieComingsBean = getItem(position);

//        holder.layoutItem.setVisibility(View.VISIBLE);
        holder.text.setText(movieComingsBean.rMonth + "月");
        if (position == 0) {
            holder.text.setVisibility(View.VISIBLE);
        } else {
            holder.text.setVisibility(View.GONE);
        }
        holder.itemMoviedate.setText(movieComingsBean.rDay + "日");
//        x.image().bind(holder.movieListItemIvPhoto, movieComingsBean.image);

        //Vollery加载图片
        ImageLoader imageLoader = new ImageLoader(MTimeApplication.getRequestQueue(), new MTimeImageCache());
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(holder.movieListItemIvPhoto, R.mipmap.img_default_90x90, R.mipmap.loading_failed);
        imageLoader.get(movieComingsBean.image, imageListener);

        if (movieComingsBean.rDay == day) {
            holder.movieListItemTagNew.setVisibility(View.VISIBLE);
        } else {
            holder.movieListItemTagNew.setVisibility(View.GONE);
        }
        holder.itemMoviename.setText(movieComingsBean.title);
        holder.itemWantseenNum.setText(movieComingsBean.wantedCount + "");
        holder.itemMovietype.setText("人想看-" + movieComingsBean.type + "/" + movieComingsBean.locationName);
        holder.itemMoviedirector.setText("导演: " + movieComingsBean.director);

        if (movieComingsBean.isVideo) {
            holder.buttonVedio.setVisibility(View.VISIBLE);
        } else {
            holder.buttonVedio.setVisibility(View.GONE);
        }

        if (movieComingsBean.isTicket) {
            holder.buttonPresell.setVisibility(View.VISIBLE);
        } else {
            holder.buttonPresell.setVisibility(View.GONE);
        }

        if (movieComingsBean.isFilter) {
            holder.buttonRemind.setVisibility(View.VISIBLE);
        } else {
            holder.buttonRemind.setVisibility(View.GONE);
        }

//        holder.dividerLineLong = (Ima
//        holder.dividerLineShort = (Im

        Log.e("SIZE", "Position" + position);

        return convertView;
    }

    static class ViewHolder {
        public TextView text;
        public LinearLayout layoutItem;
        public TextView itemMoviedate;
        public ImageView movieListItemIvPhoto;
        public ImageView movieListItemTagNew;
        public TextView movieCoverFilterText;
        public TextView itemMoviename;
        public TextView itemWantseenNum;
        public TextView itemMovietype;
        public TextView itemMoviedirector;
        public Button buttonPresell;
        public Button buttonRemind;
        public Button buttonVedio;
        public ImageView dividerLineLong;
        public ImageView dividerLineShort;
    }

}
