package com.bgy.robot.accesscontrol.service.impl;

import com.bgy.robot.accesscontrol.common.NetDataCommand;
import com.bgy.robot.accesscontrol.common.NetDataTypeTransform;
import com.bgy.robot.accesscontrol.common.SendFileThread;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.Base64;

@Component
public class AsyncTask {

    private  static NetDataTypeTransform transform;

    private static DataInputStream fromServerStream;

    private static DataOutputStream toServerStream;

    private static NetDataCommand dataCommand;

    /**
     * 获取图片特征值
     * @param photo
     * @param host
     * @param port
     * @return
     */
    public String getEigenvalue(MultipartFile photo, String host, int port) {
        Socket socket = null;
        String result = "";
        try {
            socket = new Socket(host,port);
            toServerStream = new DataOutputStream(socket.getOutputStream());

            //发送要发送文件的指令
            int id = 1;
            String tempStr = photo.getName();
            dataCommand = new NetDataCommand(id,tempStr);
            toServerStream.write(dataCommand.getByteArrayData());
            toServerStream.flush();

            //启动发送文件线程
            SendFileThread sendFileThread = new SendFileThread(socket,photo);
            sendFileThread.start();

            socket.shutdownOutput();

            //接收服务端数据
            fromServerStream = new DataInputStream(socket.getInputStream());
            String info="";
            String temp=null;//临时变量
            while((temp=fromServerStream.readUTF())!=null){
                info += temp;
            }
            result = removeUnreadablCode(info);

            //关闭相对应的资源
            toServerStream.close();
            fromServerStream.close();

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
        return result;
    }

    /**
     * 截取返回数据据中的特征值
     * @param rec
     * @return
     */
    private String removeUnreadablCode(String rec) {
        int idx = 0;
        for(int i=0;i<rec.length();i++) {
            if(rec.charAt(i) == ')') {
                idx = i;
                break;
            }
        }
        String tag = rec.substring(0,idx+1);
        System.out.println(tag);
        return tag;
    }
}
