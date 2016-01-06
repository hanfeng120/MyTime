package com.atguigu.mtime.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.bean.ChartsListBean;

import java.util.ArrayList;

/**
 * 发现Tab——排行榜 适配器
 * Created by 申瑞芹 on 2015/12/9.
 */
public class ChartsPageAdapter extends BaseAdapter {


    /**
     * 保存
     */
    private static final String READ_ARRAY_ID = "read_array_id";
    private static final String TAG = ChartsPageAdapter.class.getSimpleName();
    private Context context;
    private ArrayList<ChartsListBean.TopLists> data;

    public ChartsPageAdapter(Context context, ArrayList<ChartsListBean.TopLists> data) {
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
                    R.layout.item_charts_page, null);
            holder = new ViewHolder();
            holder.tv_topListNameCn = (TextView) convertView
                    .findViewById(R.id.tv_topListNameCn);
            holder.summary = (TextView) convertView
                    .findViewById(R.id.summary);
            // 容器和View关联起来
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        /**
         * 通过位置得到对应的数据
         */
        ChartsListBean.TopLists topLists = data.get(position);

        Log.e(TAG, "possiton: " + topLists.toString());

        holder.tv_topListNameCn.setText(topLists.getTopListNameCn());
        holder.summary.setText(topLists.getSummary());

        return convertView;
    }


    static class ViewHolder {
        TextView tv_topListNameCn;
        TextView summary;

    }

}
