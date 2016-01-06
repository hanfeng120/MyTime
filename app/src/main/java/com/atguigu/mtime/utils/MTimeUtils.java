package com.atguigu.mtime.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Formatter;
import java.util.Locale;

/**
 * 基本工具类
 * Created by HanFeng on 2015/12/6.
 */
public class MTimeUtils {

    /**
     * 弹出一个Toast
     *
     * @param context
     * @param message
     */
    public static void showMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 根据传入的发布时间返回距离当前时间的Sring格式 如22分钟前
     *
     * @param time
     * @return
     */
    public static String setTime(long time) {
        int m = 28797;//该值是通过找规律找出来的
        long date = (System.currentTimeMillis()/1000 + m) - time;
        Log.e("TIME", System.currentTimeMillis() + ">>>" + date);

        if (date / 60 < 60) {
            int min = (int) (date / 60);
            return min + "分钟前";
        } else if (date / 60 / 60 < 24) {
            int hour = (int) (date / 60);
            return hour + "小时前";
        } else {
            int day = (int) (date / 60 / 60 / 24);
            return day + "天前";
        }
    }

    /**
     * 获取当前是几号
     * @return
     */
    public static int getDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回当前年份
     * @return
     */
    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static String getDateFromShowing(String date) {
//        20151210
        if(date == null) return null;
        String mon = date.substring(4, 6);
        String day = date.substring(6);
        if(day.startsWith("0")) {
            day = day.substring(1);
        }
        return mon + "月" + day + "日上映";
    }

    /**
     * 把毫秒转换成：1小时20分30秒 这里形式
     * @param timeMs
     * @return
     */
    public static String stringForTimeCn(long timeMs) {
        StringBuilder mFormatBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());

        int totalSeconds = (int) timeMs;
        int seconds = totalSeconds % 60;

        int minutes = (totalSeconds / 60) % 60;

        int hours = totalSeconds / 3600;

        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d小时%02d分%02秒", hours, minutes, seconds)
                    .toString();
        } else if (minutes > 0) {
            return mFormatter.format("%02d分%02d秒", minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d秒", seconds).toString();
        }
    }

    /**
     * 把毫秒转换成：1：20：30 这里形式
     * @param timeMs
     * @return
     */
    public static String stringForTime(long timeMs) {
        StringBuilder mFormatBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());

        int totalSeconds = (int) (timeMs / 1000);
        int seconds = totalSeconds % 60;

        int minutes = (totalSeconds / 60) % 60;

        int hours = totalSeconds / 3600;

        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02", hours, minutes, seconds)
                    .toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }



}
