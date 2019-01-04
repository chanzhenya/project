package com.bgy.robot.socket;

import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.charset.Charset;

/**
 * @author Judith
 * @date 2019/1/4
 * 参考网站：https://blog.csdn.net/suph1990/article/details/77488236/
 */
public class SocketServer {

    //解码buffer
    private Charset charset = Charset.forName("UTF-8");

    //发送数据缓冲区
    private static ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

    //接受数据缓冲区
    private static ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);

    //选择器（叫监听器更准确些吧应该）
    private static Selector selector;

    /**
     * 启动socket服务，开启监听
     * @param port
     */
    public void startSocketServer(int port) {

    }
}
