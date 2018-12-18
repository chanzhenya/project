package com.bgy.device.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bgy.device.common.CommonCoreContent;
import com.bgy.device.entity.Device;
import com.bgy.device.entity.DeviceAndDish;
import com.bgy.device.entity.DeviceType;
import com.bgy.device.entity.DishProductRelation;
import com.bgy.device.service.DeviceService;
import com.bgy.device.service.DeviceTypeService;
import com.bgy.device.service.DishService;
import com.bgy.device.utils.ResultUtil;
import com.bgy.device.value.DeviceVo;
import com.bgy.device.value.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/device")
public class DeviceController {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private DeviceService deivceService;

    @Autowired
    private DishService dishService;

    @Autowired
    private DeviceTypeService deviceTypeService;

    @PostMapping("/list")
    public ResultVo list(@RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize",required = false,defaultValue = "10") int pageSize) {
        //PageData pageData = deivceService.findBypage(pageNum,pageSize);
        List<DeviceVo> devices = deivceService.findAll();
        return ResultUtil.success(devices);
    }

    @PostMapping("/select-option")
    public ResultVo selectOption() {
        List<DeviceVo> devices = deivceService.findAll();
        List<DishProductRelation> dishes = dishService.findAll();
        List<DeviceType> deviceTypes = deviceTypeService.findAll();
        Map<String,Object> data = new HashMap<>();
        data.put("deviceSelection",devices);
        data.put("dishSelection",dishes);
        data.put("deviceTypeSelection",deviceTypes);
        return ResultUtil.success(data);
    }

    @PostMapping("/submit")
    public ResultVo save(@RequestBody Map<String,Object> params) {

        Device device = initParam(params);

        deivceService.updateDevice(device);
        return ResultUtil.success(null);
    }

    @PostMapping("/delete")
    public ResultVo delete(@RequestBody Map<String,Object> params) {
        String data = (String) params.get("data");
        JSONArray array = JSONArray.parseArray(data);
        List<Device> deviceList = new ArrayList<>();
        for(int i=0;i<array.size();i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            Device device = initParam(jsonObject);
            deviceList.add(device);
        }
        deivceService.delete(deviceList);
        return ResultUtil.success(null);
    }

    @PostMapping("/sync")
    public ResultVo sync() throws Exception {
        Map<String,Object> params = new JSONObject();
        params.put("branchId", CommonCoreContent.BRANCH_ID);
        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(CommonCoreContent.SYNC_URL,params,JSONObject.class);
        JSONObject result = responseEntity.getBody();
        deivceService.sync(result);
        return ResultUtil.success(result);
    }

    @PostMapping("/dish-list")
    public ResultVo dishList(@RequestParam("deviceId")String deviceId) {
        List<DeviceAndDish> dishes = deivceService.getDeviceDishList(deviceId);
        return ResultUtil.success(dishes);
    }

    @PostMapping("/dish-submit")
    public ResultVo submitDeviceDish(@RequestBody Map<String,Object> params) {
        String id = (String) params.get("id");
        String deviceId = (String) params.get("deviceId");

        deivceService.insertDeviceDish(deviceId,id);
        return ResultUtil.success(null);
    }

    @PostMapping("/dish-delete")
    public ResultVo deleteDeviceDish(@RequestBody Map<String,Object> params) {
        String data = (String) params.get("data");
        JSONObject jsonObject = JSONObject.parseObject(data);
        DeviceAndDish dish = new DeviceAndDish();
        dish.setId(jsonObject.getString("idx"));
        dish.setDeviceId(jsonObject.getString("deviceId"));
        dish.setDishName(jsonObject.getString("dishName"));
        dish.setDishId(jsonObject.getString("dishId"));
        deivceService.deleteDeviceDish(dish);
        return ResultUtil.success(null);
    }

    private Device initParam(Map<String,Object> params) {
        String deviceId = (String) params.get("deviceId");
        String deviceName = (String) params.get("deviceName");
        String ip = (String) params.get("ip");
        String port = (String) params.get("port");
        String deviceType = (String) params.get ("deviceType");
        String pid = (String) params.get("pid");
        String online = (String) params.get("online");
        String stationType = (String) params.get("stationType");
        String stationPno = (String) params.get("stationPno");
        String stationNo = (String) params.get("stationNo");
        String positionCode = (String) params.get("positionCode");

        Device device = new Device();
        if(StringUtils.isNotBlank(deviceId)) {
            device.setDeviceId(deviceId);
        }
        if(StringUtils.isNotBlank(deviceName)) {
            device.setDeviceName(deviceName);
        }
        if(StringUtils.isNotBlank(ip)) {
            device.setIp(ip);
        }
        if(StringUtils.isNotBlank(port)) {
            device.setPort(Integer.valueOf(port));
        }
        if(StringUtils.isNotBlank(deviceType)) {
            device.setDeviceType(deviceType);
        }
        if(StringUtils.isNotBlank(pid)) {
            device.setPid(pid);
        }
        if(StringUtils.isNotBlank(online)) {
            device.setOnline(Integer.valueOf(online));
        }
        if(StringUtils.isNotBlank(stationType)) {
            device.setStationType(Integer.valueOf(stationType));
        }
        if(StringUtils.isNotBlank(stationPno)) {
            device.setStationPno(Integer.valueOf(stationPno));
        }
        if (StringUtils.isNotBlank(stationNo)) {
            device.setStationNo(Integer.valueOf(stationNo));
        }
        if (StringUtils.isNotBlank(positionCode)) {
            device.setPositionCode(positionCode);
        }

        return device;
    }

    private Device initParam(JSONObject params) {
        Device device = new Device();
        device.setDeviceId(params.getString("deviceId"));
        device.setDeviceName(params.getString("deviceName"));
        device.setIp(params.getString("ip"));
        device.setPort(params.getInteger("port"));
        device.setDeviceType(params.getString("deviceType"));
        device.setPid(params.getString("pid"));
        device.setOnline(params.getInteger("online"));
        device.setStationType(params.getInteger("stationType"));
        device.setStationPno(params.getInteger("stationPno"));
        device.setStationNo(params.getInteger("stationNo"));
        device.setPositionCode(params.getString("positionCode"));

        return device;
    }
}
