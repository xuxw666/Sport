package com.sport.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xxw on 2016/9/4.
 */
public class SharedPreferencesUtil {

    private static SharedPreferences sharedPreferences;

    public static SharedPreferences getSharedPreferences(Context context,String name){
        SharedPreferencesUtil.sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return SharedPreferencesUtil.sharedPreferences;

    }

}
