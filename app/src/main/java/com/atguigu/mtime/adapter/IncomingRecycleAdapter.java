package com.atguigu.mtime.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.bean.MovieIncomingBean;
import com.atguigu.mtime.utils.MTimeUtils;

import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by HanFeng on 2015/12/11.
 */
public class IncomingRecycleAdapter extends RecyclerView.Adapter<IncomingRecycleAdapter.ViewHolder>  {

    private Context context;
    private ArrayList<MovieIncomingBean.AttentionBean> data;
    private LayoutInflater inflater;

    public IncomingRecycleAdapter(Context context, ArrayList<MovieIncomingBean.AttentionBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.list_header_film_off_item, null);
//        View rootView = inflater.inflate(R.layout.item_incoming, null);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieIncomingBean.AttentionBean attentionBean = getItem(position);
        if (MTimeUtils.getYear() != attentionBean.rYear) {
            holder.itemDate.setText(attentionBean.rYear + "年" + attentionBean.rMonth + "月" + attentionBean.rDay + "日");
        } else {
            holder.itemDate.setText(attentionBean.rMonth + "月" + attentionBean.rDay + "日");
        }

        x.image().bind(holder.movieListItemIvPhoto, attentionBean.image);

        if (MTimeUtils.getDay() == attentionBean.rDay) {
            holder.movieListItemTagNew.setVisibility(View.VISIBLE);
        } else {
            holder.movieListItemTagNew.setVisibility(View.GONE);
        }
        holder.itemMoviename.setText(attentionBean.title);
        holder.itemWantseenNum.setText(attentionBean.wantedCount + "");
        holder.itemMovietype.setText("人想看-" + attentionBean.type);
        holder.itemMoviedirector.setText("导演: " + attentionBean.director);
        holder.itemMovieactor.setText("演员: " + attentionBean.actor1 + "," + attentionBean.actor2);
        if (attentionBean.isVideo) {
            holder.buttonVedio.setVisibility(View.VISIBLE);
        } else {
            holder.buttonVedio.setVisibility(View.GONE);
        }
        if (attentionBean.isTicket) {
            holder.buttonPresell.setVisibility(View.VISIBLE);
        } else {
            holder.buttonPresell.setVisibility(View.GONE);
        }
        if (attentionBean.isFilter) {
            holder.buttonRemind.setVisibility(View.VISIBLE);
        } else {
            holder.buttonRemind.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public MovieIncomingBean.AttentionBean getItem(int position){
        return data.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout llIncoming;
        public TextView itemDate;
        public ImageView movieListItemIvPhoto;
        public ImageView movieListItemTagNew;
        public TextView itemMoviename;
        public TextView itemWantseenNum;
        public TextView itemMovietype;
        public TextView itemMoviedirector;
        public TextView itemMovieactor;
        public Button buttonVedio;
        public Button buttonPresell;
        public Button buttonRemind;

        public ViewHolder(View itemView) {
            super(itemView);

            llIncoming = (LinearLayout)itemView.findViewById( R.id.ll_incoming );
            itemDate = (TextView)itemView.findViewById( R.id.item_date );
            movieListItemIvPhoto = (ImageView)itemView.findViewById( R.id.movie_list_item_iv_photo );
            movieListItemTagNew = (ImageView)itemView.findViewById( R.id.movie_list_item_tag_new );
            itemMoviename = (TextView)itemView.findViewById( R.id.item_moviename );
            itemWantseenNum = (TextView)itemView.findViewById( R.id.item_wantseen_num );
            itemMovietype = (TextView)itemView.findViewById( R.id.item_movietype );
            itemMoviedirector = (TextView)itemView.findViewById( R.id.item_moviedirector );
            itemMovieactor = (TextView)itemView.findViewById( R.id.item_movieactor );
            buttonVedio = (Button)itemView.findViewById( R.id.button_vedio );
            buttonPresell = (Button)itemView.findViewById( R.id.button_presell );
            buttonRemind = (Button)itemView.findViewById( R.id.button_remind );

        }
    }

}
