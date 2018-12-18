package com.bgy.device.mapper;

import com.bgy.device.entity.DeviceAndTable;
import com.bgy.device.utils.MyMapper;

public interface DeviceAndTableMapper extends MyMapper<DeviceAndTable> {

    public void deleteAll();

    public void deleteByDevice(String deviceId);
}