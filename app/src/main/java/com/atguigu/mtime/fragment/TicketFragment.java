package com.atguigu.mtime.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.MainActivity;
import com.atguigu.mtime.view.widget.SubTabView;

/**
 * 购票Tab页面
 * Created by HanFeng on 2015/12/6.
 */
public class TicketFragment extends Fragment implements View.OnClickListener {

    private MainActivity mainActivity;
    private TextView tvMovie, tvCinema, tvLocation;
    private ImageView ivSearch;
    private FrameLayout flContent;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private MovieFragment moviewFragment;
    private CinemaFragment cinemaFragment;
    private Bundle bundle;
    private int tabIndex = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket, null);
        tvMovie = (TextView) view.findViewById(R.id.tv_ticket_bar_movie);
        tvCinema = (TextView) view.findViewById(R.id.tv_ticket_bar_cinema);
        tvLocation = (TextView) view.findViewById(R.id.tv_ticket_bar_location);
        ivSearch = (ImageView) view.findViewById(R.id.iv_ticket_bar_search);
        flContent = (FrameLayout) view.findViewById(R.id.fl_fragment_ticket);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        setListener();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        bundle = getArguments();
        if (bundle != null) {
            int index = bundle.getInt("index");
            if (index == 0) {
                tabIndex = bundle.getInt("tabIndex");
                setSelect(index, -1);
            } else {
                setSelect(index, -1);
            }
        } else {
            setSelect(0, -1);//默认显示第一页 更新数据
        }
    }

    /**
     * 设置监听器
     */
    private void setListener() {
        tvMovie.setOnClickListener(this);
        tvCinema.setOnClickListener(this);
    }

    /**
     * 设置展示的页面
     *
     * @param i
     */
    public void setSelect(int i, int tabIndex) {
        fragmentManager = mainActivity.getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);

        switch (i) {
            case 0:
                if (moviewFragment == null) {
                    moviewFragment = new MovieFragment();
                    moviewFragment.setArguments(bundle);
                    transaction.add(R.id.fl_fragment_ticket, moviewFragment);
                } else {
                    if (tabIndex == 0 || tabIndex == 1) {
                        moviewFragment.setSelected(tabIndex);
                    }
                    transaction.show(moviewFragment);
                }
                break;
            case 1:
                if (cinemaFragment == null) {
                    cinemaFragment = new CinemaFragment();
                    transaction.add(R.id.fl_fragment_ticket, cinemaFragment);
                } else {
                    transaction.show(cinemaFragment);
                }
                break;

        }
        setTabState(i);
        transaction.commit();
    }

    /**
     * 隐藏所有已经创建的Fragment
     *
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (moviewFragment != null) {
            transaction.hide(moviewFragment);
        }
        if (cinemaFragment != null) {
            transaction.hide(cinemaFragment);
        }
    }

    /**
     * 设置顶部Tab的状态
     * @param index
     */
    private void setTabState(int index) {
        if (index == 0) {
            tvMovie.setEnabled(false);
            tvCinema.setEnabled(true);
        } else {
            tvMovie.setEnabled(true);
            tvCinema.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_ticket_bar_movie:
                setSelect(0, -1);
                setTabState(0);
                break;
            case R.id.tv_ticket_bar_cinema:
                setSelect(1, -1);
                setTabState(1);
                break;

        }
    }
}
