package com.bgy.robot.accesscontrol.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private  static NetDataTypeTransform transform;

    private static DataInputStream fromServerStream;

    private static DataOutputStream toServerStream;

    private static NetDataCommand dataCommand;

    public static void main(String [] args) {
        try {
            Socket socket = new Socket("10.8.102.149",8888);
            toServerStream = new DataOutputStream(socket.getOutputStream());

            //发送要发送文件的指令
            int id = 1;
            String tempStr = "4.jpg";
            dataCommand = new NetDataCommand(id,tempStr);
            toServerStream.write(dataCommand.getByteArrayData());
            toServerStream.flush();

            //启动发送文件线程
            SendFileThread sendFileThread = new SendFileThread(socket);
            sendFileThread.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
