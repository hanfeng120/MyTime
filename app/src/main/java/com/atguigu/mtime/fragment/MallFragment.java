package com.atguigu.mtime.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.atguigu.mtime.R;
import com.atguigu.mtime.activity.BrowserActivity;
import com.atguigu.mtime.activity.ChartsMoviesActivity;
import com.atguigu.mtime.activity.ChartsPersonActivity;
import com.atguigu.mtime.activity.MainActivity;
import com.atguigu.mtime.adapter.MallGridAdapter;
import com.atguigu.mtime.adapter.MallListAdapter;
import com.atguigu.mtime.adapter.MallPagerAdapter;
import com.atguigu.mtime.adapter.MallTopicAdapter;
import com.atguigu.mtime.bean.MallBottomBean;
import com.atguigu.mtime.bean.MallHomeBean;
import com.atguigu.mtime.utils.DensityUtil;
import com.atguigu.mtime.utils.DepthPageTransformer;
import com.atguigu.mtime.utils.UrlConstants;
import com.atguigu.mtime.view.ObservableScrollView;
import com.atguigu.mtime.view.widget.LoadingView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

/**
 * 商城Tab页面
 * Created by HanFeng on 2015/12/6.
 */
public class MallFragment extends Fragment implements OnClickListener {
    private MainActivity mainActivity;

    //title和搜索页
    private ImageView blackBackground;
    private ImageView background;
    private ImageButton scan;
    private RelativeLayout back;
    private ImageView cart;
    private RelativeLayout searchRegion;
    private EditText searchContent;

    //直接回到顶部
    private ImageView gotopBtn;

    //viewpager
    private ViewPager mall_home_viewpager;

    private ObservableScrollView mall_home_scrollview;

    //四大分类栏
    private LinearLayout mallNavigatorFirstline;
    //玩具
    private ImageView mallNavigatorFirstlineFirstIcon;
    private TextView mallNavigatorFirstlineFirstIconTxt;
    //数码
    private ImageView mallNavigatorFirstlineSecondIcon;
    private TextView mallNavigatorFirstlineSecondIconTxt;
    //服饰
    private ImageView mallNavigatorFirstlineThirdIcon;
    private TextView mallNavigatorFirstlineThirdIconTxt;
    //家居
    private ImageView mallNavigatorFirstlineForthIcon;
    private TextView mallNavigatorFirstlineForthIconTxt;

    private RelativeLayout mallHomeLayout;

    //四个分类栏下的不规则布局商品
    private LinearLayout cell;

    //左上部——闪购特惠A
    private ImageView cella;
    private TextView cellaMainTitle;
    private TextView cellaSubTitle;

    //    //时间动画
//    private LinearLayout cellaFlashsaleLayout;
//    private TextView cellaFlashsaleText;
//    private Button cellaFlashsaleHour;
//    private Button cellaFlashsaleMinute;
//    private Button cellaFlashsaleMill;
    //下部——时光预售
    private View cellbSeperated;
    private ImageView cellb;
    private TextView cellbMainTitle;
    private TextView cellbSubTitle;

    //右上
    private ImageView cellc1;
    private TextView cellc1MainTitle;
    private TextView cellc1SubTitle;
    private View subSecondSeperated;
    //右中
    private ImageView cellc2;
    private TextView cellc2MainTitle;
    private TextView cellc2SubTitle;

//    private LinearLayout cellbFlashsaleLayout;
//    private TextView cellbFlashsaleText;
//    private Button cellbFlashsaleHour;
//    private Button cellbFlashsaleMinute;
//    private Button cellbFlashsaleMill;


    //加载失败
    private RelativeLayout loading_failed_layout;
    private ImageView loadingFailed;
    //    private include mallHomeRecommendLayout;

    private ScrollView mallHomeScrollview;
    private ImageView pageHomeMallenterSeperated;
    //    private include mallHomeCell;
    //四个商品模块
    private LinearLayout mallHomeCategoryLayout;

    /**
     * 商城上部横向滚动scrollview
     */
    private ImageView backgroundImg;
    private HorizontalScrollView mallHomeTopicScroll;
    private LinearLayout mallHomeTopicTitle;
    private ImageView mallHomeTopicImg1;
    private ImageView mallHomeTopicImg2;
    private ImageView mallHomeTopicImg3;
    private ImageView mallHomeTopicImg4;
    private ImageView mallHomeTopicImg5;
    private TextView topicTitleEn;
    private TextView topicTitleCn;
    private GridView mallHomeTopicGrid;
    private TextView topicMore;
    private ImageView topicSeperateView;

    private ListView lv_mall_home_listview;

    //四个分类 listview
    private View categoryColor;
    private TextView categoryName;
    private TextView categoryMore;
    private ImageView iconArrow;
    private ImageView categoryImg;
    private LinearLayout categorySublist;
    private ImageView categorySeparateView;

    private ImageView mallCategoryImg;
    private TextView mallCategoryTitle;
    private TextView mallCategoryPrice;

    //    private ScrollGridview productGrid;
    private GridView productGrid;
    private TextView productNomore;

    private LoadingView loadingView;
    private ImageView ivLoadFailed;

    /**
     * 数据
     */
    private MallHomeBean mallHomeBean;
    private MallBottomBean mallBottomBean;
    private ArrayList<MallHomeBean.CategoryBean> categoryData;
    private MallHomeBean.CellABean cellaData;
    private MallHomeBean.CellBBean cellbData;
    private MallHomeBean.CellCBean cellcData;
    private MallHomeBean.NavigatorFirthIconBean navigatorFirthIconData;
    private ArrayList<MallHomeBean.NavigatorIconBean> navigatorIconData;
    private ArrayList<MallHomeBean.ScrollImgBean> scrollImgData;
    private ArrayList<MallHomeBean.TopicBean> topicData;

    private ArrayList<MallBottomBean.GoodsListBean> goodsListData;
    /**
     * 底部布局
     */
    private com.atguigu.mtime.view.ScrollGridview interest_gridview;
    private TextView product_nomore;
    private ImageView product_img;
    private TextView product_name;

    /**
     * 顶部viewpager消息
     */
    private static final int TOPVIEWPAGER = 0;
    private int currentPosition = 0;
    private int lastItem;
    private static final int DEFAULT_BANNER_SIZE = 3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mall_home_layout, null);
        mallHomeLayout = (RelativeLayout) view.findViewById(R.id.mall_home_layout);
        loadingView = (LoadingView) view.findViewById(R.id.loading_mall);
        ivLoadFailed = (ImageView) view.findViewById(R.id.iv_mall_load_failed);

        //顶部
        blackBackground = (ImageView) view.findViewById(R.id.black_background);
        background = (ImageView) view.findViewById(R.id.background);
        scan = (ImageButton) view.findViewById(R.id.scan);
        back = (RelativeLayout) view.findViewById(R.id.back);
        cart = (ImageView) view.findViewById(R.id.cart);
        searchRegion = (RelativeLayout) view.findViewById(R.id.search_region);
        searchContent = (EditText) view.findViewById(R.id.search_content);


        mViewPager = (ViewPager) view.findViewById(R.id.mall_home_viewpager);
        ly_mall_points = (LinearLayout) view.findViewById(R.id.ly_mall_points);

        mall_home_scrollview = (ObservableScrollView) view.findViewById(R.id.mall_home_scrollview);

        loading_failed_layout = (RelativeLayout) view.findViewById(R.id.loading_failed_layout);
        pageHomeMallenterSeperated = (ImageView) view.findViewById(R.id.page_home_mallenter_seperated);
        mallHomeCategoryLayout = (LinearLayout) view.findViewById(R.id.mall_home_category_layout);
        gotopBtn = (ImageView) view.findViewById(R.id.gotop_btn);
        // scroll_gallery = (ViewPager) view.findViewById(R.id.scroll_gallery);

        loadingFailed = (ImageView) view.findViewById(R.id.loading_failed);

        mallNavigatorFirstline = (LinearLayout) view.findViewById(R.id.mall_navigator_firstline);
        mallNavigatorFirstlineFirstIcon = (ImageView) view.findViewById(R.id.mall_navigator_firstline_firstIcon);
        mallNavigatorFirstlineFirstIconTxt = (TextView) view.findViewById(R.id.mall_navigator_firstline_firstIconTxt);
        mallNavigatorFirstlineSecondIcon = (ImageView) view.findViewById(R.id.mall_navigator_firstline_SecondIcon);
        mallNavigatorFirstlineSecondIconTxt = (TextView) view.findViewById(R.id.mall_navigator_firstline_SecondIconTxt);
        mallNavigatorFirstlineThirdIcon = (ImageView) view.findViewById(R.id.mall_navigator_firstline_ThirdIcon);
        mallNavigatorFirstlineThirdIconTxt = (TextView) view.findViewById(R.id.mall_navigator_firstline_ThirdIconTxt);
        mallNavigatorFirstlineForthIcon = (ImageView) view.findViewById(R.id.mall_navigator_firstline_ForthIcon);
        mallNavigatorFirstlineForthIconTxt = (TextView) view.findViewById(R.id.mall_navigator_firstline_ForthIconTxt);


        cell = (LinearLayout) view.findViewById(R.id.cell);
        cella = (ImageView) view.findViewById(R.id.cella);
        cellaMainTitle = (TextView) view.findViewById(R.id.cella_main_title);
        cellaSubTitle = (TextView) view.findViewById(R.id.cella_sub_title);
        cellc1 = (ImageView) view.findViewById(R.id.cellc1);
        cellc1MainTitle = (TextView) view.findViewById(R.id.cellc1_main_title);
        cellc1SubTitle = (TextView) view.findViewById(R.id.cellc1_sub_title);
        subSecondSeperated = (View) view.findViewById(R.id.subSecond_seperated);
        cellc2 = (ImageView) view.findViewById(R.id.cellc2);
        cellc2MainTitle = (TextView) view.findViewById(R.id.cellc2_main_title);
        cellc2SubTitle = (TextView) view.findViewById(R.id.cellc2_sub_title);
        cellbSeperated = (View) view.findViewById(R.id.cellb_seperated);
        cellb = (ImageView) view.findViewById(R.id.cellb);
        cellbMainTitle = (TextView) view.findViewById(R.id.cellb_main_title);
        cellbSubTitle = (TextView) view.findViewById(R.id.cellb_sub_title);

        /**
         * 商城上部横向滚动scrollview
         */
        backgroundImg = (ImageView) view.findViewById(R.id.background_img);
        mallHomeTopicScroll = (HorizontalScrollView) view.findViewById(R.id.mall_home_topic_scroll);
        mallHomeTopicTitle = (LinearLayout) view.findViewById(R.id.mall_home_topic_title);
        mallHomeTopicImg1 = (ImageView) view.findViewById(R.id.mall_home_topic_img1);
        mallHomeTopicImg2 = (ImageView) view.findViewById(R.id.mall_home_topic_img2);
        mallHomeTopicImg3 = (ImageView) view.findViewById(R.id.mall_home_topic_img3);
        mallHomeTopicImg4 = (ImageView) view.findViewById(R.id.mall_home_topic_img4);
        mallHomeTopicImg5 = (ImageView) view.findViewById(R.id.mall_home_topic_img5);
        topicTitleEn = (TextView) view.findViewById(R.id.topic_title_en);
        topicTitleCn = (TextView) view.findViewById(R.id.topic_title_cn);
        topicMore = (TextView) view.findViewById(R.id.topic_more);
        topicSeperateView = (ImageView) view.findViewById(R.id.topic_seperate_view);
        mallHomeTopicGrid = (GridView)view.findViewById( R.id.mall_home_topic_grid );


        lv_mall_home_listview = (ListView) view.findViewById(R.id.lv_mall_home_listview);


//        mallCategoryImg = (ImageView) view.findViewById(R.id.mall_category_img);
//        mallCategoryTitle = (TextView) view.findViewById(R.id.mall_category_title);
//        mallCategoryPrice = (TextView) view.findViewById(R.id.mall_category_price);

//        productGrid = (ScrollGridview)view.findViewById( R.id.product_grid );
        productGrid = (GridView) view.findViewById(R.id.interest_gridview);
        productNomore = (TextView) view.findViewById(R.id.product_nomore);


        return view;
    }


    private int dipsize;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mall_home_scrollview.smoothScrollTo(0, 0);
        getData();

        //把这个单位当成dip -10 -
        dipsize = DensityUtil.dip2px(mainActivity, 5);
    }

    private void getData() {
        getDataFromNet();
        getDataFromNet2();
        setListeners();
    }

    /**
     * 设置监听
     */
    private void setListeners() {
        //title
        scan.setOnClickListener(this);
        cart.setOnClickListener(this);
        searchContent.setOnClickListener(this);
        //一键置顶部
        gotopBtn.setOnClickListener(this);
        //四个导航栏
        mallNavigatorFirstlineFirstIcon.setOnClickListener(this);
        mallNavigatorFirstlineSecondIcon.setOnClickListener(this);
        mallNavigatorFirstlineThirdIcon.setOnClickListener(this);
        mallNavigatorFirstlineForthIcon.setOnClickListener(this);

        //导航栏下方
        cella.setOnClickListener(this);
        cellb.setOnClickListener(this);
        cellc1.setOnClickListener(this);
        cellc2.setOnClickListener(this);

        mall_home_scrollview.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                // 获取当前位置占头布局高度的百分比
                int height = scrollView.getChildAt(0).getMeasuredHeight()/10;
                if(y<0){y=0;}
                float f = (float)y/(float)height;
                if(f>1){f=1;}
                Log.i("mall_home_scrollview","x="+x+"y"+y+"oldx="+oldx+"oldy="+oldy+"height="+height+"f"+f);
                background.getBackground().setAlpha((int) (f*255));
                //通知标题栏刷新显示
                background.invalidate();
            }
        });
//
//        mall_home_scrollview.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                mall_home_scrollview.getScrollY();
//                return false;
//            }
//        });

//        mall_home_scrollview.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
//            @Override
//            public void onScrollChanged(ObservableScrollView mall_home_scrollview, int x, int y, int oldx, int oldy) {
//                if (getHeaderOnScreenY() < 300 / 2) {
//                    background.getBackground().setAlpha(1);
//                } else {
//                    float alpha = getHeaderOnScreenY();
//                    if (alpha > 255) {
//                        alpha = 255;
//                    }
//                    background.getBackground().setAlpha((int) alpha);
//                }
//            }
//        });

        ivLoadFailed.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ivLoadFailed.setVisibility(View.GONE);
                loadingView.setVisibility(View.VISIBLE);
                getData();
            }
        });
    }

//    private int getHeaderOnScreenY() {
//        int[] local = new int[2];
//        cell.getLocationOnScreen(local);
//        int cellOnScreenY = local[1];
//
//        mall_home_scrollview.getLocationOnScreen(local);
//        int GridViewOnScreenY = local[1];
//        return -cellOnScreenY + GridViewOnScreenY + 1234;
//    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.scan:
                Intent intent22 = new Intent(mainActivity, ChartsPersonActivity.class);
//                intent22.putExtra()/
                startActivity(intent22);
                break;
            case R.id.cart:
                Intent intent21 = new Intent(mainActivity, ChartsMoviesActivity.class);
                startActivity(intent21);
                break;
            case R.id.search_content:
                break;
            case R.id.gotop_btn:
                break;
            case R.id.mall_navigator_firstline_firstIcon:
                Intent intent = new Intent(mainActivity, BrowserActivity.class);
                intent.putExtra("url", "m.mtime.cn/" + navigatorFirthIconData.url);
                startActivity(intent);
                break;
            case R.id.mall_navigator_firstline_SecondIcon:
                Intent intent2 = new Intent(mainActivity, BrowserActivity.class);
                intent2.putExtra("url", "m.mtime.cn/" + navigatorIconData.get(0).url);
                startActivity(intent2);
                break;
            case R.id.mall_navigator_firstline_ThirdIcon:
                Intent intent3 = new Intent(mainActivity, BrowserActivity.class);
                intent3.putExtra("url", "m.mtime.cn/" + navigatorIconData.get(1).url);
                startActivity(intent3);
                break;
            case R.id.mall_navigator_firstline_ForthIcon:
                Intent intent4 = new Intent(mainActivity, BrowserActivity.class);
                intent4.putExtra("url", "m.mtime.cn/" + navigatorIconData.get(2).url);
                startActivity(intent4);
                break;
            case R.id.cella:
                Intent intenta = new Intent(mainActivity, BrowserActivity.class);
                intenta.putExtra("url", "m.mtime.cn/" + cellaData.url);
                startActivity(intenta);
                break;
            case R.id.cellb:
                Intent intentb = new Intent(mainActivity, BrowserActivity.class);
                intentb.putExtra("url", "m.mtime.cn/" + cellbData.url);
                startActivity(intentb);
                break;
            case R.id.cellc1:
                Intent intentc1 = new Intent(mainActivity, BrowserActivity.class);
                intentc1.putExtra("url", "m.mtime.cn/" + cellcData.list.get(0).url);
                startActivity(intentc1);
                break;
            case R.id.cellc2:
                Intent intentc2 = new Intent(mainActivity, BrowserActivity.class);
                intentc2.putExtra("url", "m.mtime.cn/" + cellcData.list.get(1).url);
                startActivity(intentc2);
                break;
            default:
                break;
        }
    }

    /**
     * 从网络获取商城主界面数据
     */
    private void getDataFromNet() {
        RequestParams params = new RequestParams(UrlConstants.MALL_LIST_DATA);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                parseData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ivLoadFailed.setVisibility(View.VISIBLE);
                loadingView.setVisibility(View.GONE);
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
     * 从网络获取商城底部数据
     */
    private void getDataFromNet2() {
        final RequestParams params = new RequestParams(UrlConstants.MALL_BOTTOM_DATA);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                parseData2(result);
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
     * 解析底部json数据
     *
     * @param result
     */
    private void parseData2(String result) {
        mallBottomBean = new Gson().fromJson(result, MallBottomBean.class);
        goodsListData = mallBottomBean.goodsList;

        setAdapter2();
    }

    /**
     * 解析json数据
     *
     * @param result
     */
    private void parseData(String result) {
        mallHomeBean = new Gson().fromJson(result, MallHomeBean.class);
        categoryData = mallHomeBean.category;
        cellaData = mallHomeBean.cellA;
        cellbData = mallHomeBean.cellB;
        cellcData = mallHomeBean.cellC;

        //设置UI
        ivLoadFailed.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        mallHomeLayout.setVisibility(View.VISIBLE);

        navigatorFirthIconData = mallHomeBean.navigatorFirthIcon;
        navigatorIconData = mallHomeBean.navigatorIcon;
        scrollImgData = mallHomeBean.scrollImg;
        topicData = mallHomeBean.topic;
        setData();
        setAdapter();
        setPoint();
        setPagerAdapter();
        setScrollViewData(2);
    }


    private ViewPager mViewPager;

    /**
     * 设置数据
     */
    private void setData() {


        /**
         * 导航控件
         */
        x.image().bind(mallNavigatorFirstlineFirstIcon, navigatorIconData.get(0).image);
        x.image().bind(mallNavigatorFirstlineSecondIcon, navigatorIconData.get(1).image);
        x.image().bind(mallNavigatorFirstlineThirdIcon, navigatorIconData.get(2).image);
        x.image().bind(mallNavigatorFirstlineForthIcon, mallHomeBean.navigatorFirthIcon.img1);

        mallNavigatorFirstlineFirstIconTxt.setText(navigatorIconData.get(0).iconTitle);
        mallNavigatorFirstlineSecondIconTxt.setText(navigatorIconData.get(1).iconTitle);
        mallNavigatorFirstlineThirdIconTxt.setText(navigatorIconData.get(2).iconTitle);
        mallNavigatorFirstlineForthIconTxt.setText(mallHomeBean.navigatorFirthIcon.iconTitle1);


        /**
         * 四个分类栏下的不规则布局商品
         */
        x.image().bind(cella, mallHomeBean.cellA.img);
        x.image().bind(cellc1, mallHomeBean.cellC.list.get(0).image);
        x.image().bind(cellc2, mallHomeBean.cellC.list.get(1).image);
        x.image().bind(cellb, mallHomeBean.cellB.img);

        /**
         * 商城上部横向滚动scrollview
         */

        /*mallHomeTopicScroll = (HorizontalScrollView) view.findViewById(R.id.mall_home_topic_scroll);

        mallHomeTopicTitle = (LinearLayout) view.findViewById(R.id.mall_home_topic_title);
        mallHomeTopicImg1 = (ImageView) view.findViewById(R.id.mall_home_topic_img1);
        mallHomeTopicImg2 = (ImageView) view.findViewById(R.id.mall_home_topic_img2);
        mallHomeTopicImg3 = (ImageView) view.findViewById(R.id.mall_home_topic_img3);
        mallHomeTopicImg4 = (ImageView) view.findViewById(R.id.mall_home_topic_img4);
        mallHomeTopicImg5 = (ImageView) view.findViewById(R.id.mall_home_topic_img5);

        topicTitleEn = (TextView) view.findViewById(R.id.topic_title_en);
        topicTitleCn = (TextView) view.findViewById(R.id.topic_title_cn);
        topicMore = (TextView) view.findViewById(R.id.topic_more);
        topicSeperateView = (ImageView) view.findViewById(R.id.topic_seperate_view);*/
        ImageOptions.Builder builder = new ImageOptions.Builder();
        builder.setImageScaleType(ImageView.ScaleType.CENTER_INSIDE);
        ImageOptions options = builder.build();
        x.image().bind(mallHomeTopicImg1,topicData.get(0).checkedImage);
        x.image().bind(mallHomeTopicImg2,topicData.get(1).checkedImage);
        x.image().bind(mallHomeTopicImg3,topicData.get(2).checkedImage);
        x.image().bind(mallHomeTopicImg4,topicData.get(3).checkedImage);
        x.image().bind(mallHomeTopicImg5,topicData.get(4).checkedImage);
        setScrollViewData(0);

        mallHomeTopicImg1.setOnClickListener(mOnClickListener);
        mallHomeTopicImg2.setOnClickListener(mOnClickListener);
        mallHomeTopicImg3.setOnClickListener(mOnClickListener);
        mallHomeTopicImg4.setOnClickListener(mOnClickListener);
        mallHomeTopicImg5.setOnClickListener(mOnClickListener);
    }

    /**
     * 填充横向ScrollView数据
     * @param position 索引
     */
    private void setScrollViewData(int position){
        topicTitleEn.setText(topicData.get(position).titleEn);
        topicTitleCn.setText(topicData.get(position).titleCn);
        mallHomeTopicGrid.setAdapter(new MallTopicAdapter(mainActivity, topicData.get(position).subList));
        x.image().bind(backgroundImg, topicData.get(position).backgroupImage);
    }

    private OnClickListener mOnClickListener=new OnClickListener(){


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.mall_home_topic_img1:
                    setScrollViewData(0);

                    break;
                case R.id.mall_home_topic_img2:
                    setScrollViewData(1);

                    break;
                case R.id.mall_home_topic_img3:
                    setScrollViewData(2);

                    break;
                case R.id.mall_home_topic_img4:
                    setScrollViewData(3);

                    break;
                case R.id.mall_home_topic_img5:
                    setScrollViewData(4);

                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 设置适配器
     */
    private void setAdapter() {
        lv_mall_home_listview.setAdapter(new MallListAdapter(mainActivity, categoryData));
        mall_home_scrollview.smoothScrollTo(0, 0);
    }

    /**
     * 底部兴趣推荐商品
     */
    private void setAdapter2() {
        productGrid.setAdapter(new MallGridAdapter(mainActivity, goodsListData));
    }



    private InternalHandler handler = new InternalHandler();

    private LinearLayout ly_mall_points;

    /**
     * //添加灰色的点
     */
    private ArrayList<ImageView> points;

    private void setPoint() {
        points = new ArrayList<>();
        //添加灰色的点-有多少个页面就添加多少个点击
        for (int i = 0; i < scrollImgData.size(); i++) {
            ImageView point = new ImageView(mainActivity);
            points.add(point);
            point.setBackgroundResource(R.mipmap.point);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dipsize, dipsize);//
            params.leftMargin = 10;
            ly_mall_points.addView(point, params);
        }

    }

    /**
     * 顶部viewpager
     */
    private void setPagerAdapter() {
        MallPagerAdapter mallPagerAdapter = new MallPagerAdapter(mainActivity, scrollImgData);
        mViewPager.setAdapter(mallPagerAdapter);
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mViewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        mViewPager.setCurrentItem(scrollImgData.size() * 150);
        handler.removeMessages(TOPVIEWPAGER);
        handler.sendEmptyMessageDelayed(TOPVIEWPAGER, 4000);
    }


    private AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    };


    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            position = position % scrollImgData.size();
            for (int i = 0; i < points.size(); i++) {
                if (i == position) {
                    // points.get(position).setImageResource(R.mipmap.point_f);
                    points.get(position).setBackgroundResource(R.mipmap.point_f);
                } else {
                    points.get(position).setBackgroundResource(R.mipmap.point);
                    //points.get(position).setImageResource(R.mipmap.point);
                }
            }
//            MallFragment.this.currentPosition = position;
//            position % = DEFAULT_BANNER_SIZE;
//            points.get(position).setEnabled(true);
//            points.get(lastItem).setEnabled(false);
//            lastItem = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class InternalHandler extends Handler {


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == TOPVIEWPAGER) {
                int item = (mViewPager.getCurrentItem() + 1);
                mViewPager.setCurrentItem(item);
                handler.sendEmptyMessageDelayed(TOPVIEWPAGER, 4000);
            }

        }
    }
}
