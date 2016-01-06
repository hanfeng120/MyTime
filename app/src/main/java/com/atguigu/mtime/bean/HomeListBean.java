package com.atguigu.mtime.bean;

/**
 * HomeFragment分类型的ListView数据Bean
 *
 * Created by HanFeng on 2015/12/8.
 */
public class HomeListBean {

    public int type;
    public String title;
    public String subTitle;
    public HomeBean homeBean;

    public HomeListBean() {
    }

    public HomeListBean(int type, String title, String subTitle, HomeBean homeBean) {
        this.type = type;
        this.title = title;
        this.subTitle = subTitle;
        this.homeBean = homeBean;
    }
}
