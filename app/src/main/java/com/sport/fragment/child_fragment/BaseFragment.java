package com.sport.fragment.child_fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.sport.R;

/**
 * Created by xxw on 2016/4/11.
 */
public class BaseFragment extends Fragment implements View.OnClickListener,GestureDetector.OnGestureListener,View.OnTouchListener{
//    private int viewId ;
    private GestureDetector mGestureDetector;
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        return null;
    }

    @Override
    public void onClick(View v) {
        back();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int MinDistance = 20;
        int minV = 0;
        Log.i("xxw", "Fragment onFling start");
        if (e2.getX() - e1.getX() > MinDistance && Math.abs(velocityX) > minV) {

            back();
        }

        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i("xxw", "Fragment onTouch start");
        return mGestureDetector.onTouchEvent(event);
//        return true;
    }

    public void back(){
        FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.animator.slide_in_left,R.animator.slide_out_right);
        ft.replace(R.id.fillContent, new Fragment());
        ft.commit();
    }

//    public void setViewId(int viewId){
//        this.viewId = viewId;
//    }


    public void setmGestureDetector(GestureDetector mGestureDetector) {
        this.mGestureDetector = mGestureDetector;
    }

}
