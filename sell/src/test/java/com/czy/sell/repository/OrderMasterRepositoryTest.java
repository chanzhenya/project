package com.czy.sell.repository;

import com.czy.sell.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author Judith
 * @date 2018/12/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("Alice");
        orderMaster.setBuyerPhone("12345678912");
        orderMaster.setBuyerAddress("ASCDE");
        orderMaster.setBuyerOpenid("110110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotEquals(null,result);
    }

    @Test
    public void findByBuyerOpenid() {
        String openid = "110110";
        PageRequest pageRequest = new PageRequest(0,10);
        Page<OrderMaster> orderMasterPage = repository.findByBuyerOpenid(openid,pageRequest);

        Assert.assertNotEquals(0,orderMasterPage.getTotalElements());
    }
}