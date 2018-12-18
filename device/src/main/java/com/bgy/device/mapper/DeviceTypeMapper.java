package com.bgy.device.mapper;

import com.bgy.device.entity.DeviceType;
import com.bgy.device.utils.MyMapper;

public interface DeviceTypeMapper extends MyMapper<DeviceType> {

    public void deleteAll();
}