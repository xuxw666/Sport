package com.sport.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sport.R;

/**
 * Created by xxw on 2016/9/4.
 */
public class MainContentMainFragmentPaobu extends Fragment {

    private View rootView;

    public View onCreateView(LayoutInflater layoutInflater,ViewGroup container,Bundle savedInstanceState){
        rootView = layoutInflater.inflate(R.layout.main_content_paobu,container,false);
        setDefault();
        return rootView;
    }

    public void setDefault(){

    }
}
