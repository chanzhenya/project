package com.bgy.device.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bgy.device.entity.DeviceType;
import com.bgy.device.service.DeviceTypeService;
import com.bgy.device.utils.ResultUtil;
import com.bgy.device.value.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/device-type")
public class DeviceTypeController {

    @Autowired
    private DeviceTypeService deviceTypeService;

    @PostMapping("/list")
    public ResultVo list() {
        try {
            List<DeviceType> deviceTypes = deviceTypeService.findAll();
            return ResultUtil.success(deviceTypes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResultVo save(@RequestBody Map<String,Object> params) {
        try {
            Integer typeId = (Integer) params.get("typeId");
            String typeCode = (String) params.get("typeCode");
            String typeName = (String) params.get("typeName");

            DeviceType deviceType = new DeviceType();
            if(typeId!=null) {
                deviceType.setTypeId(Integer.valueOf(typeId));
            }
            deviceType.setTypeCode(typeCode);
            deviceType.setTypeName(typeName);
            deviceTypeService.save(deviceType);
            return ResultUtil.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResultVo delete(@RequestBody Map<String,Object> params) throws Exception {
        String data = (String) params.get("data");
        JSONArray jsonArray = JSONArray.parseArray(data);
        List<DeviceType> deviceTypes = new ArrayList<>();
        for(int i=0;i<jsonArray.size();i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            DeviceType deviceType = new DeviceType();
            deviceType.setTypeId(jsonObject.getInteger("typeId"));
            deviceType.setTypeName(jsonObject.getString("typeName"));
            deviceType.setTypeCode(jsonObject.getString("typeCode"));
            deviceTypes.add(deviceType);
        }
        deviceTypeService.delete(deviceTypes);
        return ResultUtil.success(null);
    }
}
