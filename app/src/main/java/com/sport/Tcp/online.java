package com.sport.Tcp;

import com.sport.Data.DData;
import com.sport.util.LogUtil;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by xxw on 2016/9/21.
 */
public class online {

    private static DataInputStream dataInputStream = null;
    private static ObjectOutputStream dataToServer = null;
    private static Socket socket = null ;

    public static void start(){

        try {

            socket = new Socket("192.168.0.100",9527);
            Thread a = new Thread(new Receive());
            a.start();
//            Thread b = new Thread(new Send());
//            b.start();

            LogUtil.debug(online.class,"online...");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static class Receive implements Runnable{

        @Override
        public void run() {
            try {
                while(socket!=null){

                    dataInputStream = new DataInputStream(socket.getInputStream());
                    String result = dataInputStream.readUTF();
                    LogUtil.debug(online.class, "received " + result);
                    DData.setData(result);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Send implements Runnable{

        @Override
        public void run() {
            try {
                while(socket!=null){

                    dataToServer = new ObjectOutputStream(socket.getOutputStream());

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void chat(String username,String message){
        try {
            dataToServer = new ObjectOutputStream(socket.getOutputStream());
            dataToServer.writeObject(new message(username,message));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            dataToServer = null;
        }
    }

}
