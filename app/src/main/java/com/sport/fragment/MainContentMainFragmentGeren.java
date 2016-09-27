package com.sport.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sport.R;
import com.sport.fragment.notification_fragment.AchieveNotiFragment;
import com.sport.fragment.notification_fragment.AnalysisNotiFragment;
import com.sport.fragment.notification_fragment.LoginNotiFragment;
import com.sport.fragment.notification_fragment.PlanNotiFragment;
import com.sport.fragment.notification_fragment.SettingNotiFragment;
import com.sport.fragment.notification_fragment.WeightNotiFragment;
import com.sport.util.FragmentManagerUtil;
import com.sport.util.LogUtil;

/**
 * Created by xxw on 2016/9/4.
 */
public class MainContentMainFragmentGeren extends Fragment implements View.OnClickListener{
    private View rootView;

    public View onCreateView(LayoutInflater layoutInflater,ViewGroup container,Bundle savedInstanceState){
        rootView = layoutInflater.inflate(R.layout.main_content_geren,container,false);

        setDefault();

        setEvent();

        LogUtil.debug(this.getClass(), "Geren fragment is created");
        return rootView;
    }

    private TextView geren_button_login;
    private TextView geren_button_analysis;
    private TextView geren_button_weight;
    private TextView geren_button_plan;
    private TextView geren_button_achieve;
    private TextView geren_button_setting;
    private void setDefault(){

        geren_button_login = (TextView)rootView.findViewById(R.id.geren_button_login);
        geren_button_analysis = (TextView)rootView.findViewById(R.id.shujufenxi);
        geren_button_weight = (TextView)rootView.findViewById(R.id.tizhongguanli);
        geren_button_plan = (TextView)rootView.findViewById(R.id.xunlianjihua);
        geren_button_achieve = (TextView)rootView.findViewById(R.id.chengjiuqiang);
        geren_button_setting = (TextView)rootView.findViewById(R.id.shezhi);

        LogUtil.debug(this.getClass(), "获取到个人中心中的组件。");
    }

    private void setEvent(){
        geren_button_login.setOnClickListener(this);
        geren_button_analysis.setOnClickListener(this);
        geren_button_weight.setOnClickListener(this);
        geren_button_plan.setOnClickListener(this);
        geren_button_achieve.setOnClickListener(this);
        geren_button_setting.setOnClickListener(this);


        LogUtil.debug(this.getClass(), "组件事件监听设置完毕。");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.geren_button_login:
                login();
                break;
            case R.id.shujufenxi:
                analysis();
                break;
            case R.id.tizhongguanli:
                weight();
                break;
            case R.id.xunlianjihua:
                plan();
                break;
            case R.id.chengjiuqiang:
                achieve();
                break;
            case R.id.shezhi:
                setting();
                break;

        }
    }

    private void login(){
        FragmentTransaction ft = FragmentManagerUtil.getFragmentTransaction();
        ft.setCustomAnimations(R.animator.slide_in_right,R.animator.slide_out_left);
        ft.replace(R.id.fillContent, new LoginNotiFragment());
        ft.commit();
        LogUtil.debug(this.getClass(), "个人中心-->登录");
    }

    private void analysis(){
        FragmentTransaction ft = FragmentManagerUtil.getFragmentTransaction();
        ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);
        ft.replace(R.id.fillContent, new AnalysisNotiFragment());
        ft.commit();
        LogUtil.debug(this.getClass(), "个人中心-->数据分析");
    }

    private void weight(){
        FragmentTransaction ft = FragmentManagerUtil.getFragmentTransaction();
        ft.setCustomAnimations(R.animator.slide_in_right,R.animator.slide_out_left);
        ft.replace(R.id.fillContent, new WeightNotiFragment());
        ft.commit();
        LogUtil.debug(this.getClass(), "个人中心-->体重管理");
    }

    private void plan(){
        FragmentTransaction ft = FragmentManagerUtil.getFragmentTransaction();
        ft.setCustomAnimations(R.animator.slide_in_right,R.animator.slide_out_left);
        ft.replace(R.id.fillContent, new PlanNotiFragment());
        ft.commit();
        LogUtil.debug(this.getClass(), "个人中心-->训练计划");
    }

    private void achieve(){
        FragmentTransaction ft = FragmentManagerUtil.getFragmentTransaction();
        ft.setCustomAnimations(R.animator.slide_in_right,R.animator.slide_out_left);
        ft.replace(R.id.fillContent, new AchieveNotiFragment());
        ft.commit();
        LogUtil.debug(this.getClass(), "个人中心-->成就墙");
    }

    private void setting(){
        FragmentTransaction ft = FragmentManagerUtil.getFragmentTransaction();
        ft.setCustomAnimations(R.animator.slide_in_right,R.animator.slide_out_left);
        ft.replace(R.id.fillContent, new SettingNotiFragment());
        ft.commit();
        LogUtil.debug(this.getClass(), "个人中心-->设置");
    }
}
