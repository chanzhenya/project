package com.bgy.device.service;

import com.alibaba.fastjson.JSONObject;
import com.bgy.device.entity.Device;
import com.bgy.device.entity.DeviceAndDish;
import com.bgy.device.value.DeviceVo;
import com.bgy.device.value.PageData;

import java.util.List;

public interface DeviceService {

    public List<DeviceVo> findAll();

    public void updateDevice(Device device);

    public void delete(List<Device> devices);

    public void sync(JSONObject jsonObject) throws Exception;

    public List<DeviceAndDish> getDeviceDishList(String deviceId);

    public void deleteDeviceDish(DeviceAndDish deviceAndDish);

    public void insertDeviceDish(String deviceId, String id);
}
