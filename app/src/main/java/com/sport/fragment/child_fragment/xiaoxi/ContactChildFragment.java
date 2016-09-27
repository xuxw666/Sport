package com.sport.fragment.child_fragment.xiaoxi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import com.sport.R;
import com.sport.fragment.child_fragment.BaseFragment;
import com.sport.fragment.view.messageListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxw on 2016/9/6.
 */
public class ContactChildFragment extends BaseFragment {

    private View rootView;

    public View onCreateView(LayoutInflater layoutInflater,ViewGroup container ,Bundle savedInstanceState){
        rootView = layoutInflater.inflate(R.layout.child_contact_fragment,container,false);

        setDefault();

        return rootView;
    }

    private LinearLayout content_xiaoxi;
    private SimpleAdapter messageAdapter;
    //    private messageListView l ;
    private List<Map<String,Object>> messageLists;
    private String[] name = {"汪泺","徐肖威"};
    private String[] desc = {"[签名]欢迎打扰我...","[签名]正在学习中..."};
    private int[] image = {R.drawable.touxiang,R.drawable.touxiang};
    private messageListView mlv;
    private void setDefault(){

        mlv = (messageListView)rootView.findViewById(R.id.friendLists);
        mlv.setActivity(getActivity(),R.id.fillContent);
        content_xiaoxi = (LinearLayout)rootView.findViewById(R.id.content_friend);
        content_xiaoxi.setOnTouchListener(this);
        content_xiaoxi.setLongClickable(true);

        messageLists = new ArrayList<Map<String, Object>>();
        for (int i=0;i<name.length;i++){
            Map<String,Object> messagelist = new HashMap<String, Object>();
            messagelist.put("name",name[i]);
            messagelist.put("desc",desc[i]);
            messagelist.put("image",image[i]);
            messageLists.add(messagelist);
        }
        messageAdapter = new SimpleAdapter(getActivity(),messageLists,R.layout.messagelist,
                new String[]{"image","name","desc"},new int[]{R.id.message_list_image,R.id.message_list_name,R.id.message_list_desc});
//        mlv = (messageListView)rootView.findViewById(R.id.messageLists);
        mlv.setAdapter(messageAdapter);

    }

}
