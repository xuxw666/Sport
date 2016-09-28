package com.sport;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.sport.Tcp.online;
import com.sport.fragment.MainContentMainFragmentGeren;
import com.sport.fragment.MainContentMainFragmentPaobu;
import com.sport.fragment.MainContentMainFragmentQuanzi;
import com.sport.fragment.MainContentMainFragmentXiaoxi;
import com.sport.util.FragmentManagerUtil;
import com.sport.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{

    private int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        FragmentManagerUtil.setActivity(this);

        setDefault();

        setFragments();

        setBottomBar();

        setIndexFragment();

        setEvent();


        LogUtil.debug(this.getClass(), "activity is created!");
    }


    @Override
    protected void onStart(){
        super.onStart();
        LogUtil.debug(this.getClass(), "activity is started!");
    }

    private Gallery paobu_select_date;

    private void setDefault(){
//        paobu_select_date = (Gallery)findViewById(R.id.paobu_select_date);

        new Thread(new Runnable() {
            @Override
            public void run() {
                online.start();
            }
        }).start();

        LogUtil.debug(this.getClass(), "获取当前组件");
    }


    private void init(){
//        paobu_select_date.setAdapter();
    }

    private List<Fragment> mainFragment ;

    public void setFragments(){

        mainFragment = new ArrayList<Fragment>();
        mainFragment.add(new MainContentMainFragmentPaobu());
        mainFragment.add(new MainContentMainFragmentQuanzi());
        mainFragment.add(new MainContentMainFragmentXiaoxi());
        mainFragment.add(new MainContentMainFragmentGeren());

        LogUtil.debug(this.getClass(), "set fragments success!");
    }

    private List<TextView> bottom_bar_text;
    private List<ImageView> bottom_bar_image;
    private List<Integer> bottom_bar_image_selected;
    private List<Integer> bottom_bar_image_normal;
    public void setBottomBar(){

        bottom_bar_image = new ArrayList<ImageView>();
        bottom_bar_image.add((ImageView)findViewById(R.id.bottom_bar_image_paobu));
        bottom_bar_image.add((ImageView)findViewById(R.id.bottom_bar_image_quanzi));
        bottom_bar_image.add((ImageView)findViewById(R.id.bottom_bar_image_xiaoxi));
        bottom_bar_image.add((ImageView)findViewById(R.id.bottom_bar_image_geren));

        bottom_bar_image_selected = new ArrayList<Integer>();
        bottom_bar_image_selected.add(R.drawable.icon_selected_paobu);
        bottom_bar_image_selected.add(R.drawable.icon_selected_quanzi);
        bottom_bar_image_selected.add(R.drawable.icon_selected_xiaozhitiao);
        bottom_bar_image_selected.add(R.drawable.icon_selected_geren);

        bottom_bar_image_normal = new ArrayList<Integer>();
        bottom_bar_image_normal.add(R.drawable.icon_nomal_paobu);
        bottom_bar_image_normal.add(R.drawable.icon_nomal_quanzi);
        bottom_bar_image_normal.add(R.drawable.icon_nomal_xiaozhitiao);
        bottom_bar_image_normal.add(R.drawable.icon_nomal_geren);

        bottom_bar_text = new ArrayList<TextView>();
        bottom_bar_text.add((TextView)findViewById(R.id.bottom_bar_text_paobu));
        bottom_bar_text.add((TextView)findViewById(R.id.bottom_bar_text_quanzi));
        bottom_bar_text.add((TextView)findViewById(R.id.bottom_bar_text_xiaoxi));
        bottom_bar_text.add((TextView)findViewById(R.id.bottom_bar_text_geren));

        LogUtil.debug(this.getClass(), "set bottom bar success!");
    }

    public void setEvent(){
        this.findViewById(R.id.bottom_bar_paobu).setOnClickListener(this);
        this.findViewById(R.id.bottom_bar_quanzi).setOnClickListener(this);
        this.findViewById(R.id.bottom_bar_xiaoxi).setOnClickListener(this);
        this.findViewById(R.id.bottom_bar_geren).setOnClickListener(this);

        LogUtil.debug(this.getClass(), "set bar event success!");
    }

    public void setIndexFragment(){

        FragmentTransaction ft = FragmentManagerUtil.getFragmentTransaction();
        ft.replace(R.id.main_content,mainFragment.get(0));
        ft.commit();

        LogUtil.debug(this.getClass(), "set default fragment success!");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bottom_bar_paobu :
                changeFragment(0);
                break;
            case R.id.bottom_bar_quanzi :
                changeFragment(1);
//                Thread a = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        new online();
//                    }
//                });
//                a.start();
                break;
            case R.id.bottom_bar_xiaoxi :
                changeFragment(2);
                break;
            case R.id.bottom_bar_geren :
                changeFragment(3);
                break;
        }

    }

    public void changeFragment(int index){
        LogUtil.debug(this.getClass(), "now change to " + index + " fragment.");
        if(current!=index){
            //按钮文字和图片状态的改变
            bottom_bar_text.get(current).setTextColor(getResources().getColor(R.color.normal_textColor));
            bottom_bar_image.get(current).setImageResource(bottom_bar_image_normal.get(current));
            bottom_bar_text.get(index).setTextColor(getResources().getColor(R.color.selected_textColor));
            bottom_bar_image.get(index).setImageResource(bottom_bar_image_selected.get(index));

            FragmentTransaction ft = FragmentManagerUtil.getFragmentTransaction();
            ft.replace(R.id.main_content,mainFragment.get(index));
            ft.commit();

            current = index;

        }
    }
}
