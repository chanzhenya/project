package com.bgy.robot.accesscontrol;


import com.bgy.robot.accesscontrol.web.socket.SocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.bgy.robot.accesscontrol.mapper")
public class AccessControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessControlApplication.class, args);

        //启动socket服务器
//        SocketServer server = new SocketServer();
//        server.startSocketServer(8088);
    }
}
