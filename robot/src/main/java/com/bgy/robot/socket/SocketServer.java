package com.bgy.robot.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bgy.robot.entity.RobotInfo;
import com.bgy.robot.enums.RobotStatus;
import com.bgy.robot.service.RobotService;
import com.bgy.robot.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * nio socket服务端
 */
@Slf4j
public class SocketServer {

    //解码buffer
    private Charset cs = Charset.forName("UTF-8");
    //接受数据缓冲区
    private static ByteBuffer sBuffer = ByteBuffer.allocate(1024);
    //发送数据缓冲区
    private static ByteBuffer rBuffer = ByteBuffer.allocate(1024);
    //选择器（叫监听器更准确些吧应该）
    private static Selector selector;

    private Map<String,SelectionKey> selectionKeyMap = new HashMap<>();

    /**
     * 启动socket服务，开启监听
     * @param port
     * @throws IOException
     */
    public void startSocketServer(int port){
        ServerSocketChannel serverSocketChannel = null;
        try {
            //打开通信信道
            serverSocketChannel = ServerSocketChannel.open();
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
            while (true){
                selector.select();//select方法会一直阻塞直到有相关事件发生或超时
                Set<SelectionKey> selectionKeys = selector.selectedKeys();//监听到的事件
                SelectionKey tmpSelectionKey = null;
                for (SelectionKey key : selectionKeys) {
                    if(key.isValid()) {
                        handle(key);
                    } else {
                        key.cancel();
                        selectionKeys.remove(key);
                    }
                }
                selectionKeys.clear();//清除处理过的事件
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                selector.close();
                if (serverSocketChannel != null) {
                    serverSocketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理不同的事件
     * @param selectionKey
     * @throws IOException
     */
    private void handle(SelectionKey selectionKey) throws IOException {
        ServerSocketChannel serverSocketChannel = null;
        SocketChannel socketChannel = null;
        String requestMsg = "";
        int count = 0;
        if (selectionKey.isAcceptable()) {
            //每有客户端连接，即注册通信信道为可读
            serverSocketChannel = (ServerSocketChannel)selectionKey.channel();
            socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
            socketChannel = (SocketChannel)selectionKey.channel();
            rBuffer.clear();
            String responseMsg = "";
            //读取数据
            try {
                while ((count = socketChannel.read(rBuffer)) > 0) {
                    rBuffer.flip();
                    requestMsg = String.valueOf(cs.decode(rBuffer).array());

                    //数据转化String To Json
                    log.info("[----------- Service Robot Message -----------] " + requestMsg);
                    JSONObject jsonObject = JSONObject.parseObject(requestMsg);
                    String robotId = jsonObject.getString("robotId");
                    Integer requestType = jsonObject.getInteger("type");

                    //保存通信信道channel
                    if(requestType == 3) {
                        selectionKeyMap.put("queuing_machine_channel",selectionKey);
                    } else if(requestType == 1 && StringUtils.isNotBlank(robotId)) {
                        SelectionKey tempKey = selectionKeyMap.get(robotId);
                        if(tempKey != null && !selectionKey.equals(tempKey)) {
                            tempKey.cancel();
                            tempKey.channel().close();
                            selectionKeyMap.put(robotId,selectionKey);
                        } else if(tempKey == null) {
                            selectionKeyMap.put(robotId,selectionKey);
                        }
                    }

                    if (requestType == 1) {
                        //迎宾机器人状态信息上传，更新机器人信息
                        updateRobot(jsonObject);
                        //responseMsg += "{\"type\":2,\"robotId\":\"robotId-007\",\"table\":{\"tableNo\":\"A01\"}}";
                        if(selectionKeyMap.get("queuing_machine_channel") != null) {
                            SocketChannel tmpSocketChannel = (SocketChannel) selectionKeyMap.get("queuing_machine_channel").channel();
                            if (tmpSocketChannel != null) {
                                socketChannel = tmpSocketChannel;
                                responseMsg += findByRobotStatus();
                                log.info(responseMsg);
                            }
                        }
                    } else if (requestType == 3) {
                        //请求获取迎宾机器人状态信息
                        responseMsg += findByRobotStatus();
                        log.info(responseMsg);
                    } else if (requestType == 4) {
                        //接受到餐桌信息及迎宾机器人信息，并将信息转发至服务器机器人
                        jsonObject.put("type", 2);
                        SocketChannel tmpSocketChannel = (SocketChannel) selectionKeyMap.get(robotId).channel();
                        if(tmpSocketChannel != null) {
                            socketChannel = tmpSocketChannel;
                            responseMsg += jsonObject.toJSONString();
                            log.info(responseMsg);
                        }
                    }
                }
                if (count < 0) {
                    log.info("[---------- Socket Channel Close ----------]");
                    socketChannel.close();
                }
                //返回数据
                if (StringUtils.isNotBlank(responseMsg)) {
                    sBuffer = ByteBuffer.allocate(responseMsg.getBytes("UTF-8").length);
                    sBuffer.put(responseMsg.getBytes("UTF-8"));
                    sBuffer.flip();
                    socketChannel.write(sBuffer);
                    sBuffer.clear();
                }
            } catch (IOException e) {
                log.info("[---------- Socket Channel IOException Close ----------]");
                selectionKey.cancel();
                socketChannel.socket().close();
                socketChannel.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 更新机器人状态
     * 请求类型：1
     * @param robotJson
     * @return
     */
    private void updateRobot(JSONObject robotJson) {
        RobotService robotService = SpringUtil.getBean(RobotService.class);
        RobotInfo robotInfo = new RobotInfo();
        robotInfo.setRobotId(robotJson.getString("robotId"));
        robotInfo.setRobotStatus(robotJson.getInteger("status"));
        robotService.updateRobot(robotInfo);
    }

    /**
     * 查询空闲的机器人
     * 请求类型：3
     * @return
     */
    private String findByRobotStatus() {
        RobotService robotService = SpringUtil.getBean(RobotService.class);
        List<RobotInfo> robotInfos = robotService.findAll();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",robotInfos);
        return jsonObject.toJSONString();
    }
}