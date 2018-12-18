package com.bgy.device.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bgy.device.entity.DishProductRelation;
import com.bgy.device.service.DishService;
import com.bgy.device.utils.ResultUtil;
import com.bgy.device.value.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Judith
 * @date 2018/12/15
 */
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping("/list")
    public ResultVo list() {
        List<DishProductRelation> relations = dishService.findAll();
        return ResultUtil.success(relations);
    }

    @PostMapping("/save")
    public ResultVo save(@RequestBody Map<String,Object> params) {

        dishService.updateDish(initParam(params));
        return ResultUtil.success(null);
    }

    @PostMapping("/delete")
    public ResultVo delete(@RequestBody Map<String,Object> params) {
        String data = (String) params.get("data");
        JSONArray jsonArray = JSONArray.parseArray(data);
        List<DishProductRelation> relationList = new ArrayList<>();
        for(int i=0;i<jsonArray.size();i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            relationList.add(initParam(jsonObject));
        }
        dishService.deleteDish(relationList);
        return ResultUtil.success(null);
    }

    private DishProductRelation initParam(Map<String,Object> params) {
        String id = (String) params.get("id");
        String dishId = (String) params.get("dishId");
        String productName = (String) params.get("productName");

        DishProductRelation relation = new DishProductRelation();
        relation.setId(id);
        if(StringUtils.isNotBlank(dishId)) {
            relation.setDishId(dishId);
        }
        if(StringUtils.isNotBlank(productName)) {
            relation.setProductName(productName);
        }
        return relation;
    }
}
