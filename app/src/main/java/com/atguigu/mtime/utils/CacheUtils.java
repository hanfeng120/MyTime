package com.atguigu.mtime.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 保存软件的参数
 * <p/>
 * Created by 申瑞芹 on 2015/12/9.
 */
public class CacheUtils {

    private static SharedPreferences sp;

    /**
     * 获取软件保存的参数
     *
     * @param contex 上下文
     * @param key    取出参数的key
     * @return
     */
    public static boolean getBoolean(Context contex, String key) {
        if (sp == null) {
            sp = contex.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, false);// 默认返回false
    }

    /**
     * 保存boolean类型的软件参数
     *
     * @param contex
     * @param key
     * @param values 要保存的值
     */
    public static void setBoolean(Context contex, String key, boolean values) {
        if (sp == null) {
            sp = contex.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, values).commit();
    }

    /**
     * 缓存数据
     *
     * @param contex
     * @param key
     * @param values
     */
    public static void setString(Context contex, String key, String values) {
        if (sp == null) {
            sp = contex.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, values).commit();
    }

    /**
     * 得到缓存的数据
     *
     * @param contex
     * @param key
     * @return
     */
    public static String getString(Context contex, String key) {
        if (sp == null) {
            sp = contex.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getString(key, "");// 默认返回false
    }
}
