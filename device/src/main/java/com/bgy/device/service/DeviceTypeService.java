package com.bgy.device.service;

import com.bgy.device.entity.DeviceType;

import java.util.List;

public interface DeviceTypeService {

    public List<DeviceType> findAll();

    public void save(DeviceType deviceType);

    public boolean delete(List<DeviceType> deviceTypes);
}
