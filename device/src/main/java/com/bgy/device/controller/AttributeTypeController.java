package com.bgy.device.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bgy.device.entity.AttributeType;
import com.bgy.device.service.AttributeTypeService;
import com.bgy.device.utils.ResultUtil;
import com.bgy.device.value.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attribute-type")
public class AttributeTypeController {

    @Autowired
    private AttributeTypeService attributeTypeService;

    @PostMapping("/list")
    public ResultVo list() {
        try {
            List<AttributeType> attributeTypes = attributeTypeService.findAll();
            return ResultUtil.success(attributeTypes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResultVo save(@RequestBody Map<String,Object> params) {
        try {
            AttributeType type = new AttributeType();
            type.setAttributeTypeId((Integer) params.get("attributeTypeId"));
            type.setAttributeTypeName((String) params.get("attributeTypeName"));
            attributeTypeService.save(type);
            return ResultUtil.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResultVo delete(@RequestBody Map<String,Object> params) {
        try {
            String data = (String) params.get("data");
            JSONArray array = JSONArray.parseArray(data);
            List<AttributeType> attributeTypes = new ArrayList<>();
            for(int i=0;i<array.size();i++) {
                JSONObject object = array.getJSONObject(i);
                AttributeType attributeType = new AttributeType();
                attributeType.setAttributeTypeId(object.getInteger("attributeTypeId"));
                attributeType.setAttributeTypeName(object.getString("attributeTypeName"));
                attributeTypes.add(attributeType);
            }
            boolean flag = attributeTypeService.delete(attributeTypes);
            if(flag) {
                return ResultUtil.success(null);
            } else {
                return ResultUtil.error("删除失败，数据正在被应用！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }
}
