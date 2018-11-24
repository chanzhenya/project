package com.bgy.robot.accesscontrol.service;

import com.bgy.robot.accesscontrol.entity.Device;

public interface DeviceService {

    public Device findByIp(String ip);
}
