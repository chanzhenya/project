package com.bgy.device.service.impl;

import com.bgy.device.entity.DeviceType;
import com.bgy.device.mapper.DeviceTypeMapper;
import com.bgy.device.service.DeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeviceTypeServiceImpl implements DeviceTypeService {

    @Autowired
    private DeviceTypeMapper deviceTypeMapper;

    @Override
    public List<DeviceType> findAll() {
        return deviceTypeMapper.selectAll();
    }

    @Override
    public void save(DeviceType deviceType) {
        if(deviceType.getTypeId() != null) {
            deviceTypeMapper.updateByPrimaryKeySelective(deviceType);
        } else {
            deviceTypeMapper.insert(deviceType);
        }
    }

    @Override
    public void delete(List<DeviceType> deviceTypes) throws Exception {
        for(DeviceType type:deviceTypes) {
            deviceTypeMapper.deleteByPrimaryKey(type);
        }
    }
}
