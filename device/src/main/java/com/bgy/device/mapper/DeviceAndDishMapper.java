package com.bgy.device.mapper;

import com.bgy.device.entity.DeviceAndDish;
import com.bgy.device.utils.MyMapper;

import java.util.List;

public interface DeviceAndDishMapper extends MyMapper<DeviceAndDish> {

    public void deleteAll();

    public void deleteByDevice(String deviceId);

    public List<DeviceAndDish> findByDevice(String deviceId);
}