package com.atguigu.mtime.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.GoldleActivity;
import com.atguigu.mtime.activity.MainActivity;

/**
 * 我Tab页面
 * Created by HanFeng on 2015/12/6.
 */
public class MeFragment extends Fragment {

    private MainActivity mainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mainActivity, R.layout.fragment_mefragment,null);
        Button btn = (Button) view.findViewById(R.id.btn_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity, GoldleActivity.class);
                mainActivity.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("TAG", "MeFragment init");
    }

}
