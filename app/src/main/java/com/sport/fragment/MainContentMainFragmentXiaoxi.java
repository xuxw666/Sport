package com.sport.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sport.R;
import com.sport.fragment.child_fragment.xiaoxi.ContactChildFragment;
import com.sport.fragment.child_fragment.xiaoxi.MessageChildFragment;
import com.sport.util.FragmentManagerUtil;
import com.sport.util.LogUtil;

/**
 * Created by xxw on 2016/9/4.
 */
public class MainContentMainFragmentXiaoxi extends Fragment implements View.OnClickListener{

    private View rootView;
    private int current;

    public View onCreateView(LayoutInflater layoutInflater,ViewGroup container,Bundle savedInstanceState){
        rootView = layoutInflater.inflate(R.layout.main_content_xiaoxi,container,false);
        setDefault();

        setEvent();

        setDefaultFragment();

        LogUtil.debug(this.getClass(), "Xiaoxi fragment is created");
        return rootView;
    }

    private TextView xiaoxi;
    private TextView lianxi;
    private void setDefault(){
        xiaoxi = (TextView)rootView.findViewById(R.id.message_button);
        lianxi = (TextView)rootView.findViewById(R.id.contact_button);

        LogUtil.debug(this.getClass(), "获取到个人中心中的组件。");
    }

    private void setEvent(){
        xiaoxi.setOnClickListener(this);
        lianxi.setOnClickListener(this);

        LogUtil.debug(this.getClass(), "组件事件监听设置完毕。");
    }

    private void setDefaultFragment(){
        FragmentTransaction ft = FragmentManagerUtil.getFragmentTransaction();
        ft.replace(R.id.xiaoxi_main_content, new MessageChildFragment());
        ft.commit();
        current = 1;
        LogUtil.debug(this.getClass(), "默认显示消息列表，设置完毕");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.message_button:
                if (current!=1)
                    message();
                break;
            case R.id.contact_button:
                if(current!=2)
                    contact();
                break;

        }
    }

    private void message(){
        FragmentTransaction ft = FragmentManagerUtil.getFragmentTransaction();
        ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
        ft.replace(R.id.xiaoxi_main_content, new MessageChildFragment());
        ft.commit();
        current = 1;
        LogUtil.debug(this.getClass(), "消息-->消息列表");
    }

    private void contact(){
        FragmentTransaction ft = FragmentManagerUtil.getFragmentTransaction();
        ft.setCustomAnimations(R.animator.slide_in_left,R.animator.slide_out_right);
        ft.replace(R.id.xiaoxi_main_content, new ContactChildFragment());
        ft.commit();
        current = 2;
        LogUtil.debug(this.getClass(), "消息-->联系人列表");
    }
}
