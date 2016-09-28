package com.sport.fragment.child_fragment.xiaoxi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sport.Data.ChatData;
import com.sport.Data.DData;
import com.sport.R;
import com.sport.Tcp.online;
import com.sport.fragment.child_fragment.BaseFragment;


/**
 * Created by xxw on 2016/4/15.
 */

public class chatFragment extends BaseFragment {
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    content_message.setText(DData.getData());
                    break;
            }
        }
    };
    private View rootView;

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.xiaozhitiao_chatfragment, container, false);

        setDefault();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(DData.getFlag()==1){
                        handler.sendEmptyMessage(1);
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        DData.setFlag(0);
                    }
                }
            }
        }).start();
        return rootView;

    }

    private TextView chat_name;
    private ImageView backMessage_chat;
    private GestureDetector mGestureDetector;
    private ChatData chatData = new ChatData();
    private LinearLayout root_liaotian;
    private TextView content_message;
    private Button send_message;
    private EditText current_message;
    public void setDefault(){
        content_message = (TextView)rootView.findViewById(R.id.content_message);
        content_message.setText(DData.getData());
        send_message = (Button)rootView.findViewById(R.id.send_message);
        current_message = (EditText)rootView.findViewById(R.id.current_message);
        chat_name = (TextView)rootView.findViewById(R.id.chat_name);
        backMessage_chat = (ImageView)rootView.findViewById(R.id.backMessage_chat);
        backMessage_chat.setOnClickListener(this);

        send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = current_message.getText().toString();

                online.chat("xxw",message);

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                handler.sendEmptyMessage(1);

                current_message.setText("");
            }
        });

        chat_name.setText(chatData.getName());

        root_liaotian = (LinearLayout)rootView.findViewById(R.id.root_liaotian);
        root_liaotian.setOnTouchListener(this);
        root_liaotian.setLongClickable(true);

        mGestureDetector = new GestureDetector((GestureDetector.OnGestureListener) this);
        super.setmGestureDetector(mGestureDetector);
    }
}
