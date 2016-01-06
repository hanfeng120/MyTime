package com.atguigu.mtime.bean;

import java.util.ArrayList;

/**
 * 商城的主体数据
 * Created by yui-pc on 2015/12/7.
 */
public class MallHomeBean {

    public ArrayList<CategoryBean> category;
    public CellABean cellA;
    public CellBBean cellB;
    public CellCBean cellC;
    public NavigatorFirthIconBean navigatorFirthIcon;
    public ArrayList<NavigatorIconBean> navigatorIcon;
    public ArrayList<ScrollImgBean> scrollImg;
    public ArrayList<TopicBean> topic;

    /**
     * 含(玩具模型/数码配件/服饰配件/家居生活)的ArrayListView商品Bean
     */
    public static class CategoryBean {
        public String colorValue;
        public int goodsId;
        public String image;
        public String imageUrl;
        public String moreUrl;
        public String name;
        public ArrayList<SubArrayListBean> subList;

        @Override
        public String toString() {
            return "CategoryBean{" +
                    "colorValue='" + colorValue + '\'' +
                    ", goodsId=" + goodsId +
                    ", image=" + image +
                    ", imageUrl='" + imageUrl + '\'' +
                    ", moreUrl='" + moreUrl + '\'' +
                    ", name='" + name + '\'' +
                    ", subList=" + subList +
                    '}';
        }
    }

    public static class SubArrayListBean {
        public int goodsId;
        public String image;
        public String title;
        public String url;
    }

    /**
     * 导航下方闪购特惠A的Bean
     */
    public static class CellABean {

        public int goodsId;
        public String img;
        public int longTime;
        public int startTime;
        public String subTitle;
        public String title;
        public String titleColor;
        public String url;
        public int warmup;


    }

    /**
     * 导航下方时光预售B的Bean
     */
    public static class CellBBean {
        public int goodsId;
        public String img;
        public int longTime;
        public int startTime;
        public String subTitle;
        public String title;
        public String titleColor;
        public String url;
        public int warmup;
    }

    /**
     * 导航下方新品主推和原创主推C的Bean
     */
    public static class CellCBean {
        public ArrayList<ArrayListBean> list;


    }

    public static class ArrayListBean {
        public String image;
        public String subTitle;
        public String title;
        public String titleColor;
        public String url;

    }

    /**
     * 四大导航之一家居的Bean
     */
    public static class NavigatorFirthIconBean {
        public String iconTitle1;
        public String iconTitle2;
        public String img1;
        public String img2;
        public String url;
    }

    /**
     * 四大导航中三个(玩具/数码/服饰)Bean
     */
    public static class NavigatorIconBean {
        public String iconTitle;
        public String image;
        public String url;
    }

    /**
     * ViewPager的Bean
     */
    public static class ScrollImgBean {
        public String image;
        public String url;
    }

    /**
     * 横向滑动的ScrollView的Bean
     */
    public static class TopicBean {
        public String backgroupImage;
        public String checkedImage;
        public int goodsId;
        public ArrayList<SubArrayListBean> subList;
        public String titleCn;
        public String titleEn;
        public String uncheckImage;
        public String url;
    }
}
