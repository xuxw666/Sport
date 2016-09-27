package com.sport.Tcp;

import com.sport.util.LogUtil;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by xxw on 2016/9/21.
 */
public class chatTo {

    public chatTo(){

        try {

            Socket socket = new Socket("192.168.1.112",9527);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            ObjectOutputStream dataToServer = new ObjectOutputStream(socket.getOutputStream());
            dataToServer.writeObject(new message("xxw","hahaha"));
            LogUtil.debug(this.getClass(),"received " + dataInputStream.readUTF());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
