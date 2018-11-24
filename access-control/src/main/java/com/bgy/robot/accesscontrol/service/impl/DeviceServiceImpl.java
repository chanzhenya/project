package com.bgy.robot.accesscontrol.service.impl;

import com.bgy.robot.accesscontrol.entity.Device;
import com.bgy.robot.accesscontrol.mapper.DeviceMapper;
import com.bgy.robot.accesscontrol.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public Device findByIp(String ip) {
        Device device = deviceMapper.selectByPrimaryKey(ip);
        return device;
    }
}
