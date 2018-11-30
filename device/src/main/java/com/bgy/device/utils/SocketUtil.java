package com.bgy.device.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketUtil {

    public static String send(String host,Integer port,String message) {
        Socket socket = null;
        try {
            socket = new Socket(host,port);
            DataOutputStream toServerStream = new DataOutputStream(socket.getOutputStream());
            toServerStream.write(message.getBytes());
            toServerStream.flush();

            //接收服务端数据
            DataInputStream fromServerStream = new DataInputStream(socket.getInputStream());
            String info="";
            String temp=null;//临时变量
            while((temp=fromServerStream.readUTF())!=null){
                info += temp;
            }

            //关闭相对应的资源
            toServerStream.close();
            fromServerStream.close();

            return info;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    socket = null;
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
