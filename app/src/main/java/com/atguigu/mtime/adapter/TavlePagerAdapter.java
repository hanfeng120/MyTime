package com.atguigu.mtime.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.bean.GoldleItemBean;

import org.xutils.x;

import java.util.List;

/**
 * Created by yiran on 2015/12/13.
 * 全球排行榜
 */
public class TavlePagerAdapter extends BaseAdapter {

    private List<GoldleItemBean.MoviesData> movies;
    private Context context;



    public TavlePagerAdapter(Context context, List<GoldleItemBean.MoviesData> movies) {
        this.movies = movies;
        this.context = context;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.goldle_item,null);

            holder.tvGoldleNumber = (TextView)convertView.findViewById(R.id.tv_goldle_number);
            holder.ivGoldleImage = (ImageView)convertView.findViewById(R.id.iv_goldle_image);
            holder.tvGoldleTitle = (TextView)convertView.findViewById(R.id.tv_goldle_title);
            holder.tvGoldleNumber1 = (TextView)convertView.findViewById(R.id.tv_goldle_number1);
            holder.tvGoldleContent = (TextView)convertView.findViewById(R.id.tv_goldle_content);
            holder.tvGoldleOne = (TextView)convertView.findViewById(R.id.tv_goldle_one);
            holder.tvGoldleAll = (TextView)convertView.findViewById(R.id.tv_goldle_all);
            holder.btnGoldleBuy = (Button)convertView.findViewById(R.id.btn_goldle_buy);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        GoldleItemBean.MoviesData moviesData =movies.get(position);
        if(movies.get(position).rankNum == 1){
            holder.tvGoldleNumber.setBackgroundResource(R.mipmap.topmovie_list_numa);
            holder.tvGoldleNumber1.setBackgroundResource(R.mipmap.cinema_subshop_heart);
        }else
        if(movies.get(position).rankNum == 2){
            holder.tvGoldleNumber.setBackgroundResource(R.mipmap.topmovie_list_numb);
        }else
        if(movies.get(position).rankNum == 3){
            holder.tvGoldleNumber.setBackgroundResource(R.mipmap.topmovie_list_numc);
        }else{
            holder.tvGoldleNumber.setBackgroundResource(R.mipmap.topmovie_list_numd);
        }
        if(position == 9){
            holder.btnGoldleBuy.setText("购票");
        }
        if(movies.get(position).rankNum < 10){
            holder.tvGoldleNumber.setText("0" + moviesData.rankNum);
        }else{
            holder.tvGoldleNumber.setText(moviesData.rankNum + "");
        }

        //holder.tvGoldleNumber.setTextColor(0xffbbbb);
        x.image().bind(holder.ivGoldleImage, moviesData.posterUrl);
        holder.tvGoldleTitle.setText(moviesData.name);
        holder.tvGoldleContent.setText(moviesData.nameEn);
        holder.tvGoldleOne.setText(moviesData.weekBoxOffice);
        holder.tvGoldleAll.setText(moviesData.totalBoxOffice);
        holder.tvGoldleNumber1.setText(moviesData.rating);



        return convertView;
    }


    class ViewHolder{
        private TextView tvGoldleNumber;
        private ImageView ivGoldleImage;
        private TextView tvGoldleTitle;
        private TextView tvGoldleNumber1;
        private TextView tvGoldleContent;
        private TextView tvGoldleOne;
        private TextView tvGoldleAll;
        private Button btnGoldleBuy;



    }
}











