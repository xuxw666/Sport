package com.sport.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sport.R;
import com.sport.fragment.child_fragment.quanzi.FriendChildFragment;
import com.sport.fragment.child_fragment.quanzi.RecChildFragment;
import com.sport.util.FragmentManagerUtil;
import com.sport.util.LogUtil;

/**
 * Created by xxw on 2016/9/4.
 */
public class MainContentMainFragmentQuanzi extends Fragment implements View.OnClickListener{

    private View rootView;
    private int current ;

    public View onCreateView(LayoutInflater layoutInflater,ViewGroup container,Bundle savedInstanceState){
        rootView = layoutInflater.inflate(R.layout.main_content_quanzi,container,false);

        setDefault();

        setEvent();

        setDefaultFragment();

        LogUtil.debug(this.getClass(), "Quanzi fragment is created");
        return rootView;
    }

    private TextView tuijian;
    private TextView pengyou;
    private void setDefault(){
        tuijian = (TextView)rootView.findViewById(R.id.quanzi_title_tuijian);
        pengyou = (TextView)rootView.findViewById(R.id.quanzi_title_pengyou);

        LogUtil.debug(this.getClass(), "获取到个人中心中的组件。");
    }

    private void setEvent(){
        tuijian.setOnClickListener(this);
        pengyou.setOnClickListener(this);

        LogUtil.debug(this.getClass(), "组件事件监听设置完毕。");
    }

    private void setDefaultFragment(){
        FragmentTransaction ft = FragmentManagerUtil.getFragmentTransaction();
        ft.replace(R.id.quanzi_main_content, new RecChildFragment());
        ft.commit();
        current = 1;
        LogUtil.debug(this.getClass(), "默认显示推荐圈，设置完毕");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.quanzi_title_tuijian:
                if (current!=1)
                    recommend();
                break;
            case R.id.quanzi_title_pengyou:
                if(current!=2)
                    friend();
                break;

        }
    }

    private void recommend(){
        FragmentTransaction ft = FragmentManagerUtil.getFragmentTransaction();
        ft.setCustomAnimations(R.animator.slide_in_left,R.animator.slide_out_right);
        ft.replace(R.id.quanzi_main_content, new RecChildFragment());
        ft.commit();
        current = 1;
        LogUtil.debug(this.getClass(), "圈子-->推荐圈");
    }

    private void friend(){
        FragmentTransaction ft = FragmentManagerUtil.getFragmentTransaction();
        ft.setCustomAnimations(R.animator.slide_in_left,R.animator.slide_out_right);
        ft.replace(R.id.quanzi_main_content, new FriendChildFragment());
        ft.commit();
        current = 2;
        LogUtil.debug(this.getClass(), "圈子-->朋友圈");
    }

}
