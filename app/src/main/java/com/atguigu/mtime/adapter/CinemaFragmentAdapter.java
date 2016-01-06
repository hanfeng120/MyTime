package com.atguigu.mtime.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.MainActivity;
import com.atguigu.mtime.bean.CinemaFragmentBean;

import java.util.List;

/**
 * Created by yiran on 2015/12/10.
 */
public class CinemaFragmentAdapter extends BaseAdapter {
    private MainActivity mainActivity;
    private List<CinemaFragmentBean.CinemaListData> data;
    public CinemaFragmentAdapter(MainActivity mainActivity, List<CinemaFragmentBean.CinemaListData> data) {
        this.mainActivity = mainActivity;
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
        ViewHolder viewHolder ;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(mainActivity, R.layout.cinema_listview_item,null);
            viewHolder.tvCinemaItemTitle = (TextView) convertView.findViewById(R.id.tv_cinema_item_title);
            viewHolder.tvCinemaItemContent = (TextView)convertView.findViewById(R.id.tv_cinema_item_content);
            viewHolder.ivCinemaItemHas3d = (ImageView)convertView.findViewById(R.id.iv_cinema_item_has3d);
            viewHolder.ivCinemaItemHasvip = (ImageView)convertView.findViewById(R.id.iv_cinema_item_hasvip);
            viewHolder.ivCinemaItemHaswifi = (ImageView)convertView.findViewById(R.id.iv_cinema_item_haswifi);
            viewHolder.ivCinemaItemHasmax = (ImageView)convertView.findViewById(R.id.iv_cinema_item_hasmax);
            viewHolder.ivCinemaItemRest = (ImageView)convertView.findViewById(R.id.iv_cinema_item_rest);
            viewHolder.tvCinemaItemMeter = (TextView)convertView.findViewById(R.id.tv_cinema_item_meter);
            viewHolder.tvCinemaItemPrice = (TextView)convertView.findViewById(R.id.tv_cinema_item_price);


            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }


        CinemaFragmentBean.CinemaListData cinemaListData =data.get(position);
        viewHolder.tvCinemaItemTitle.setText(cinemaListData.cinameName);
        viewHolder.tvCinemaItemContent.setText(cinemaListData.address);

        if(cinemaListData.feature.has3D == 1){
            viewHolder.ivCinemaItemHas3d.setVisibility(View.VISIBLE);
        }
        if (cinemaListData.feature.hasIMAX == 1){
            viewHolder.ivCinemaItemHasmax.setVisibility(View.VISIBLE);
        }
         if (cinemaListData.feature.hasPark == 1){
        viewHolder.ivCinemaItemRest.setVisibility(View.VISIBLE);

        }
        if (cinemaListData.feature.hasVIP == 1){
            viewHolder.ivCinemaItemHasvip.setVisibility(View.VISIBLE);

        }
        if (cinemaListData.feature.hasWifi == 1){
            viewHolder.ivCinemaItemHaswifi.setVisibility(View.VISIBLE);
        }

        viewHolder.tvCinemaItemMeter.setText(7.5*(position + 1) +"Km" );
        viewHolder.tvCinemaItemPrice.setText(9.5*(position + 1) + "元");
        return convertView;
    }

    class ViewHolder{
        private TextView tvCinemaItemTitle;
        private TextView tvCinemaItemContent;
        private ImageView ivCinemaItemHas3d;
        private ImageView ivCinemaItemHasvip;
        private ImageView ivCinemaItemHaswifi;
        private ImageView ivCinemaItemHasmax;
        private ImageView ivCinemaItemRest;
        private TextView tvCinemaItemMeter;
        private TextView tvCinemaItemPrice;




    }
}
