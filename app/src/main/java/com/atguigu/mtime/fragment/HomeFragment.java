package com.atguigu.mtime.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.MainActivity;
import com.atguigu.mtime.activity.MovieDetailActivity;
import com.atguigu.mtime.adapter.HomeBaseAdapter;
import com.atguigu.mtime.adapter.HomeGalleryAdapter;
import com.atguigu.mtime.adapter.HomePagerAdapter;
import com.atguigu.mtime.bean.HomeBean;
import com.atguigu.mtime.bean.HomeMovieBean;
import com.atguigu.mtime.utils.DensityUtil;
import com.atguigu.mtime.utils.FastBlur;
import com.atguigu.mtime.utils.UrlConstants;
import com.atguigu.mtime.view.ObservableScrollView;
import com.atguigu.mtime.view.widget.LoadingDialog;
import com.atguigu.mtime.view.widget.LoadingView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.FileOutputStream;
import java.util.ArrayList;

import at.technikum.mti.fancycoverflow.FancyCoverFlow;

/**
 * 首页Tab页面
 * Created by HanFeng on 2015/12/6.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final int WHAT_UPDATE_ADV = 0;
    private MainActivity mainActivity;
    private ListView listView;
    private ImageView ivHomeOneSubfifth;
    private ImageView ivHomeOneSubfirst;
    private ImageView ivHomeOneSubsecond;
    private ImageView ivHomeOneThird;
    private ViewPager vpHomeTwo;
    private ImageView ivHomeTwoLeftind;
    private ImageView ivHomeTwoRightind;
    private ImageView ivHomeThreeHome0;
    private TextView tvHomeThreeTitle0;
    private TextView tvHomeThreeSubtitle0;
    private TextView tvHomeThreeTime0;
    private ImageView ivHomeThreeHome1;
    private TextView tvHomeThreeTitle1;
    private TextView tvHomeThreeSubtitle1;
    private TextView tvHomeThreeTime1;
    private TextView tvHomeThreeTitle2;
    private TextView tvHomeThreeSubtitle2;
    private ImageView ivHomeThreeHome20;
    private ImageView ivHomeThreeHome21;
    private ImageView ivHomeThreeHome22;
    private TextView tvHomeThreeTime2;
    private TextView tvHomeThreeNews;
    private TextView tvHomeThreePrevue;
    private TextView tvHomeThreeCharts;
    private TextView tvHomeThreeReview;
    private ImageView ivHomeFour;
    private TextView tvHomeFourTitle;
    private ImageView ivHomeFourActor;
    private TextView tvHomeFourSubtitle;
    private TextView tvHomeFourActorEn;
    private TextView tvHomeFourActor;
    private LinearLayout inHomeHotMovie;
    private RelativeLayout rlHomeHotMovie;
    private RelativeLayout rlHomeHotPoints;
    private RelativeLayout rlHomeHotMall;
    private ObservableScrollView scrollView;
    private FancyCoverFlow gallery;

    private ImageView homeHotMovieBg;
    private TextView homeMovieName;
    private TextView homeMovieScoreView;
    private ImageView homeLineGreen;
    private TextView homeOnecomment;
    private LinearLayout homeHotmovieNumLayout;
    private TextView homeHotmovieNum;
    private LinearLayout homeComingmovieNumLayout;
    private TextView comingmoiveNum;
    private LinearLayout homeIncomingNumLayout;
    private TextView homeIncomingNum;
    private RelativeLayout homeButLayout;
    private Button homeButPay;
    private Button homeButMovieinfo;

    private ImageView ivHomeTitleBarBG;

    private ArrayList<HomeBean.HomeHotPoints> data;
    private ArrayList<HomeBean.HomeAdvBean> advData;
    private HomeBean homeBean;
    private HomeMovieBean homeMovieBean;
    private ArrayList<Bitmap> bitmaps;//顶部图片数据
    private LoadingDialog loadingDialog;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_UPDATE_ADV:
                    int index = vpHomeTwo.getCurrentItem() + 1;
                    vpHomeTwo.setCurrentItem(index);
                    handler.sendEmptyMessageDelayed(WHAT_UPDATE_ADV, 4000);
                    break;
            }
        }
    };
    private int headerViewHeight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, null);
        listView = (ListView) rootView.findViewById(R.id.lv_home);
        ivHomeOneSubfifth = (ImageView) rootView.findViewById(R.id.iv_home_one_subfifth);
        ivHomeOneSubfirst = (ImageView) rootView.findViewById(R.id.iv_home_one_subfirst);
        ivHomeOneSubsecond = (ImageView) rootView.findViewById(R.id.iv_home_one_subsecond);
        ivHomeOneThird = (ImageView) rootView.findViewById(R.id.iv_home_one_third);
        vpHomeTwo = (ViewPager) rootView.findViewById(R.id.vp_home_two);
        ivHomeTwoLeftind = (ImageView) rootView.findViewById(R.id.iv_home_two_leftind);
        ivHomeTwoRightind = (ImageView) rootView.findViewById(R.id.iv_home_two_rightind);
        ivHomeFour = (ImageView) rootView.findViewById(R.id.iv_home_four);
        tvHomeFourTitle = (TextView) rootView.findViewById(R.id.tv_home_four_title);
        ivHomeFourActor = (ImageView) rootView.findViewById(R.id.iv_home_four_actor);
        tvHomeFourSubtitle = (TextView) rootView.findViewById(R.id.tv_home_four_subtitle);
        tvHomeFourActorEn = (TextView) rootView.findViewById(R.id.tv_home_four_actor_en);
        tvHomeFourActor = (TextView) rootView.findViewById(R.id.tv_home_four_actor);
        tvHomeThreeNews = (TextView) rootView.findViewById(R.id.tv_home_three_news);
        tvHomeThreePrevue = (TextView) rootView.findViewById(R.id.tv_home_three_prevue);
        tvHomeThreeCharts = (TextView) rootView.findViewById(R.id.tv_home_three_charts);
        tvHomeThreeReview = (TextView) rootView.findViewById(R.id.tv_home_three_review);
        inHomeHotMovie = (LinearLayout) rootView.findViewById(R.id.in_home_hot_movie);
        rlHomeHotMovie = (RelativeLayout) rootView.findViewById(R.id.rl_home_hot_movie);
        rlHomeHotPoints = (RelativeLayout) rootView.findViewById(R.id.rl_home_hot_points);
        rlHomeHotMall = (RelativeLayout) rootView.findViewById(R.id.rl_home_hot_mall);
        ivHomeTwoLeftind = (ImageView) rootView.findViewById(R.id.iv_home_two_leftind);
        ivHomeTwoRightind = (ImageView) rootView.findViewById(R.id.iv_home_two_rightind);
        scrollView = (ObservableScrollView) rootView.findViewById(R.id.sc_home);
        gallery = (FancyCoverFlow) rootView.findViewById(R.id.gallery_home);
        homeHotMovieBg = (ImageView) rootView.findViewById(R.id.home_top_movie_background);
        homeMovieName = (TextView) rootView.findViewById(R.id.home_movie_name);
        homeMovieScoreView = (TextView) rootView.findViewById(R.id.home_movie_score_view);
        homeLineGreen = (ImageView) rootView.findViewById(R.id.home_line_green);
        homeOnecomment = (TextView) rootView.findViewById(R.id.home_onecomment);
        homeHotmovieNumLayout = (LinearLayout) rootView.findViewById(R.id.home_hotmovie_num_layout);
        homeHotmovieNum = (TextView) rootView.findViewById(R.id.home_hotmovie_num);
        homeComingmovieNumLayout = (LinearLayout) rootView.findViewById(R.id.home_comingmovie_num_layout);
        comingmoiveNum = (TextView) rootView.findViewById(R.id.comingmoive_num);
        homeIncomingNumLayout = (LinearLayout) rootView.findViewById(R.id.home_incoming_num_layout);
        homeIncomingNum = (TextView) rootView.findViewById(R.id.home_incoming_num);
        homeButLayout = (RelativeLayout) rootView.findViewById(R.id.home_but_layout);
        homeButPay = (Button) rootView.findViewById(R.id.home_but_pay);
        homeButMovieinfo = (Button) rootView.findViewById(R.id.home_but_movieinfo);
        ivHomeTitleBarBG = (ImageView) rootView.findViewById(R.id.iv_home_title_bar_bg);


        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadingDialog = new LoadingDialog.Builder(mainActivity).create();
        loadingDialog.show();

        scrollView.smoothScrollTo(0, 0);

        ivHomeTitleBarBG.measure(0, 0);
        headerViewHeight = ivHomeTitleBarBG.getMeasuredHeight();
        ivHomeTitleBarBG.setAlpha(0f);

        getDataFromNet();
        setListener();
    }

    /**
     * 从网络获取数据
     */
    private void getDataFromNet() {
        String content = "{\n" +
                "    \"advList\": [\n" +
                "        {\n" +
                "            \"gotoPage\": {\n" +
                "                \"gotoType\": \"gotogoodsinfo\",\n" +
                "                \"parameters\": {\n" +
                "                    \"goodsId\": 101527\n" +
                "                },\n" +
                "                \"relatedTypeUrl\": \"\",\n" +
                "                \"url\": \"http://mall.wv.mtime.cn/#!/commerce/item/101527/\"\n" +
                "            },\n" +
                "            \"img\": \"http://img31.mtime.cn/mg/2015/12/10/110244.40670495.jpg\",\n" +
                "            \"img2\": \"http://img31.mtime.cn/mg/2015/12/10/110248.10405171.jpg\",\n" +
                "            \"url\": \"http://mall.wv.mtime.cn/#!/commerce/item/101527/\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"areaFirst\": {},\n" +
                "    \"areaSecond\": {\n" +
                "        \"subFifth\": {\n" +
                "            \"goodsId\": 101682,\n" +
                "            \"gotoPage\": {\n" +
                "                \"gotoType\": \"gotogoodsinfo\",\n" +
                "                \"parameters\": {\n" +
                "                    \"goodsId\": 101682\n" +
                "                },\n" +
                "                \"relatedTypeUrl\": \"\",\n" +
                "                \"url\": \"http://mall.wv.mtime.cn/?utm_source=app_home_5#!/commerce/item/101682/\"\n" +
                "            },\n" +
                "            \"id\": 18159583,\n" +
                "            \"image\": \"http://img31.mtime.cn/mg/2015/12/09/110315.98009755.jpg\",\n" +
                "            \"image2\": \"http://img31.mtime.cn/mg/2015/12/09/110321.51406012.jpg\",\n" +
                "            \"title\": \"新品主推\",\n" +
                "            \"titleColor\": \"#48b37e\",\n" +
                "            \"titleSmall\": \"星球大战千年隼i6手机壳\"\n" +
                "        },\n" +
                "        \"subFirst\": {\n" +
                "            \"goodsId\": 101527,\n" +
                "            \"gotoPage\": {\n" +
                "                \"gotoType\": \"gotogoodsinfo\",\n" +
                "                \"parameters\": {\n" +
                "                    \"goodsId\": 101527\n" +
                "                },\n" +
                "                \"relatedTypeUrl\": \"\",\n" +
                "                \"url\": \"http://mall.wv.mtime.cn/?utm_source=app_home_1#!/commerce/item/101527/\"\n" +
                "            },\n" +
                "            \"id\": 17728960,\n" +
                "            \"image\": \"http://img31.mtime.cn/mg/2015/12/10/111336.77577334.jpg\",\n" +
                "            \"image2\": \"http://img31.mtime.cn/mg/2015/12/10/111340.52113393.jpg\",\n" +
                "            \"title\": \"闪购特惠\",\n" +
                "            \"titleColor\": \"#e75959\",\n" +
                "            \"titleSmall\": \"Hello Kitty直饮保温杯\"\n" +
                "        },\n" +
                "        \"subFourth\": {\n" +
                "            \"goodsId\": 0,\n" +
                "            \"gotoPage\": {\n" +
                "                \"gotoType\": \"gotourl\",\n" +
                "                \"parameters\": {},\n" +
                "                \"relatedTypeUrl\": \"\",\n" +
                "                \"url\": \"http://feature.mtime.cn/mobile/item/2015/preironman/\"\n" +
                "            },\n" +
                "            \"image\": \"http://img31.mtime.cn/mg/2015/11/30/112419.32487408.jpg\",\n" +
                "            \"image2\": \"http://img31.mtime.cn/mg/2015/11/30/112419.32487408.jpg\"\n" +
                "        },\n" +
                "        \"subSecond\": {\n" +
                "            \"goodsId\": 101702,\n" +
                "            \"gotoPage\": {\n" +
                "                \"gotoType\": \"gotogoodsinfo\",\n" +
                "                \"parameters\": {\n" +
                "                    \"goodsId\": 101702\n" +
                "                },\n" +
                "                \"relatedTypeUrl\": \"\",\n" +
                "                \"url\": \"http://mall.wv.mtime.cn/?utm_source=app_home_2#!/commerce/item/101702/\"\n" +
                "            },\n" +
                "            \"id\": 17304115,\n" +
                "            \"image\": \"http://img31.mtime.cn/mg/2015/12/09/110742.93394220.jpg\",\n" +
                "            \"image2\": \"http://img31.mtime.cn/mg/2015/12/09/110746.93886641.jpg\",\n" +
                "            \"title\": \"热销推荐\",\n" +
                "            \"titleColor\": \"#2b88ca\",\n" +
                "            \"titleSmall\": \"星球大战POP公仔\"\n" +
                "        },\n" +
                "        \"subThird\": {\n" +
                "            \"goodsId\": 101484,\n" +
                "            \"gotoPage\": {\n" +
                "                \"gotoType\": \"gotogoodsinfo\",\n" +
                "                \"parameters\": {\n" +
                "                    \"goodsId\": 101484\n" +
                "                },\n" +
                "                \"relatedTypeUrl\": \"\",\n" +
                "                \"url\": \"http://mall.wv.mtime.cn/?utm_source=app_home_3#!/commerce/item/101484/\"\n" +
                "            },\n" +
                "            \"id\": 17304116,\n" +
                "            \"image\": \"http://img31.mtime.cn/mg/2015/12/09/110831.51555126.jpg\",\n" +
                "            \"image2\": \"http://img31.mtime.cn/mg/2015/12/09/110834.78027307.jpg\",\n" +
                "            \"title\": \"主推单品\",\n" +
                "            \"titleColor\": \"#e45e5e\",\n" +
                "            \"titleSmall\": \"蚁人炫酷出行套装\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"hotMovie\": {\n" +
                "        \"movie\": {\n" +
                "            \"desc\": \"韩国悬疑版杀妻俱乐部，前半段笑死后面高能，权相宇的窝囊废演技挺反转。\",\n" +
                "            \"image\": \"http://img31.mtime.cn/mt/2015/08/24/162838.79432275_1280X720X2.jpg\",\n" +
                "            \"movieId\": 218906,\n" +
                "            \"titleCn\": \"侦探\",\n" +
                "            \"titleEn\": \"?? : ? ???\",\n" +
                "            \"year\": \"2015\"\n" +
                "        },\n" +
                "        \"newsId\": 0,\n" +
                "        \"title\": \"曾经高冷的男神都开始演废柴奶爸了\",\n" +
                "        \"topCover\": \"http://img31.mtime.cn/mg/2015/12/10/100937.92725630.jpg\"\n" +
                "    },\n" +
                "    \"hotPerson\": {},\n" +
                "    \"hotPoints\": [\n" +
                "        {\n" +
                "            \"commentCount\": 2,\n" +
                "            \"desc\": \"传闻正在接洽 角色依旧未知\",\n" +
                "            \"id\": 1550200,\n" +
                "            \"img\": \"http://img31.mtime.cn/mg/2015/12/11/093750.15441772.jpg\",\n" +
                "            \"img2\": \"\",\n" +
                "            \"img3\": \"\",\n" +
                "            \"publishTime\": 1449825643,\n" +
                "            \"tag\": \"\",\n" +
                "            \"title\": \"凯特布兰切特或加盟“雷神3”\",\n" +
                "            \"type\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"commentCount\": 13,\n" +
                "            \"desc\": \"首支预告今日发布 将贴片《星球大战7》\",\n" +
                "            \"id\": 1550195,\n" +
                "            \"img\": \"http://img31.mtime.cn/mg/2015/12/11/093319.14838313.jpg\",\n" +
                "            \"img2\": \"\",\n" +
                "            \"img3\": \"\",\n" +
                "            \"publishTime\": 1449821957,\n" +
                "            \"tag\": \"\",\n" +
                "            \"title\": \"《X战警:天启》曝预告海报\",\n" +
                "            \"type\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"commentCount\": 13,\n" +
                "            \"desc\": \"“老辈中国人就是这样打架的”\",\n" +
                "            \"id\": 1550169,\n" +
                "            \"img\": \"http://img31.mtime.cn/mg/2015/12/11/082630.13318684.jpg\",\n" +
                "            \"img2\": \"\",\n" +
                "            \"img3\": \"\",\n" +
                "            \"publishTime\": 1449821527,\n" +
                "            \"tag\": \"\",\n" +
                "            \"title\": \"《师父》兵器全解析\",\n" +
                "            \"type\": 0\n" +
                "        }\n" +
                "    ],\n" +
                "    \"mallEntrys\": [],\n" +
                "    \"topPosters\": []\n" +
                "}";
        parseData(content);

        RequestParams params = new RequestParams(UrlConstants.HOME_LIST_DATA);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                parseData(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

        RequestParams params2 = new RequestParams(UrlConstants.HOME_MOVIE_DATA);
        x.http().get(params2, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                parseMoviesData(result);
                saveData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 将Json数据保存到本地
     *
     * @param result
     */
    private void saveData(String result) {
        try {
            FileOutputStream fos = mainActivity.openFileOutput("HOME_DATA", Context.MODE_PRIVATE);
            fos.write(result.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析Json数据
     *
     * @param result
     */
    private void parseData(final String result) {
        homeBean = JSON.parseObject(result, HomeBean.class);
        advData = homeBean.advList;
        data = homeBean.hotPoints;

        setData();
        setAdapter();
        setPagerAdapter();
    }

    /**
     * 解析头部选座购票数据
     *
     * @param result
     */
    private void parseMoviesData(String result) {
        homeMovieBean = JSON.parseObject(result, HomeMovieBean.class);
        loadingDialog.dismiss();

        setGalleryData();
        setGalleryAdapter();
    }

    /**
     * 设置数据
     */
    private void setData() {
        ImageOptions.Builder builder = new ImageOptions.Builder();
        builder.setImageScaleType(ImageView.ScaleType.FIT_XY);
        ImageOptions options = builder.build();
        x.image().bind(ivHomeOneSubfifth, homeBean.areaSecond.subFifth.image, options);
        x.image().bind(ivHomeOneSubfirst, homeBean.areaSecond.subFirst.image, options);
        x.image().bind(ivHomeOneSubsecond, homeBean.areaSecond.subSecond.image, options);
        x.image().bind(ivHomeOneThird, homeBean.areaSecond.subThird.image, options);

        x.image().bind(ivHomeFour, homeBean.hotMovie.topCover);
        x.image().bind(ivHomeFourActor, homeBean.hotMovie.movie.image);

        tvHomeFourTitle.setText(homeBean.hotMovie.title);
        tvHomeFourSubtitle.setText(homeBean.hotMovie.movie.desc);
        tvHomeFourActorEn.setText(homeBean.hotMovie.movie.titleEn);
        tvHomeFourActor.setText(homeBean.hotMovie.movie.titleCn);

    }

    /**
     * 设置适配器
     */
    private void setAdapter() {
        HomeBaseAdapter adapter = new HomeBaseAdapter(mainActivity, data);
        listView.setAdapter(adapter);
        scrollView.smoothScrollTo(0, 0);
    }

    /**
     * 给广告条设置adapter
     */
    private void setPagerAdapter() {
        HomePagerAdapter pagerAdapter = new HomePagerAdapter(mainActivity, advData);
        vpHomeTwo.setAdapter(pagerAdapter);

        if (advData.size() > 1) {
            vpHomeTwo.setCurrentItem((advData.size() % 2) * (1000 % advData.size()));
            handler.sendEmptyMessageDelayed(WHAT_UPDATE_ADV, 4000);
        }

    }

    /**
     * 给Gallery设置数据
     */
    private void setGalleryAdapter() {
        bitmaps = new ArrayList<>();
        HomeGalleryAdapter galleryAdapter = new HomeGalleryAdapter(mainActivity, homeMovieBean.movies, bitmaps);
        gallery.setAdapter(galleryAdapter);
        gallery.setUnselectedAlpha(0.6f);
        gallery.setUnselectedSaturation(0.0f);
        gallery.setUnselectedScale(0.5f);
        gallery.setSpacing(DensityUtil.dip2px(mainActivity, 15));
        gallery.setMaxRotation(0);
        gallery.setScaleDownGravity(0.5f);
        gallery.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
        gallery.setSelection(0);
    }

    /**
     * 设置头部数据
     */
    private void setGalleryData() {
        homeHotmovieNum.setText(homeMovieBean.totalHotMovie + "");
        comingmoiveNum.setText(homeMovieBean.totalComingMovie + "");
        homeIncomingNum.setText(homeMovieBean.totalCinemaCount + "");
    }

    /**
     * 设置监听器
     */
    private void setListener() {
        tvHomeThreeNews.setOnClickListener(this);
        tvHomeThreePrevue.setOnClickListener(this);
        tvHomeThreeCharts.setOnClickListener(this);
        tvHomeThreeReview.setOnClickListener(this);
        inHomeHotMovie.setOnClickListener(this);
        rlHomeHotMovie.setOnClickListener(this);
        rlHomeHotPoints.setOnClickListener(this);
        rlHomeHotMall.setOnClickListener(this);
        homeHotmovieNumLayout.setOnClickListener(this);
        homeComingmovieNumLayout.setOnClickListener(this);
        homeIncomingNumLayout.setOnClickListener(this);

        vpHomeTwo.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                homeOnecomment.setText(homeMovieBean.movies.get(position).commonSpecial);
                homeMovieName.setText(homeMovieBean.movies.get(position).titleCn);
                String rate = homeMovieBean.movies.get(position).ratingFinal;
                if ("-1".equals(rate)) {
                    homeMovieScoreView.setVisibility(View.GONE);
                } else {
                    homeMovieScoreView.setText(homeMovieBean.movies.get(position).ratingFinal);
                }

                if (bitmaps != null && bitmaps.size() > 0) {
                    ImageView imageView = (ImageView) gallery.findViewWithTag(position);
                    imageView.setDrawingCacheEnabled(true);
                    Bitmap bitmapImage = imageView.getDrawingCache();
//                    Bitmap bitmap = FastBlur.blurBitmap(bitmapImage, mainActivity);
                    Bitmap bitmap = FastBlur.doBlur(bitmapImage, 35, false);
                    homeHotMovieBg.setImageBitmap(bitmap);
                    imageView.setDrawingCacheEnabled(false);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity, MovieDetailActivity.class);
                intent.putExtra("id", homeMovieBean.movies.get(position).movieId);
                startActivity(intent);
            }
        });

        scrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (getHeaderOnScreenY() > 300 / 2) {
                    ivHomeTitleBarBG.setAlpha(1f);
                } else {
                    float alpha = getHeaderOnScreenY() * 2f / 300;
                    ivHomeTitleBarBG.setAlpha(alpha);
                }
            }
        });
    }

    private int getHeaderOnScreenY() {
        int[] local = new int[2];
        ivHomeOneSubfifth.getLocationOnScreen(local);
        int headerOnScreenY = local[1];

        scrollView.getLocationOnScreen(local);
        int listViewOnScreenY = local[1];
        return listViewOnScreenY - headerOnScreenY + 1450;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_home_three_news:
                mainActivity.setRadioButtonChecked(3, 0);
                break;
            case R.id.tv_home_three_prevue:
                mainActivity.setRadioButtonChecked(3, 1);
                break;
            case R.id.tv_home_three_charts:
                mainActivity.setRadioButtonChecked(3, 2);
                break;
            case R.id.tv_home_three_review:
                mainActivity.setRadioButtonChecked(3, 3);
                break;
            case R.id.rl_home_hot_movie:
                mainActivity.setRadioButtonChecked(3, 2);
                break;
            case R.id.in_home_hot_movie:
                mainActivity.setRadioButtonChecked(3, 2);
                break;
            case R.id.rl_home_hot_points:
                mainActivity.setRadioButtonChecked(3, 0);
                break;
            case R.id.home_hotmovie_num_layout:
                mainActivity.setRadioButtonChecked(1, 0);
                break;
            case R.id.home_comingmovie_num_layout:
                mainActivity.setRadioButtonChecked(1, 1);
                break;
            case R.id.home_incoming_num_layout:
                mainActivity.setRadioButtonChecked(1, 2);
                break;
            case R.id.rl_home_hot_mall:
                mainActivity.setRadioButtonChecked(2, 0);
                break;
        }
    }

    private AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    };


}
