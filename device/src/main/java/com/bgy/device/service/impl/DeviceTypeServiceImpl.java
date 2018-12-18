package com.bgy.device.service.impl;

import com.bgy.device.entity.DeviceType;
import com.bgy.device.mapper.DeviceTypeMapper;
import com.bgy.device.service.DeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Judith
 * @date 2018/12/13
 */
@Service
public class DeviceTypeServiceImpl implements DeviceTypeService {

    @Autowired
    private DeviceTypeMapper deviceTypeMapper;

    @Override
    public List<DeviceType> findAll() {
        return deviceTypeMapper.selectAll();
    }
}
