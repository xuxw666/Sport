package com.sport.fragment.child_fragment.xiaoxi;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sport.Data.ChatData;
import com.sport.R;
import com.sport.fragment.child_fragment.BaseFragment;


/**
 * Created by xxw on 2016/4/15.
 */

public class chatFragment extends BaseFragment {
    private View rootView;

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.xiaozhitiao_chatfragment, container, false);

        setDefault();

        return rootView;

    }

    private TextView chat_name;
    private ImageView backMessage_chat;
    private GestureDetector mGestureDetector;
    private ChatData chatData = new ChatData();
    private LinearLayout root_liaotian;
    public void setDefault(){
        chat_name = (TextView)rootView.findViewById(R.id.chat_name);
        backMessage_chat = (ImageView)rootView.findViewById(R.id.backMessage_chat);
        backMessage_chat.setOnClickListener(this);

        chat_name.setText(chatData.getName());

        root_liaotian = (LinearLayout)rootView.findViewById(R.id.root_liaotian);
        root_liaotian.setOnTouchListener(this);
        root_liaotian.setLongClickable(true);

        mGestureDetector = new GestureDetector((GestureDetector.OnGestureListener) this);
        super.setmGestureDetector(mGestureDetector);
    }
}
