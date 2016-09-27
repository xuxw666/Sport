package com.sport.fragment.view;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;

import com.sport.Data.ChatData;
import com.sport.fragment.child_fragment.xiaoxi.chatFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xxw on 2016/4/13.
 */
public class messageListView extends baseListView{

    private int rightSlip = 0;
    private int leftSlip = -110;
//    private boolean canTouch = false;

    public messageListView(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

    private Activity Acitivity;
    private int contentFragment;
    private ChatData chatData ;
    public void setActivity(Activity activity,int contentFragment){
        this.Acitivity = activity;
        this.contentFragment = contentFragment;
    }


    private float startX;
    private float startY;
    private int preMessage = -2;
    private int curMessage = -1;
    private boolean firstStore = true;
    private Rect storeList = new Rect();


//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event){
////        Log.i("xxw","canTouch "+canTouch);
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                Log.i("xxw","disTouch Down");
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.i("xxw","dis Touch Up");
//                return false;
//        }
//        Log.i("xxw","dis Touch " + super.dispatchTouchEvent(event));
//        return super.dispatchTouchEvent(event);
//    }

    @Override
    public boolean onDown(MotionEvent e) {

//        Log.i("xxw","Down  "+ canTouch);
        if (firstStore){
            storeList.set(this.getLeft(),this.getTop(),this.getRight(),this.getBottom());
            firstStore = false;
        }
        startX = e.getX();
        startY = e.getY();
        setCurMessage();

        firstMove = true;

//        canTouch = false;
        return super.onDown(e);
    }


    public void setCurMessage(){
        if (isTouchOnMessage()){
            curMessage = (int)startY/123;
            if (curMessage!=preMessage&&curMessage>=0&&preMessage>=0){
                MessageSlip(preMessage,rightSlip);
            }
        }else {
//            canTouch = false;
            if (preMessage>=0)
                MessageSlip(preMessage,rightSlip);
        }
    }

    public boolean isTouchOnMessage(){
        if(startY<=getChildCount()*123)
            return true;
        else
            return false;
    }


    @Override
    public boolean onSingleTapUp(MotionEvent event){
//        canClick = true;
//        Log.i("xxw","SingleTapUp  "+canTouch);
        chatData = new ChatData();
        if (isTouchOnMessage()){
            Map<String,Object> d = (HashMap<String,Object>)this.getAdapter().getItem(curMessage);
            chatData.setName(d.get("name").toString());
            chatData.setImage((int)d.get("image"));
            chatData.setDesc(d.get("desc").toString());
            FragmentTransaction ft =  Acitivity.getFragmentManager().beginTransaction();
            ft.replace(contentFragment,new chatFragment());
            ft.commit();
        }


        return false;
    }

    private boolean canScroll = false;
    private boolean firstMove = true;
    //只执行了move，起到了"拖"的作用,e1是起点，即你按下去的那个点，e2是移动过程中的点，即你在拖动的时候所经过的点
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.i("xxw", preMessage + "" + curMessage);
        float y2 = e2.getY();
        float dy = y2-startY;
        startY = y2;
        if (firstMove){
            float xx = Math.abs(e2.getX() - e1.getX());
            float yy = Math.abs(e2.getY() - e1.getY());
            if (yy>xx)
                canScroll = true;
            firstMove = false;
        }
        if (Math.abs(distanceY)>distanceX&&canScroll){
            if(super.isNeedMove()&& Math.abs(y2 - e1.getY())<=450){
                this.layout(this.getLeft(),this.getTop()+(int)dy/3,this.getRight(),this.getBottom()+(int)dy/3);
            }
        }
        return false;
    }



    //只执行了UP，起到了"甩"的作用,e1是起点，即你按下时的那个点，e2是终点，即你松开时的那个点
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.i("xxw", preMessage + "" + curMessage);
        float dx = e2.getX() - e1.getX();
        float dy = e2.getY() - e1.getY();
        if (velocityX<0&& Math.abs((int) velocityX)>velocityY&&startY<getChildCount()*123){
            //判断为左滑
            Log.i("xxw", preMessage + "" + curMessage);
            MessageSlip(curMessage, leftSlip);

        }else if (velocityX>0&& Math.abs((int) velocityX)>velocityY&&startY<getChildCount()*123){
            //判断为右滑
            if (preMessage == curMessage)
                MessageSlip(curMessage,rightSlip);
        }else {
            //判断为垂直滑动时的效果
            super.listMoveBack();
            Log.i("xxw", preMessage + "" + curMessage);
        }
        Log.i("xxw", preMessage + "" + curMessage);
        return false;
    }


    //将左滑和恢复结合在了一起
    public void MessageSlip(int item,int operation){
        Animation Slip = null;

        if (operation == leftSlip){
            Slip = new TranslateAnimation(rightSlip,leftSlip,0,0);
            preMessage = curMessage ;
        }
        else if (operation == rightSlip){
            Slip = new TranslateAnimation(leftSlip,rightSlip,0,0);
            preMessage = -2 ;
        }
        Slip.setDuration(500);
        Slip.setInterpolator(new DecelerateInterpolator());
        Slip.setFillAfter(true);
        this.getChildAt(item).startAnimation(Slip);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
////        Log.i("xxw","canTouch "+canTouch);
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                Log.i("xxw","Touch Down");
//                return true;
////                break;
//            case MotionEvent.ACTION_UP:
//                Log.i("xxw","Touch Up");
//                return false;
////                return false;
//        }
//        return ;
//    }
}
