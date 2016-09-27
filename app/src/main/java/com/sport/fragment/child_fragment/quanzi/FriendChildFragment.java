package com.sport.fragment.child_fragment.quanzi;

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
public class FriendChildFragment extends BaseFragment {

    private View rootView;

    public View onCreateView(LayoutInflater layoutInflater,ViewGroup container ,Bundle savedInstanceState){
        rootView = layoutInflater.inflate(R.layout.child_friend_fragment,container,false);

        setDefault();

        return rootView;
    }

    private LinearLayout content_quanzi_friend;
    private SimpleAdapter friendAdapter;
    private List<Map<String,Object>> friendLists;
    private String[] name = {"晶儿","徐肖威"};
    private String[] desc = {"来俩俩俩来来来欢迎打扰我...","这真的是我的第一条朋友圈正在学习中..."};
    private int[] image = {R.drawable.touxiang,R.drawable.touxiang};
    private int[] goods = {99,12};
    private int[] pingluns = {11,87};
    private String[] times = {"2016-09-24 11:12:10","2016-08-12 09:12:48"};
    private messageListView mlv;
    private void setDefault(){

        content_quanzi_friend = (LinearLayout)rootView.findViewById(R.id.content_qunazi_friend);
        mlv = (messageListView)rootView.findViewById(R.id.messageLists);
        content_quanzi_friend.setOnTouchListener(this);
        content_quanzi_friend.setLongClickable(true);

        friendLists = new ArrayList<Map<String, Object>>();
        for (int i=0;i<name.length;i++){
            Map<String,Object> messagelist = new HashMap<String, Object>();
            messagelist.put("name",name[i]);
            messagelist.put("desc",desc[i]);
            messagelist.put("image",image[i]);
            messagelist.put("goods",goods[i]);
            messagelist.put("pingluns",pingluns[i]);
            messagelist.put("time",times[i]);
            friendLists.add(messagelist);
        }

        friendAdapter = new SimpleAdapter(getActivity(),friendLists,R.layout.quanzi_friend_list,
                new String[]{"image","name","desc","goods","pingluns","time"},new int[]{R.id.message_list_image,R.id.message_list_name,R.id.message_list_desc,R.id.goods,R.id.pingluns,R.id.message_list_time});
        mlv.setAdapter(friendAdapter);
    }

}
