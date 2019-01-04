package com.bgy.robot.kafka;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bgy.robot.entity.OrderInfo;
import com.bgy.robot.enums.Status;
import com.bgy.robot.service.OrderService;
import com.bgy.robot.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Judith
 * @date 2018/12/17
 */
@Component
public class KafkaConsumer {

    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = {"Topic_Test"},id = "delivery-robot-1", containerFactory = "kafkaListenerContainerFactory1")
    public void receive(String message) {
        System.out.println("kafka_test 1----消费消息："+message);
//        try {
//            JSONArray jsonArray = JSONArray.parseArray(message);
//            for (int i = 0; i < jsonArray.size(); i++) {
//                JSONObject object = jsonArray.getJSONObject(i);
//                OrderInfo orderInfo = new OrderInfo();
//                orderInfo.setOrderId(object.getString("orderId"));
//                orderInfo.setDishId(object.getString("productId"));
//                orderInfo.setDishName(object.getString("productName"));
//                orderInfo.setTableNo(object.getString("tableName"));
//                orderInfo.setStatus(Status.CATERING.getCode());
//                orderInfo.setId(String.valueOf(IdUtil.genItemId()));
//                orderInfo.setCreateTime(LocalDateTime.now());
//                orderService.insert(orderInfo);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
