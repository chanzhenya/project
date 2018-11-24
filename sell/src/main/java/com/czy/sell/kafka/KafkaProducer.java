package com.czy.sell.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

//@Component
//@EnableScheduling
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

   // @Scheduled(cron = "00/1 * * * * ?")
    public void send() {
        String message = "{\"BranchId\":\"1\",\"AGV\":[{\"Id\":\"001\",\"X\":1,\"Y\":2,\"Theta\":180,\"Status\":1,\"Error\":0,\"Battery\":13.0,\"Vel\":1.0,\"RotVel\":1.0,\"LineAcc\":0.1,\"RotAcc\":0.1}],\"Time\":\"1542872497\"}";
        ListenableFuture future = kafkaTemplate.send("AGV",message);
        future.addCallback(o -> System.out.println("send-消息发送成功："+message),throwable -> System.out.println("消息发送失败："+message));
    }
}
