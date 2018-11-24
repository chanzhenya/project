package com.bgy.robot.accesscontrol.kafka;

import com.alibaba.fastjson.JSONObject;
import com.bgy.robot.accesscontrol.utils.BeanUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
public class KafkaConsumer {

//	@KafkaListener(topics= {"order_2"})
	public void receive(String message) {
		System.out.println("kafka_test----消费消息："+message);
	}


}
