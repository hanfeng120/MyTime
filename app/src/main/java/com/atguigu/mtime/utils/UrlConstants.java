package com.atguigu.mtime.utils;

import android.app.Activity;
import android.view.View;

import com.atguigu.mtime.activity.base.BaseActivity;

/**
 * Url常量接口，属性都是 public static final，因此不用再声明
 * Created by HanFeng on 2015/12/6.
 */
public interface UrlConstants {

    /**
     * 首页选座购票以下的数据
     */
    String HOME_LIST_DATA = "http://api.m.mtime.cn/PageSubArea/GetFirstPageAdvAndNews.api";
    /**
     * 影评listview的URL
     */
    String REVIEW_LISTVIEW = "http://api.m.mtime.cn/MobileMovie/Review.api?needTop=false";

    String HOME_MOVIE_DATA = "http://api.m.mtime.cn/PageSubArea/HotPlayMovies.api?locationId=290";
    /**
     * 商城首页的数据
     */
    String MALL_LIST_DATA = "http://api.m.mtime.cn/PageSubArea/MarketFirstPageNew.api?lastTime={0}";
    /**
     * 商城底部列表数据
     */
    String MALL_BOTTOM_DATA = "http://api.m.mtime.cn/ECommerce/RecommendProducts.api?pageIndex=1&goodsIds=";
    /**
     * 预告片的URL
     */
    String DISCOVER_PAGE_PREVUE_URL = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";


    /**
     * 发现页面的四个页面的 顶部的数据
     */
    String DISCOVER_PAGE_TOP_URL = "http://api.m.mtime.cn/PageSubArea/GetRecommendationIndexInfo.api";


    /**
     * 发现页面顶部刷新的 数据地址
     */
    String DISCOVER_PAGE_TOP_REFRESH = "http://api.m.mtime.cn/PageSubArea/PullMovieAdvWordAndCouponActivities.api";

    /**
     * 排行榜的URL
     */
    String DISCOVER_PAGE_CHARTS_URL = "http://api.m.mtime.cn/TopList/TopListOfAll.api?pageIndex=";

    /**
     * 排行榜华语和失望排行榜的数据
     */
    String DISCOVER_PAGE_CHARTS_MOVIES_TOP = "http://api.m.mtime.cn/TopList/TopListDetailsByRecommend.api?pageIndex=1&pageSubAreaID=";

    /**
     * 排行榜每条Item的数据
     */
    String DISCOVER_PAGE_CHARTS_MOVIES_ITEM = "http://api.m.mtime.cn/TopList/TopListDetails.api?pageIndex=1&topListId=";

    /**
     * 发现页面中的 新闻页面的URL
     */
    String DISCOVER_PAGE_NEWS_URL = "http://api.m.mtime.cn/News/NewsList.api?pageIndex=";

    /**
     * 影院的URL
     */
    String CINEMA_URL = "http://api.m.mtime.cn/OnlineLocationCinema/OnlineCinemasByCity.api?locationId=290&deviceToken={1}";
    /**
     * 排行榜的URL
     */
    String GOLDLE_URL = "http://api.m.mtime.cn/TopList/TopListFixedNew.api";
    /**
     * 全球排行榜的URL(前半部分)
     */
    String GOLDLE_ITEM_URL ="http://api.m.mtime.cn/TopList/TopListDetailsByRecommend.api?pageIndex=1&pageSubAreaID=";
    /**
     * 全球排行榜的URL(后半部分)
     */
    String GOLDLE_ITEM1_URL ="&locationId={2}";

    /**
     * 正在热映数据
     */
    String TICKET_HOT_SHOWING = "http://api.m.mtime.cn/Showtime/LocationMovies.api?locationId=290";

    /**
     * 即将上映数据
     */
    String TICKET_INCOMING = "http://api.m.mtime.cn/Movie/MovieComingNew.api?locationId=290";

    /**
     * 电影详情页
     */
    String DETAIL_MOVIE = "http://api.m.mtime.cn/Showtime/MovieDetail.api";

    /**
     * 电影详情页用户短评
     */
    String DETAIL_MOVIE_SHORT_COMMENT = "http://api.m.mtime.cn/Showtime/MovieComments.api?";

    /**
     * 电影详情页精选评论
     */
    String DETAIL_MOVIE_COMMENT = "http://api.m.mtime.cn/Movie/HotLongComments.api?";

    /**
     * 电影详情页相关新闻
     */
    String DETAIL_MOVIE_NEWS = "http://api.m.mtime.cn/Movie/News.api?movieId=216495&pageIndex=1";

    /**
     * 电影详情页视频
     */
    String DETAIL_MOVIE_VIDEOS = "http://api.m.mtime.cn/Movie/Video.api?";

    /**
     * 电影详情页视频
     */
    String DETAIL_MOVIE_IMAGES = "http://api.m.mtime.cn/Movie/ImageAll.api?movieId=";

    /**
     * 发现界面新闻详情页
     */
    String DETAIL_NEWS = "http://api.m.mtime.cn/News/Detail.api?newsId=";

    /**
     * 发现界面评论详情页
     */
    String DETAIL_REVIEW = "http://api.m.mtime.cn/Review/Detail.api?reviewId=";

    /**
     * 发现界面评论详情页
     */
    String CHARTS_TOP_LIST = "http://api.m.mtime.cn/TopList/TopListFixedNew.api";
}
