package com.bgy.device.mapper;

import com.bgy.device.entity.Device;
import com.bgy.device.utils.MyMapper;
import com.bgy.device.value.DeviceVo;

import java.util.List;

public interface DeviceMapper extends MyMapper<Device> {

    public List<DeviceVo> findByPage();

    public void deleteAll();

    public void insertDevice(Device device);
}