package com.sport.fragment.notification_fragment;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sport.R;
import com.sport.util.LogUtil;

/**
 * Created by xxw on 2016/9/10.
 */
public class PlanNotiFragment extends BaseNotiFragment {

    private View rootView;

    public View onCreateView(LayoutInflater layoutInflater,ViewGroup container,Bundle savedInstanceState){
        rootView = layoutInflater.inflate(R.layout.geren_noti_plan_fragment,container,false);

        setBackButton((TextView)rootView.findViewById(R.id.back_to_fragment));

        setDefault();

        LogUtil.debug(this.getClass(), "Plan fragment is created");
        return rootView;
    }

    private GestureDetector mGestureDetector;
    private RelativeLayout root;
    private void setDefault(){

        root = (RelativeLayout)rootView.findViewById(R.id.root);
        root.setOnTouchListener(this);
        root.setLongClickable(true);

        mGestureDetector = new GestureDetector((GestureDetector.OnGestureListener) this);
        super.setmGestureDetector(mGestureDetector);
    }
}
