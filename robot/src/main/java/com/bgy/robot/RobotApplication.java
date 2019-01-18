package com.bgy.robot;

import com.bgy.robot.socket.SocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RobotApplication {

    public static void main(String[] args) {
        SpringApplication.run(RobotApplication.class, args);

        //启动socket服务
        SocketServer socketServer = new SocketServer();
        socketServer.startSocketServer(8234);
    }
}
