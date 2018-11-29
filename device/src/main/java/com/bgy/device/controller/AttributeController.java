package com.bgy.device.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bgy.device.entity.Attribute;
import com.bgy.device.entity.AttributeType;
import com.bgy.device.service.AttributeService;
import com.bgy.device.service.AttributeTypeService;
import com.bgy.device.utils.ResultUtil;
import com.bgy.device.value.AttributeVo;
import com.bgy.device.value.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attribute")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private AttributeTypeService attributeTypeService;

    @PostMapping("/list")
    public ResultVo list() {
        try {
            List<AttributeVo> attributes = attributeService.findAllDetail();
            return ResultUtil.success(attributes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResultVo save(@RequestBody Map<String,Object> params) {
        try {
            Integer typeId = Integer.valueOf((String) params.get("attributeTypeId"));

            Attribute attribute = new Attribute();
            attribute.setAttributeId((Integer) params.get("attributeId"));
            attribute.setAttributeName((String) params.get("attributeName"));
            attribute.setAttributeTypeId(typeId);
            attribute.setAttributeValue((String) params.get("attributeValue"));

            attributeService.save(attribute);
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
            JSONArray jsonArray = JSONArray.parseArray(data);
            List<Integer> ids = new ArrayList<>();
            for(int i=0;i<jsonArray.size();i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                Integer id = object.getInteger("attributeId");
                ids.add(id);
            }
            boolean flag = attributeService.delete(ids);
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
