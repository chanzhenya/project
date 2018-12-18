package com.bgy.device.service.impl;

import com.bgy.device.entity.Device;
import com.bgy.device.mapper.DeviceMapper;
import com.bgy.device.value.DeviceVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Judith
 * @date 2018/12/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceServiceImplTest {

    @Autowired
    private DeviceMapper deviceMapper;

    @Test
    @Transactional
    public void insert() {
        Device device = new Device();
        device.setDeviceId("1");
        device.setDeviceName("Test");
        device.setBranchId("2345678");
        device.setStationType(0);
        device.setOnline(1);
        int result = deviceMapper.insert(device);
        Assert.assertEquals(1,1);
    }

    @Test
    public void finAll() {
        List<DeviceVo> deviceVos = deviceMapper.findByPage();
        Assert.assertNotEquals(0,deviceVos);
    }
}