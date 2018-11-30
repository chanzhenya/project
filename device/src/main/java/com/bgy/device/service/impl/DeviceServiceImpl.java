package com.bgy.device.service.impl;

import com.bgy.device.entity.Branch;
import com.bgy.device.entity.Device;
import com.bgy.device.entity.DeviceType;
import com.bgy.device.mapper.BranchMapper;
import com.bgy.device.mapper.DeviceMapper;
import com.bgy.device.mapper.DeviceTypeMapper;
import com.bgy.device.service.DeviceService;
import com.bgy.device.value.DeviceVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private BranchMapper branchMapper;

    @Autowired
    private DeviceTypeMapper deviceTypeMapper;

    @Override
    public List<DeviceVo> findAllDetail() {
        return deviceMapper.findAllDetail();
    }

    @Override
    public List<Device> findPerantDevice() {
        return deviceMapper.findPerantDevice();
    }

    @Override
    public void save(Device device) {
        if(StringUtils.isNotBlank(device.getDeviceId())) {
            deviceMapper.updateByPrimaryKeySelective(device);
        } else {
            Branch branch = branchMapper.selectByPrimaryKey(device.getBranchId());
            DeviceType deviceType = deviceTypeMapper.selectByPrimaryKey(device.getDeviceTypeId());
            Integer index = deviceMapper.findMaxIndex(device);
            if(index == null) {
                index = 0;
            }
            index++;
            String deviceId = branch.getBranchNo()+"_"+deviceType.getTypeCode()+"_"+index;
            device.setDeviceId(deviceId);
            device.setDeviceIndex(index);
            deviceMapper.insert(device);
        }
    }

    @Override
    public Device findOne(String id) {
        return deviceMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Device> findAll() {
        return deviceMapper.selectAll();
    }

    @Override
    public void delete(List<String> ids) throws Exception {
        for(String id:ids) {
            deviceMapper.deleteByPrimaryKey(id);
        }
    }
}
