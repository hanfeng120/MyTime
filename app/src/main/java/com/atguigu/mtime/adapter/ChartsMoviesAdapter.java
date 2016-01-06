package com.atguigu.mtime.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.bean.ChartsMoviesBean;

import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by yui-pc on 2015/12/12.
 */
public class ChartsMoviesAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ChartsMoviesBean.MoviesBean> data;

    public ChartsMoviesAdapter(Context context, ArrayList<ChartsMoviesBean.MoviesBean> data) {
        this.data = data;
        this.context = context;
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
            convertView = View.inflate(context, R.layout.activity_charts_movies_item, null);
            holder = new ViewHolder();
            holder.ivChartsmoviesItemNum = (TextView) convertView.findViewById(R.id.iv_chartsmovies_item_num);
            holder.tvChartsmoviesItemPhoto = (ImageView) convertView.findViewById(R.id.tv_chartsmovies_item_photo);
            holder.tvChartsmoviesItemName = (TextView) convertView.findViewById(R.id.tv_chartsmovies_item_name);
            holder.tv_chartsmovies_item_rating = (TextView) convertView.findViewById(R.id.tv_chartsmovies_item_rating);
            holder.tvChartsmoviesItemNameEn = (TextView) convertView.findViewById(R.id.tv_chartsmovies_item_nameEn);
            holder.tvChartsmoviesItemDirector = (TextView) convertView.findViewById(R.id.tv_chartsmovies_item_director);
            holder.tvChartsmoviesItemActor = (TextView) convertView.findViewById(R.id.tv_chartsmovies_item_actor);
            holder.tvChartsmoviesItemReleaseDate = (TextView) convertView.findViewById(R.id.tv_chartsmovies_item_releaseDate);
            holder.tvChartsmoviesItemReleaseLocation = (TextView) convertView.findViewById(R.id.tv_chartsmovies_item_releaseLocation);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(data.get(position).rankNum<10){
            holder.ivChartsmoviesItemNum.setText("0" + data.get(position).rankNum);
        }else {
            holder.ivChartsmoviesItemNum.setText(data.get(position).rankNum + "" );
        }
        if (data.get(position).rankNum == 1) {
            holder.ivChartsmoviesItemNum.setBackgroundResource(R.mipmap.topmovie_list_num1);
        } else if (data.get(position).rankNum == 2) {
            holder.ivChartsmoviesItemNum.setBackgroundResource(R.mipmap.topmovie_list_num2);
        } else if (data.get(position).rankNum == 3) {
            holder.ivChartsmoviesItemNum.setBackgroundResource(R.mipmap.topmovie_list_num3);
        } else {
            holder.ivChartsmoviesItemNum.setBackgroundResource(R.mipmap.topmovie_list_num_normal);
        }
        x.image().bind(holder.tvChartsmoviesItemPhoto, data.get(position).posterUrl);
        holder.tvChartsmoviesItemName.setText(data.get(position).name);
        holder.tv_chartsmovies_item_rating.setText(data.get(position).rating);
        holder.tvChartsmoviesItemNameEn.setText(data.get(position).nameEn);
        holder.tvChartsmoviesItemDirector.setText("导演：" + data.get(position).director);
        holder.tvChartsmoviesItemActor.setText("主演：" + data.get(position).actor);
        holder.tvChartsmoviesItemReleaseDate.setText("上映日期" + data.get(position).releaseDate);
        holder.tvChartsmoviesItemReleaseLocation.setText(data.get(position).releaseLocation);
        Log.e("xxxx", "convertView" + convertView);
        return convertView;
    }

    static class ViewHolder {
        public String actor;//主演
        public String actor2;//导演
        public String decade;//年份
        public String director;//导演
        public int id;
        public String movieType;//类型
        public String name;//电影名称
        public String nameEn;//英文名字
        public String posterUrl;//封面地址
        public int rankNum;
        public int rating;//评分
        public String releaseDate;//上映日期
        public String releaseLocation;//国家
        public String remark;//影片介绍


        private TextView ivChartsmoviesItemNum;
        private ImageView tvChartsmoviesItemPhoto;
        // private LinearLayout chartsmoviesTitle;
        private TextView tvChartsmoviesItemName;
        private TextView tv_chartsmovies_item_rating;
        private TextView tvChartsmoviesItemNameEn;
        private TextView tvChartsmoviesItemDirector;
        private TextView tvChartsmoviesItemActor;
        private TextView tvChartsmoviesItemReleaseDate;
        private TextView tvChartsmoviesItemReleaseLocation;


    }
}
