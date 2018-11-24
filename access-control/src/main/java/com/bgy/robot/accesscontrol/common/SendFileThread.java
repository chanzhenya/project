package com.bgy.robot.accesscontrol.common;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

public class SendFileThread extends Thread implements Runnable {

    private Socket socket;

    private  NetDataCommand dataCommand;

    private  NetDataTypeTransform transform = new NetDataTypeTransform();

    private MultipartFile multipartFile;

    public SendFileThread(Socket socket, MultipartFile multipartFile){
        this.socket = socket;
        this.multipartFile = multipartFile;
    }

    @Override
    public void run() {
        try {
            DataInputStream fromServerStream = new DataInputStream(socket.getInputStream());
            DataOutputStream toServerStream = new DataOutputStream(socket.getOutputStream());

            //初始化文件信息
//            File file = new File("C:\\Users\\Judith\\Desktop\\文件\\组员照片\\梁以铭.jpg");
//            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream fileInputStream = multipartFile.getInputStream();

            //1. 发送图片的分辨率
            int fileLen = (int) multipartFile.getSize();
            BufferedImage image = ImageIO.read(fileInputStream);
            int width = image.getWidth();
            int height = image.getHeight();
            toServerStream.write(transform.StringToByteArray(width+"_"+height));
            toServerStream.flush();

            //2. 确认服务端已经做好准备
            Boolean isOk;
            isOk = fromServerStream.readBoolean();
            System.out.println(isOk);

            byte[] toServerByte = new byte[2048];
            int len; //每次发送长度
            long sendLen=0; //已经发送的总长度
            while((len = fileInputStream.read(toServerByte)) > 0) {
                toServerStream.write(toServerByte,0,len);
                toServerStream.flush();
                sendLen+=len;
                System.out.println("len = "+len);
                System.out.println("发送经度："+(sendLen*1.0/fileLen*1.0*100)+"%");
            }

            sleep(10);

            if(sendLen == fileLen) {
                System.out.println("Success");
            }
            System.out.println("send over!");

            //资源关闭
            fromServerStream.close();
            toServerStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
