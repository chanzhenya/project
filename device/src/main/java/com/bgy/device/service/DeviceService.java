package com.bgy.device.service;

import com.bgy.device.entity.Device;
import com.bgy.device.value.DeviceVo;

import java.util.List;

public interface DeviceService {

    public List<DeviceVo> findAllDetail();

    public List<Device> findPerantDevice();

    public void save(Device device);

    public Device findOne(String id);

    public List<Device> findAll();

    public boolean delete(List<String> ids);
}
