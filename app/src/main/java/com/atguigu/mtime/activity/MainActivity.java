package com.atguigu.mtime.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.KeyEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.atguigu.mtime.R;
import com.atguigu.mtime.fragment.DiscoverFragment;
import com.atguigu.mtime.fragment.HomeFragment;
import com.atguigu.mtime.fragment.MallFragment;
import com.atguigu.mtime.fragment.MeFragment;
import com.atguigu.mtime.fragment.TicketFragment;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 * Create by 赵迅艺 at 2015/12/6
 */
public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton rbHome, rbTicket, rbMall, rbDiscover, rbMe;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private HomeFragment homeFragment;
    private TicketFragment ticketFragment;
    private MallFragment mallFragment;
    private DiscoverFragment discoverFragment;
    private MeFragment meFragment;

    private int pageIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
        initData();
        initXUtils();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.rg_main);
        rbHome = (RadioButton) findViewById(R.id.rb_main_home);
        rbTicket = (RadioButton) findViewById(R.id.rb_main_ticket);
        rbMall = (RadioButton) findViewById(R.id.rb_main_mall);
        rbDiscover = (RadioButton) findViewById(R.id.rb_main_discover);
        rbMe = (RadioButton) findViewById(R.id.rb_main_me);
    }

    /**
     * 设置监听器
     */
    private void setListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_main_home:
                        setSelect(0, pageIndex);
                        break;
                    case R.id.rb_main_ticket:
                        setSelect(1, pageIndex);
                        break;
                    case R.id.rb_main_mall:
                        setSelect(2, pageIndex);
                        break;
                    case R.id.rb_main_discover:
                        setSelect(3, pageIndex);
                        break;
                    case R.id.rb_main_me:
                        setSelect(4, pageIndex);
                        break;
                }
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        rbHome.setChecked(true);
    }

    /**
     * 初始化xUtils框架
     */
    private void initXUtils() {
        x.Ext.init(getApplication());
        x.Ext.setDebug(true); // 是否输出debug日志
    }

    /**
     * 展示页面
     *
     * @param i 要显示的Fragment的页数
     */
    private void setSelect(int i, int index) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);

        switch (i) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.fl_main, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                if (ticketFragment == null) {
                    ticketFragment = new TicketFragment();
                    transaction.add(R.id.fl_main, ticketFragment);
                    setBundle2Ticket(index, ticketFragment);
                } else {
                    setIndex2Ticket(index);
                    transaction.show(ticketFragment);
                }
                break;
            case 2:
                if (mallFragment == null) {
                    mallFragment = new MallFragment();
                    transaction.add(R.id.fl_main, mallFragment);
                } else {
                    transaction.show(mallFragment);
                }
                break;
            case 3:
                if (discoverFragment == null) {
                    discoverFragment = new DiscoverFragment();
                    setBundle2Discover(index, discoverFragment);
                    transaction.add(R.id.fl_main, discoverFragment);
                } else {
                    discoverFragment.setSelected(index);
                    transaction.show(discoverFragment);
                }
                break;
            case 4:
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    transaction.add(R.id.fl_main, meFragment);
                } else {
                    transaction.show(meFragment);
                }
                break;
        }
        pageIndex = -1;
        transaction.commit();
    }

    /**
     * 隐藏所有已经创建的Fragment
     *
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (ticketFragment != null) {
            transaction.hide(ticketFragment);
        }
        if (mallFragment != null) {
            transaction.hide(mallFragment);
        }
        if (discoverFragment != null) {
            transaction.hide(discoverFragment);
        }
        if (meFragment != null) {
            transaction.hide(meFragment);
        }
    }

    /**
     * 设置展示的Tab和对应的Tab中的页面
     *
     * @param tab   tab页码数
     * @param index tab中具体的页码
     */
    public void setRadioButtonChecked(int tab, int index) {
        pageIndex = index;
        switch (tab) {
            case 0:
                break;
            case 1:
                rbTicket.setChecked(true);
                break;
            case 2:
                rbMall.setChecked(true);
                break;
            case 3:
                rbDiscover.setChecked(true);
                break;
            case 4:
                break;
        }
    }

    /**
     * 传递数据到指定的Fragment
     *
     * @param fragment
     */
    private void setBundle2Discover(int index, Fragment fragment) {
        if (index != -1) {
            Bundle bundle = new Bundle();
            bundle.putInt("index", index);
            fragment.setArguments(bundle);
        }
    }

    /**
     * 跳转到TicketFragment中指定的页面
     *
     * @param index
     */
    private void setIndex2Ticket(int index) {
        if (index == 0) {
            ticketFragment.setSelect(0, 0);
        } else if (index == 1) {
            ticketFragment.setSelect(0, 1);
        } else if (index == 2) {
            ticketFragment.setSelect(1, -1);
        }
    }

    /**
     * 传递数据到TicketFragment，测试SVN
     *
     * @param index
     * @param ticketFragment
     */
    private void setBundle2Ticket(int index, TicketFragment ticketFragment) {
        if (index != -1) {
            if (index == 0) {
                Bundle bundle = new Bundle();
                bundle.putInt("index", 0);
                bundle.putInt("tabIndex", 0);
            } else if (index == 1) {
                Bundle bundle = new Bundle();
                bundle.putInt("index", 0);
                bundle.putInt("tabIndex", 1);
            } else if (index == 2) {
                Bundle bundle = new Bundle();
                bundle.putInt("index", 1);
                bundle.putInt("tabIndex", -1);
            }
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(this)
                    .setMessage("确定要退出吗？")
                    .setPositiveButton("确定", new Dialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .setCancelable(false).show();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
