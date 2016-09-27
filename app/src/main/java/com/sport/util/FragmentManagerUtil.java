package com.sport.util;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by xxw on 2016/9/4.
 */
public class FragmentManagerUtil {

    private static FragmentManager fragmentManager = null;
    private static FragmentTransaction fragmentTransaction = null;
    private static Activity activity = null;

    public static void setActivity(Activity activity){
        FragmentManagerUtil.activity = activity;
        FragmentManagerUtil.fragmentManager = activity.getFragmentManager();
    }

    public static FragmentManager getFragmentManager(){
        if(fragmentManager==null)
            LogUtil.debug(FragmentManagerUtil.class, "fragmentManager is null , pleease set Activity . ");
        return FragmentManagerUtil.fragmentManager;
    }

    public static FragmentTransaction getFragmentTransaction(){
        if (fragmentManager==null){
            LogUtil.debug(FragmentManagerUtil.class, "could not get fragmentTransaction ,  please set fragmentManager");
        }else{
            fragmentTransaction = fragmentManager.beginTransaction();
        }
        return FragmentManagerUtil.fragmentTransaction;
    }

}
