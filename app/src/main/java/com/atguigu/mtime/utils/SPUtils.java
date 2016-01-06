package com.atguigu.mtime.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 操作SharePreference的工具类
 *
 * Created by 赵迅艺 on 2015/11/27
 */
    public final class SPUtils {

        private static SharedPreferences sp;
        private static SPUtils instance;

        private SPUtils() {

        }

    /**
     * 获得SpUtils的实例
     *
     * @param context
     * @return
     */
    public static SPUtils getInstance(Context context){
        if(instance == null){
            sp = context.getSharedPreferences("mTime", Context.MODE_PRIVATE);
            instance = new SPUtils();
        }
        return instance;
    }

    /**
     * 保存数据
     *
     * @param key
     * @param value 支持String,int,boolean三种类型
     */
    public void save(String key,Object value){
        if(value instanceof String){
            sp.edit().putString(key, (String)value).commit();
        } else if(value instanceof Integer){
            sp.edit().putInt(key, (Integer)value).commit();
        } else if(value instanceof Boolean){
            sp.edit().putBoolean(key, (Boolean)value).commit();
        }
    }

    /**
     * 根据key获取对应的值，返回结果或者默认值
     *
     * @param key
     * @param defValue
     * @return
     */
    public <T> T getValue(String key,T defValue){
        T t = null;
        if(defValue == null || defValue instanceof String){
            t = (T) sp.getString(key, (String) defValue);
        }else if(defValue instanceof Integer){
            Integer value = sp.getInt(key, (Integer) defValue);
            t = (T) value;
        }else if(defValue instanceof Boolean){
            Boolean value = sp.getBoolean(key, (Boolean) defValue);
            t = (T) value;
        }
        return t;
    }

    /**
     * 根据key移除对应的value
     *
     * @param key
     */
    public void remove(String key){
        sp.edit().remove(key).commit();
    }


}
