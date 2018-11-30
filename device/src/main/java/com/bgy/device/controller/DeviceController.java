package com.bgy.device.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bgy.device.entity.Attribute;
import com.bgy.device.entity.Branch;
import com.bgy.device.entity.Device;
import com.bgy.device.entity.DeviceType;
import com.bgy.device.service.AttributeService;
import com.bgy.device.service.BranchService;
import com.bgy.device.service.DeviceService;
import com.bgy.device.service.DeviceTypeService;
import com.bgy.device.utils.ResultUtil;
import com.bgy.device.value.DeviceVo;
import com.bgy.device.value.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deivceService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private DeviceTypeService deviceTypeService;

    @Autowired
    private AttributeService attributeService;

    @ResponseBody
    @PostMapping("/list")
    public ResultVo list() {
        try {
         List<DeviceVo> deviceList = deivceService.findAllDetail();
         return ResultUtil.success(deviceList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }

    @PostMapping("/submit")
    public ResultVo submit(@RequestBody Map<String,Object> params) {
        try {
            String branchId = (String) params.get("branchId");
            String deviceTypeId = (String) params.get("deviceTypeId");
            String attributeId = (String) params.get("attributeId");

            Device device = new Device();
            device.setDeviceName((String) params.get("deviceName"));
            device.setIp((String) params.get("ip"));
            device.setOnline(Integer.valueOf((String) params.get("online")));
            device.setPort(Integer.valueOf((String) params.get("port")));
            if(StringUtils.isNotBlank(branchId)) {
                device.setBranchId(Integer.valueOf(branchId));
            }
            device.setPid((String) params.get("pid"));
            if(StringUtils.isNotBlank(deviceTypeId)) {
                device.setDeviceTypeId(Integer.valueOf(deviceTypeId));
            }
            if(StringUtils.isNotBlank(attributeId)) {
                device.setAttributeId(Integer.valueOf(attributeId));
            }
            device.setDeviceId((String) params.get("deviceId"));

            deivceService.save(device);
            return ResultUtil.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResultVo delete(@RequestBody Map<String,Object> params) throws Exception {
        String data = (String) params.get("data");
        JSONArray array = JSONArray.parseArray(data);
        List<String> ids = new ArrayList<>();
        for(int i=0;i<array.size();i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            ids.add(jsonObject.getString("deviceId"));
        }
        deivceService.delete(ids);
        return ResultUtil.success(null);
    }

    @PostMapping("/select-option")
    public ResultVo selectOption() {
        try {
            Map<String,Object> data = new HashMap<>();
            List<Branch> branches = branchService.findAll();
            List<DeviceType> deviceTypes = deviceTypeService.findAll();
            List<Device> devices = deivceService.findPerantDevice();
            List<Attribute> attributes = attributeService.findAll();

            data.put("branches",branches);
            data.put("deviceTypes",deviceTypes);
            data.put("devices",devices);
            data.put("attributes",attributes);
            return ResultUtil.success(data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }
}
