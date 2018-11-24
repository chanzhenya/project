package com.bgy.robot.accesscontrol.web.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;

/**
 *  socket 服务器
 */
public class SocketServer {

    /**
     * 解码buffer
     */
    private Charset charset = Charset.forName("UTF-8");

    /**
     * 发送数据缓冲去
     */
    private static ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

    /**
     * 接收数据缓冲区
     */
    private static ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);

    /**
     * 选择器（监听器）
     */
    private static Selector selector;


    public void startSocketServer(int port) {
        try {
            //打开通信信道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            //设置为非阻塞
            serverSocketChannel.configureBlocking(false);

            //获取套接字
            ServerSocket serverSocket = serverSocketChannel.socket();

            //绑定端口号
            serverSocket.bind(new InetSocketAddress(port));

            //打开监听器
            selector = Selector.open();

            //将通信信道注册到监听器
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            //监听器会一直监听，如果客户端有请求就会进入相应的事件处理
            while(true) {

                //select方法会一直阻塞直到有关有相关时间发生或超时
                selector.select();

                //监听到的事件
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for(SelectionKey key:selectionKeys) {
                    handle(key);
                }
                //清除处理过的事件
                selectionKeys.clear();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handle(SelectionKey selectionKey) throws IOException {
        ServerSocketChannel serverSocketChannel = null;
        SocketChannel socketChannel = null;
        String requestMsg = "";
        int count = 0;
        if (selectionKey.isAcceptable()) {
            //每有客户端连接，即注册通信信道为可读
            serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
            socketChannel = (SocketChannel) selectionKey.channel();
            receiveBuffer.clear();
            count = socketChannel.read(receiveBuffer);
            //读取数据
            if (count > 0) {
                receiveBuffer.flip();
                requestMsg = String.valueOf(charset.decode(receiveBuffer).array());
            }
            String responseMsg = "已收到客户端的消息:" + requestMsg;
            System.out.println(responseMsg);
            //返回数据
            sendBuffer = ByteBuffer.allocate(responseMsg.getBytes("UTF-8").length);
            sendBuffer.put(responseMsg.getBytes("UTF-8"));
            sendBuffer.flip();
            socketChannel.write(sendBuffer);
            socketChannel.close();
        }
    }
}
