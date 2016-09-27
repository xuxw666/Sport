package com.sport.util;

import android.util.Log;

/**
 * Created by xxw on 2016/9/10.
 */
public class LogUtil {
    public static void debug(Class className,String message){
        Log.d(className + "-->xxw", message);
    }
}
