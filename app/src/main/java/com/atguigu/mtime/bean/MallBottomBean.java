package com.atguigu.mtime.bean;

import java.util.ArrayList;

/**
 * 商城底部数据
 * Created by yui-pc on 2015/12/10.
 */
public class MallBottomBean {
    public int count;
    public String goodsIds;
    public ArrayList<GoodsListBean> goodsList;
    public int pageCount;

    public static class GoodsListBean {
        public String background;
        public int goodsId;
        public String iconText;
        public String image;
        public String longName;
        public int marketPrice;
        public int minSalePrice;
        public String name;
        public String url;
    }
}
