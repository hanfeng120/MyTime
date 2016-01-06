package com.atguigu.mtime.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.MTimeApplication;
import com.atguigu.mtime.bean.HotShowingBean;
import com.atguigu.mtime.utils.MTimeImageCache;
import com.atguigu.mtime.utils.MTimeImageLoader;
import com.atguigu.mtime.utils.MTimeUtils;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by HanFeng on 2015/12/10.
 */
public class HotShowingBaseAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<HotShowingBean.ShowingMovieBean> data;
    private LayoutInflater inflater;
    private MTimeImageLoader imageLoader;

    public HotShowingBaseAdapter(Context context, ArrayList<HotShowingBean.ShowingMovieBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
        imageLoader = new MTimeImageLoader();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public HotShowingBean.ShowingMovieBean getItem(int position) {
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
            convertView = inflater.inflate(R.layout.adapter_filmlist_on, null);
            holder.movieListItemIvPhoto = (ImageView) convertView.findViewById(R.id.movie_list_item_iv_photo);
            holder.movieListItemTagNew = (ImageView) convertView.findViewById(R.id.movie_list_item_tag_new);
            holder.movieListItemMoviename = (TextView) convertView.findViewById(R.id.movie_list_item_moviename);
            holder.movieListItemScoreView = (TextView) convertView.findViewById(R.id.movie_list_item_score_view);
            holder.movieListItemDesc = (TextView) convertView.findViewById(R.id.movie_list_item_desc);
            holder.movieListItemDescTag = (ImageView) convertView.findViewById(R.id.movie_list_item_desc_tag);
            holder.movieListItemMovietype = (TextView) convertView.findViewById(R.id.movie_list_item_movietype);
            holder.movieListItemDate = (TextView) convertView.findViewById(R.id.movie_list_item_date);
            holder.movieListItemShowdata = (TextView) convertView.findViewById(R.id.movie_list_item_showdata);
            holder.movieListItemTag3d = (ImageView) convertView.findViewById(R.id.movie_list_item_tag_3d);
            holder.movieListItemTagImax = (ImageView) convertView.findViewById(R.id.movie_list_item_tag_imax);
            holder.movieListItemTagLarget = (ImageView) convertView.findViewById(R.id.movie_list_item_tag_larget);
            holder.buttonPay = (Button) convertView.findViewById(R.id.button_pay);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        /**
         * 得到每一个Item的Bean
         */
        HotShowingBean.ShowingMovieBean showingMovieBean = getItem(position);

        holder.movieListItemIvPhoto.setTag(showingMovieBean.img);
        holder.movieListItemIvPhoto.setImageResource(R.mipmap.img_default);
        imageLoader.loadImageWithTag(holder.movieListItemIvPhoto, showingMovieBean.img, 300, 540);

        holder.movieListItemMoviename.setText(showingMovieBean.tCn);
        holder.movieListItemScoreView.setText(showingMovieBean.r);

        if (TextUtils.isEmpty(showingMovieBean.commonSpecial)) {
            holder.movieListItemDescTag.setVisibility(View.GONE);
            holder.movieListItemDesc.setText(showingMovieBean.wantedCount + "");
            holder.movieListItemMovietype.setText("人想看-" + showingMovieBean.movieType);
        } else {
            holder.movieListItemDescTag.setVisibility(View.VISIBLE);
            holder.movieListItemDesc.setText(showingMovieBean.commonSpecial);
        }

        holder.movieListItemDate.setText(MTimeUtils.getDateFromShowing(showingMovieBean.rd));
        holder.movieListItemShowdata.setText("今日" + showingMovieBean.NearestCinemaCount + "家影院上映" + showingMovieBean.NearestShowtimeCount + "场");

        if (showingMovieBean.is3D) {
            holder.movieListItemTag3d.setVisibility(View.VISIBLE);
        } else {
            holder.movieListItemTag3d.setVisibility(View.GONE);
        }
        if (showingMovieBean.isIMAX) {
            holder.movieListItemTagImax.setVisibility(View.VISIBLE);
        } else {
            holder.movieListItemTagImax.setVisibility(View.GONE);
        }
        if (showingMovieBean.isDMAX) {
            holder.movieListItemTagLarget.setVisibility(View.VISIBLE);
        } else {
            holder.movieListItemTagLarget.setVisibility(View.GONE);
        }
        if (showingMovieBean.isNew) {
            holder.movieListItemTagNew.setVisibility(View.VISIBLE);
        } else {
            holder.movieListItemTagNew.setVisibility(View.GONE);
        }

        return convertView;
    }

    static class ViewHolder {
        public ImageView movieListItemIvPhoto;
        public ImageView movieListItemTagNew;
        public TextView movieListItemMoviename;
        public TextView movieListItemScoreView;
        public TextView movieListItemDesc;
        public ImageView movieListItemDescTag;
        public TextView movieListItemMovietype;
        public TextView movieListItemDate;
        public TextView movieListItemShowdata;
        public ImageView movieListItemTag3d;
        public ImageView movieListItemTagImax;
        public ImageView movieListItemTagLarget;
        public Button buttonPay;
    }

}
