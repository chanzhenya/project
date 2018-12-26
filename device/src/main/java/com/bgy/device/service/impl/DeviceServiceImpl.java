package com.bgy.device.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bgy.device.entity.*;
import com.bgy.device.mapper.*;
import com.bgy.device.service.DeviceService;
import com.bgy.device.utils.IdUtil;
import com.bgy.device.value.DeviceVo;
import com.bgy.device.value.PageData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private DeviceAndDishMapper deviceAndDishMapper;

    @Autowired
    private DeviceAndTableMapper deviceAndTableMapper;

    @Autowired
    private DishProductRelationMapper relationMapper;

    @Autowired
    private DeviceTypeMapper deviceTypeMapper;

    @Autowired
    private TableInfoMapper tableInfoMapper;

    @Autowired
    private CookDishesMapper cookDishesMapper;

    @Autowired
    private OrderDishesMapper orderDishesMapper;

    @Override
    public List<DeviceVo> findAll() {
        return deviceMapper.findByPage();
    }

    @Override
    @Transactional
    public void updateDevice(Device device) {
        deviceMapper.updateByPrimaryKeySelective(device);
    }

    @Transactional
    public void delete(List<Device> devices) {
        for(Device deviceVo:devices) {
            deviceAndTableMapper.deleteByDevice(deviceVo.getDeviceId());
            deviceAndDishMapper.deleteByDevice(deviceVo.getDeviceId());
            deviceMapper.deleteByPrimaryKey(deviceVo.getDeviceId());
        }
    }

    @Override
    public void sync(JSONObject jsonObject) throws Exception {
        //1.清空本地数据库
        deviceAndDishMapper.deleteAll();
        deviceMapper.deleteAll();
        tableInfoMapper.deleteAll();
        deviceTypeMapper.deleteAll();
        relationMapper.deleteAll();

        //2、同步信息
        //设备类型同步
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray deviceTypeList = data.getJSONArray("deviceTypeList");
        synsDeviceTypeList(deviceTypeList);

        //菜品信息同步
        JSONArray dishList = data.getJSONArray("dishList");
        syncDish(dishList);

        //设备信息同步
        JSONArray deviceList = data.getJSONArray("deviceList");
        syncDeivce(deviceList);

        //餐桌信息同步
        JSONArray tableList = data.getJSONArray("tableList");
        syncTable(tableList);
    }

    @Override
    public List<DeviceAndDish> getDeviceDishList(String deviceId) {
        return deviceAndDishMapper.findByDevice(deviceId);
    }

    @Override
    @Transactional
    public void deleteDeviceDish(DeviceAndDish deviceAndDish) {
        deviceAndDishMapper.delete(deviceAndDish);
    }

    @Override
    public void insertDeviceDish(String deviceId, String id) {
        DishProductRelation relation = relationMapper.selectByPrimaryKey(id);
        DeviceAndDish dish = new DeviceAndDish();
        dish.setDishId(relation.getDishId());
        dish.setDishName(relation.getProductName());
        dish.setDeviceId(deviceId);
        dish.setId(String.valueOf(IdUtil.genItemId()));
        deviceAndDishMapper.insert(dish);
    }

    @Override
    @Transactional
    public void initDatabase() {
        cookDishesMapper.deleteByState();
        orderDishesMapper.truncateTable();
    }

    private void syncDeivce(JSONArray deviceList) {
        for(int i=0;i<deviceList.size();i++) {
            JSONObject jsonObject = deviceList.getJSONObject(i);
            Device device = new Device();
            device.setDeviceId(jsonObject.getString("deviceId"));
            device.setDeviceName(jsonObject.getString("deviceName"));
            device.setIp(jsonObject.getString("ip"));
            device.setPort(jsonObject.getInteger("port"));
            device.setPid(jsonObject.getString("pid"));
            device.setOnline(jsonObject.getInteger("online"));
            device.setDeviceType(jsonObject.getString("deviceType"));
            device.setPositionCode(jsonObject.getString("posionCode"));
            device.setBranchId(jsonObject.getString("branchId"));
            device.setStationType(jsonObject.getInteger("stationType"));
            device.setStationPno(jsonObject.getInteger("stationPno"));
            device.setStationNo(jsonObject.getInteger("stationNo"));
            deviceMapper.insert(device);

            JSONArray dishes = jsonObject.getJSONArray("dishes");
            for(int j=0;j<dishes.size();j++) {
                JSONObject object = dishes.getJSONObject(j);
                String id = object.getString("id");
                if(StringUtils.isNotBlank(id)) {
                    DeviceAndDish dish = new DeviceAndDish();
                    dish.setId(object.getString("id"));
                    dish.setDishId(object.getString("dishId"));
                    dish.setDishName(object.getString("dishName"));
                    dish.setDeviceId(device.getDeviceId());
                    deviceAndDishMapper.insert(dish);
                }
            }
        }
    }

    private void syncDish(JSONArray dishList) {
        for(int i=0;i<dishList.size();i++) {
            JSONObject dish = dishList.getJSONObject(i);
            DishProductRelation relation = new DishProductRelation();
            relation.setId(dish.getString("ID"));
            relation.setDishId(dish.getString("DISH_ID"));
            relation.setProductName(dish.getString("DESCRIPTION"));
            relation.setProductId(dish.getString("PRODUCT_ID"));
            relationMapper.insert(relation);
        }
    }

    private void syncTable(JSONArray tableList) {
        for(int i=0;i<tableList.size();i++) {
            JSONObject jsonObject = tableList.getJSONObject(i);
            TableInfo tableInfo = new TableInfo();
            tableInfo.setBranchId(jsonObject.getString("BRANCH_ID"));
            tableInfo.setDeviceId(jsonObject.getString("DEVICE_ID"));
            tableInfo.setTableId(jsonObject.getString("ID"));
            tableInfo.setTableName(jsonObject.getString("NAME"));
            tableInfo.setPositionCode(jsonObject.getString("COORDINATE"));
            tableInfoMapper.insert(tableInfo);
        }
    }

    public void synsDeviceTypeList(JSONArray deviceTypeList) {
        for(int i=0;i<deviceTypeList.size();i++) {
            JSONObject jsonObject = deviceTypeList.getJSONObject(i);
            DeviceType deviceType = new DeviceType();
            deviceType.setTypeCode(jsonObject.getString("deviceTypeCode"));
            deviceType.setTypeName(jsonObject.getString("deviceTypeName"));
            deviceTypeMapper.insert(deviceType);
        }
    }
}
